package online.babylove.www.example1;

/**
 * 男演员（周杰伦）
 * 继承了@Thread
 * @author zhangjiawei
 *
 */
public class Actor extends Thread{
	
	/**
	 * 例子1
	 * 这个例子用线程模拟了男演员周杰和女演员昆凌在舞台上轮流演出
	 * 并且分别演示了继承@Thread类和实现@Runnable接口
	 * @param args
	 */
	public static void main(String[] args) {
		Thread actor = new Actor();
		actor.setName("周杰伦");
		actor.start();
		
		Thread actress = new Thread(new Actress(), "昆凌");
		actress.start();
	}
	
	/**
	 * 重写run方法
	 */
	@Override
	public void run() {
		System.out.println(getName() + "进行演出!");
		//这是一个计数器，当运行一定次数的时候就退出线程
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(getName() + "演出次数" + (++count));
			if (count == 100) {
				keepRunning = false;
			}
			//预防运行速度太快，每运行10次，我们使用sleep方法停顿一下
			if (count %10 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(getName() + "演出结束！");
	}
}

/**
 * 女演员（昆凌）
 * 实现@Runnable
 * @author zhangjiawei
 *
 */
class Actress implements Runnable{
	
	/**
	 * 继承@Runnable必须实现run这个方法
	 */
	public void run() {
		System.out.println(Thread.currentThread().getName() + "进行演出!");
		//这是一个计数器，当运行一定次数的时候就退出线程
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(Thread.currentThread().getName() + "演出次数" + (++count));
			if (count == 100) {
				keepRunning = false;
			}
			//预防运行速度太快，每运行10次，我们使用sleep方法停顿一下
			if (count %10 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(Thread.currentThread().getName() + "演出结束！");
	}
	
}
