package online.babylove.www.example2;


public class Stage extends Thread{

	@Override
	public void run() {
		
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
