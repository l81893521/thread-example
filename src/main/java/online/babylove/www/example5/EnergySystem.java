package online.babylove.www.example5;

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
		//如果能量源能量不足，则放弃本次转移
		if (energyBoxes[from] < amount) {
			return ;
		}
		System.out.print(Thread.currentThread().getName());
		energyBoxes[from] -= amount;
		System.out.printf("从%d转移%10.2f单位能量到%d",from, amount, to);
		energyBoxes[to] += amount;
		System.out.printf("能量总和:%10.2f%n",getTotalEnergy());
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
