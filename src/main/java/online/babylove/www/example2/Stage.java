package online.babylove.www.example2;

/**
 * 舞台
 * 舞台也是一个线程，在舞台线程中创建 隋唐军线程 和 农民起义军线程 并启动他们
 * 我们可以看到 隋唐军 和 农民起义军 并不是有规律的你一刀我一刀
 * 因为@ArmyRunnable每次发起攻击后都调用了yield()方法来释放处理器资源，让2个军队重新去抢占线程
 * @author zhangjiawei
 *
 */
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
		
	}
	
	public static void main(String[] args) {
		new Stage().start();
	}
}
