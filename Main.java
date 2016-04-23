import java.util.*;
public class Main 
{
	public static void main(String args[]) {
		Snake s1 = new Snake();
		System.out.println(" ----------------------------------------------------------");
		System.out.println();
		System.out.println("                           贪吃蛇");
		System.out.println();
		System.out.println("                           Snake");
		System.out.println();
		System.out.println(" ----------------------------------------------------------");

		System.out.println("-----------------须知|notice-----------------");
		System.out.println("	回车代表提交，如未输入就提交，则按原方向前进，若");
		System.out.println("键入定义按钮外的其他字符，则视为未输入。");
		System.out.println("	Enter to hand in.The snake will not change");
		System.out.println("it's way if you hand in an null value or a ");
		System.out.println("wrong value.");
		System.out.println("------回车开始游戏|enter to begin the game-------");
		s1.useScan();

		//询问开始
		System.out.println("下面开始规定贪吃蛇行动范围的宽度..|now hand in the weight..");
		while (true) {
			try {
				s1.setWeight();
				System.out.println("宽度OK|weight is OK");
				break;
			}
			catch (InputMismatchException ex1) {
				System.out.println("再输一遍，不要乱来!!!!!!|wrong value!!!");
				s1.useScan();//除去上次错误输入的信息可使用.nextLine();
			}
		}
		System.out.println();
		System.out.print("这次是高度......|the height...");
		while (true) {
			try {
				s1.setHeight();
				System.out.println("高度OK|height is OK.");
				System.out.println();
				break;
			}
			catch (InputMismatchException ex2) {
				System.out.println("再输一遍，不要乱来!!!!!!|wrong value");
				s1.useScan();//除去上次错误输入的信息可使用.nextLine();
			}
		}
		s1.screan();
		System.out.println("下面开始定义按键,请按下一个按键作为该方向，然后回车提交...........");
		System.out.println("PS:在命令行里面可以定义为键盘上的上下左右");
		while (true) {
	 		try {
				s1.setButton();
				System.out.println("按键定义完毕，开始Play!!!!|OK,start play it!!!");
				break;
			}
			catch (Exception ex3) {
				System.out.println("有误，请重试");
			}
		}
		s1.startArrayList();
		s1.food();
		//s1.useScan();
		while (s1.getOn() != -1) {
			s1.screan();
			System.out.println("输入你要转向的方向：");
			s1.input();
		}
	}
}
