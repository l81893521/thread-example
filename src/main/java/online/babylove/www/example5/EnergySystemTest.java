package online.babylove.www.example5;

/**
 * 这是这次例子的入口
 * 我们构建了指定数量的能量盒子，每个盒子也初始化了相同的能量值
 * 然后我们构建跟盒子数量一样的线程数，每个盒子都是一个单独的线程
 * 每个盒子之间，互相传输自己的能量值
 * 我们并没有做对能量值的添加或者减少，所以根据能量守恒定律来说，能量的总值是不变的
 * 但是当你运行这个程序的时候，你会发现能量总值出现错误，并不是等于（盒子数量 * 每个盒子能量），它减少了
 * 为什么呢？什么是争用条件呢?
 * 请看下一个例子example6
 * @author zhangjiawei
 *
 */
public class EnergySystemTest {
	//初始化能量盒子的数量
	public static final int BOX_AMOUNT = 100;
	//初始化每个能量盒子拥有多少能量
	public static final double INITIAL_ENERGY = 1000;
	
	public static void main(String[] args) {
		//构建能量系统
		EnergySystem energySystem = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
		for (int i = 0; i < BOX_AMOUNT; i++) {
			//我们初始化了跟盒子数量一样的线程数，每个盒子都是一个单独的线程，他们各自传输着自己的能量
			EnergyTransferTask energyTransferTask = new EnergyTransferTask(energySystem, i, INITIAL_ENERGY);
			Thread thread = new Thread(energyTransferTask, "TransferThread_" + i);
			thread.start();
		}
	}

}
