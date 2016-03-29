package online.babylove.www.example3;


/**
 * 军队
 * 军队存在2种角色（隋唐军和农民军）
 * @author zhangjiawei
 *
 */
public class ArmyRunnable implements Runnable{
	//volatile保证了线程可以正确读取其他线程写入的值
	//可见性 ref JMM(java memory model), happens-before原则
	volatile boolean isKeepRunning = true;
	
	public void run() {
		while(isKeepRunning){
			//发动五连击
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":向对方砍了一刀");
				//释放当前线程占用处理器的资源，让线程重新抢占处理器，下一次是谁砍谁还不知道呢
				Thread.yield();
			}
		}
		System.out.println(Thread.currentThread().getName() + "结束了战斗!");
	}
}
