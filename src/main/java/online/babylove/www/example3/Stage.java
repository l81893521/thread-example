package online.babylove.www.example3;

/**
 * 在example2的基础上，我们又做出了一点点小修改
 * 首先，我们在舞台开幕的时候，我们加入了一些小小的对白
 * 然后，加入了关键人物@KeyPersonThread(程咬金)这个新的线程
 * 我们可以 程咬金线程 入场时，双方军队会停止
 * 同时，程咬金线程 会调用join方法，让 舞台线程 等待 程咬金线程 演出完毕，再输出上演完毕这句话
 * @author zhangjiawei
 *
 */
public class Stage extends Thread{

	@Override
	public void run() {
		System.out.println("欢迎大家收看隋唐演义!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("大幕徐徐拉开!!!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("大戏正式上演!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		ArmyRunnable dynastyArmyRunnable = new ArmyRunnable();
		ArmyRunnable revoltArmyRunnable = new ArmyRunnable();
		
		//使用runnable接口创建线程
		Thread dynastyArmyThread = new Thread(dynastyArmyRunnable,"隋唐军");
		Thread revoltArmyThread = new Thread(revoltArmyRunnable,"农民起义军");
		
		//启动线程
		dynastyArmyThread.start();
		revoltArmyThread.start();
		
		//舞台线程休眠，让大家专心观看
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//收兵 因为程咬金准备要出场
		dynastyArmyRunnable.isKeepRunning = false;
		revoltArmyRunnable.isKeepRunning = false;
//		dynastyArmyThread.stop();
//		revoltArmyThread.stop();
		
		Thread chengyaojin = new KeyPersonThread();
		chengyaojin.setName("程咬金");
		
		
		System.out.println("半路杀出一个程咬金！！！");
		chengyaojin.start();
		//让所有线程等待程咬金执行完毕
		try {
			chengyaojin.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("上演完毕!");
		System.out.println("欢迎大家收看!");
		
		try {
			revoltArmyThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Stage().start();
	}
}
