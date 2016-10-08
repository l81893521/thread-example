package online.babylove.www.example6;

import java.util.Date;

/**
 * 宇宙能量系统
 * 遵循宇宙能量守恒定律
 * 能量不会凭空消失，能量总量不会减少也不会增加，只会进行互相转移
 * @author zhangjiawei
 *
 */
public class EnergySystem {
	
	//能量盒子，存储能量的地方
	private final double[] energyBoxes;
	//一个锁对象
	private final Object lockObject = new Object();
	//记录执行次数
	private int count;
	//耗时，毫秒数
	private long currentTimestamp = new Date().getTime();
	
	/**
	 * 初始化能量盒子
	 * @param n 需要创建能量盒子的数量
	 * @param initialEnergy 每个盒子存在多少能量
	 */
	public EnergySystem(int n, double initialEnergy) {
		energyBoxes = new double[n];
		for (int i = 0; i < energyBoxes.length; i++) {
			energyBoxes[i] = initialEnergy;
		}
	}
	
	/**
	 * 能量的转移，从一个盒子到另一个盒子
	 * @param from 能量源
	 * @param to 能量终点
	 * @param amount 需要转移的数量
	 */
	public void transfer(int from, int to, double amount){
		/*
		 * lockObject是一个普通的Object对象
		 * synchronized保护了我们的关键代码，只有获得了lockObject这个锁对象才能进入我们的关键代码
		 * 其他没有或者lockObject锁的线程，没法进入我们关键代码的区域
		 * java的语法，保证了我们的线程同一时间只有一个线程可以获得lockObject
		 */
		synchronized (lockObject) {
			//如果能量源能量不足，则放弃本次转移
			//但是这并不是一个好的方法，因为线程还在继续运行，每次执行都要使用synchronized (lockObject)进行加锁，这个开销是很大的
//			if (energyBoxes[from] < amount) {
//				return ;
//			}
			//while循环，保证条件不满足时任务都会被阻挡
			//而不是继续竞争cpu资源
			//都在wait set上等待
			while (energyBoxes[from] < amount) {
				try {
					//能量盒子中的能量不足，进入等待状态，以免重复获取锁而消耗系统资源
					//因为其他盒子有可能往当前盒子注入能量，等待盒子能量足够时候，再执行关键代码
					lockObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.print(Thread.currentThread().getName());
			energyBoxes[from] -= amount;
			System.out.printf("从%d转移%10.2f单位能量到%d",from, amount, to);
			energyBoxes[to] += amount;
			System.out.printf("能量总和:%10.2f",getTotalEnergy());
			System.out.printf("\t执行次数"+(++count));
			System.out.printf("\t耗时(毫秒)"+(new Date().getTime() - currentTimestamp) + "%n");
			//唤醒等待中的盒子
			lockObject.notifyAll();
		}
		
	}
	
	/**
	 * 获取世界能量的总和
	 * @return
	 */
	public double getTotalEnergy(){
		double sum = 0;
		for(double amount : energyBoxes){
			sum += amount;
		}
		return sum;
	}
	
	/**
	 * 获取能量盒子的长度
	 * @return
	 */
	public int getBoxAmount(){
		return energyBoxes.length;
	}
}
