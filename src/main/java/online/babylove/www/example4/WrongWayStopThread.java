package online.babylove.www.example4;

/**
 * 这里演示了使用interrupt()来停止线程
 * @author zhangjiawei
 *
 */
public class WrongWayStopThread extends Thread{
	public static void main(String[] args) {
		WrongWayStopThread wrongWayStopThread = new WrongWayStopThread();
		System.out.println("Starting thread...");
		wrongWayStopThread.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Interrupting thread...");
		wrongWayStopThread.interrupt();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("stopping application...");
	}
	
	@Override
	public void run() {
		
		//当上面使用wrongWayStopThread.interrupt();
		//并不能有效停止下面代码
		//因为interrupt()只是修改了线程的interrupt的状态值可以
//		while(true){
//			System.out.println("Thread is running...");
//			long time = System.currentTimeMillis();
//			while(System.currentTimeMillis() - time < 1000){
//				//减少屏幕输出，空循环
//			}
//		}
		
		
		//如果使用isInterrupted()来判断当前线程是否被中断,线程可以有效停止
		//不过使用用isInterrupted()来判断，也等同于用标记的方式停止线程，只不过换了一个方式
//		while(!this.isInterrupted()){
//			System.out.println("Thread is running...");
//			long time = System.currentTimeMillis();
//			while(System.currentTimeMillis() - time < 1000){
//				//减少屏幕输出，空循环
//			}
//		}
		
		//为什么上面我们都使用while循环来模拟每秒输出一次，而不是用sleep()来等待1000毫秒呢
		//我们不妨来试一下，如果使用sleep()来等待1000毫秒会发什么什么
		//我们看到会出现异常，因为线程处于阻塞的情况下，如wait(),join(),sleep()等等，此时调用interrupt()是没有办法中断线程的
		//同时也没法改变线程是否被中断这个状态，同时还会抛出一个InterruptedException,这就是为什么我们调用这些方法，都要捕获这个异常
		//所以我们的线程没有中断，而且还会继续运行下去
		while(!this.isInterrupted()){
			System.out.println("Thread is running...");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
