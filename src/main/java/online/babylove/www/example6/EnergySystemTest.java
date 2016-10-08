package online.babylove.www.example6;

/**
 * 这是这次例子的入口
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
