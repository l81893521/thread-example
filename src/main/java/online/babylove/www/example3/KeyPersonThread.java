package online.babylove.www.example3;

/**
 * 关键人物（程咬金）
 * @author zhangjiawei
 *
 */
public class KeyPersonThread extends Thread{
	
	@Override
	public void run() {
		//关键人物拥有特殊技能，可以发动10连击
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + "向敌人砍了一刀!");
		}
	}
}
