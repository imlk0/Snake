
import java.util.*;
public class Main
{
	public static void main (String args[]){
		Snake s1 = new Snake();
			System.out.println(" ----------------------------------------------------------");
			System.out.println("");
			System.out.println("                           贪吃蛇");
			System.out.println("");
			System.out.println(" ----------------------------------------------------------");

			System.out.println("--------------------须知--------------------");
			System.out.println("    正式进入游戏后贪吃蛇只接受您的正确输入，");
			System.out.println("不能后退，手机玩家请调至输入法数字九宫格界面");
			System.out.println("玩耍，回车代表提交，如未输入就提交，则按原反");
			System.out.println("向前进，若键入定义按钮外的其他字符，则视为未");
			System.out.println("输入，还有什么不清楚的，不要问我...");
			System.out.println("----------------回车开始游戏----------------");
			s1.useScan();

		//询问开始
		System.out.println("下面开始规定贪吃蛇行动范围的宽度......");
		while(true){
			try{
				s1.setWeight();
				System.out.println("宽度OK");
				break;
			}catch(InputMismatchException ex1){
				System.out.println("再输一遍，不要乱来!!!!!!");
				s1.useScan();//除去上次错误输入的信息可使用.nextLine();
			}
		}
		System.out.println();
		System.out.print("这次是高度......");
		while(true){
			try{
				s1.setHeight();
				System.out.println("高度OK");
				System.out.println();
				break;
			}catch(InputMismatchException ex2){
				System.out.println("再输一遍，不要乱来!!!!!!");
				s1.useScan();//除去上次错误输入的信息可使用.nextLine();
			}
		}
		s1.screan();
		System.out.println("下面开始定义按键,请按下一个按键作为该方向，然后回车提交...........");
		System.out.println("PS:在命令行里面可以定义为键盘上的上下左右");
		while(true){
	 		try{
				s1.setButton();
				System.out.println("按键定义完毕，开始Play!!!!");
				break;
			}catch(Exception ex3){
				System.out.println("有误，请重试");
			}
		}
		s1.startArrayList();
		s1.food();
		//s1.useScan();
		while(s1.getOn()!=-1){
			s1.screan();
			System.out.print("得分：" + s1.getScore() + "    当前方向： ");
			switch (s1.getR()){
				case(4):System.out.print("←");//左
					break;
				case(6):System.out.print("→");//右
					break;
				case(2):System.out.print("↑");//上
					break;
				case(8):System.out.print("↓");//下
					break;
				default:System.out.print("→");//右
			}
			s1.bigFoodTime();
			System.out.println("输入你要转向的方向：");
			s1.input();

		}
		
	}
}
