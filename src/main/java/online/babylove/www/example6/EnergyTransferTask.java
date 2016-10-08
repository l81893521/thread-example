package online.babylove.www.example6;

import java.util.Random;


public class EnergyTransferTask implements Runnable{
	
	//共享世界的能量
	private EnergySystem energySystem;
	//能量转移的源泉下标
	private int fromBox;
	//能次能转移能量的最大值
	private double maxAmount;
	//最大休眠时间(毫秒)
	private int delay = 10;
	
	public EnergyTransferTask(EnergySystem energySystem, int from, double max) {
		this.energySystem = energySystem;
		this.fromBox = from;
		this.maxAmount = max;
	}

	public void run() {
		while(true){
			//随机生成一个接受能量的盒子下标
			int toBox = (int) (energySystem.getBoxAmount() * Math.random());
			//随机生成需要转移能量数量
			double amount = maxAmount * Math.random();
			energySystem.transfer(fromBox, toBox, amount);
			try {
				Thread.sleep((int)(delay * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Random random = new Random();
		
		System.out.println(random.nextDouble());
		System.out.println(Math.random());
	}
}
