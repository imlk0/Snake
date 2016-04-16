import java.lang.*;
import java.util.*;

public class Snake
{
	private Scanner scan = new Scanner(System.in);//实例化Scanner
	private int on = 0;//描述剩余步伐------有getter
	private int r;//开始时定义   ------有getter
	private int fi_1;//开始时定义，用于储存末尾数字;
	private int y= 0;//验证次数
	private int bk = 0;//描述是否后退
	private int food = 0;//储存食物地点
	private int bigFood = 0;//储存大食物
	private int eaten = 0;//储存周期内吃了food的个数   ------有getter
	private int score = 0;//得分  ------有getter
	private int weight;//宽度
	private int height;//高度
	private String up;//用于储存按键，下同
	private String down;
	private String left;
	private String right;

	//getter
	public int getOn(){
		return on;
	}
	public int getR(){
		return r;
	}
	public int getScore(){
		return score;
	}
	public void useScan()
	{
		scan.nextLine();
	}
	void setWeight()throws InputMismatchException{
		weight= scan.nextInt();

		if(weight<10){
			System.out.println("宽度都还没10怎么玩啊!!!!!!");
			throw new InputMismatchException();
		}
	}
	void setHeight()throws InputMismatchException{
		height = scan.nextInt();
		if(height<10){
			System.out.println("就这么点???");
			throw new InputMismatchException();
		}
	}
	void setButton()throws Exception {//设定Button
		System.out.print("↑  :");
		up = scan.next();//使用next()而不是nextLine保证一定输入,下同
		System.out.println();

		System.out.print("↓  :");
		down = scan.next();
		System.out.println();

		System.out.print("←  :");
		left = scan.next();
		System.out.println();

		System.out.print("→  :");
		right = scan.next();
		System.out.println();

		if ((up.equals(down))||(up.equals(left))||(up.equals(right))||(down.equals(left))||(down.equals(right))||(left.equals(right))) {
			throw new Exception();
		}
	}
	ArrayList<Integer> sn = new ArrayList<Integer>();//创建Array数组
	public void startArrayList(){//初始化数组
		sn.add((weight/2)+(((height/2)-1)*weight));
		sn.add(sn.get(0)-1);
		sn.add(sn.get(0)-2);
	}
	public void Exchange(){//进行位置调换
		if (y == 0)
		{//正常状态y为0
			for (int i = sn.size()-1; i > 0; i--)
			{//倒序
				sn.set(i,sn.get(i - 1));
			}
		}
		else
		{//发生异常，回滚
			for (int i = 1; i <= sn.size()-1; i++)
			{//正序
				if (i == sn.size()-1)
				{
					sn.set(i, fi_1);
				}
				else
				{
					sn.set(i, sn.get(i + 1));
				}
			}
		}
	}
	public void back (){//判断是否是后退
		if(sn.get(0).equals(sn.get(2))){//此处应用name1.equals(name2)判断俩对象的内容是否一致，而不能使用==
			bk++;
		}else{}
	}
	public void input(){//主体部分
		String str = scan.nextLine();//暂存字符串str
		
		if (str.equals(up)) {//将键盘输入转化为r的值代表方向
			r=2;
		}else{
			if (str.equals(down)) {
				r=8;
			}else{
				if (str.equals(left)) {
					r=4;
				}else{
					if (str.equals(right)) {
						r=6;
					}else{
						if(r!=0){//非开局输入且输入错误
							System.out.println("已忽略您的输入，延续上次动作");
						}else{//开局时候未定义反向，则默认向右(6)
							r = 6;
						}
					}
				}
			}
		}

		System.out.println();
		fi_1 = sn.get(sn.size()-1);//储存最后一项
		while(true){
			Exchange();
			switch (r){
				case(4):sn.set(0,sn.get(0) - 1);//左
					break;
				case(6):sn.set(0,sn.get(0) + 1);//右
					break;
				case(2):sn.set(0,sn.get(0) - weight);//上
					break;
				case(8):sn.set(0,sn.get(0) + weight);//下
					break;
			}
			wall();
			back();//判断是否back
			
			if ((y==0)&&(bk ==1)) {//若为后退，则开始执行
				r = 10-r;
				y = 1;
				System.out.println("你逗我呢......");
			}else{

				break;
			}
		}
		//一步移动完成
		if(sn.get(0)==food){//判断是否吃到food
			if(bigFood==0){
				eaten ++;
			}
			score ++;
			sn.add(fi_1);
			if(sn.size()!=weight*height){
				food();
			}
		}
		if(sn.get(0)==bigFood){//判断是否吃到bigFood
			score =score +3;
			sn.add(fi_1);
			on=0;
		}
		if (on != 0&& bk!=1) {
			on--;
		}

		if (on==0) {
			bigFood = 0;
		}
		if (eaten==5&&bigFood==0&&sn.size()+1!=weight*height) {//产生bigFood的条件
			bigFood();
		}
		if (bigFood !=0) {
			eaten=0;
		}
		if (same()) {//判断是否死亡
			System.out.println();
			System.out.println();
			System.out.println("你输了！！！！！");
			System.out.println();
			System.out.println("最后得分：" + score);
			on = -1;//游戏状态改为结束(0)
		}
		if(sn.size() == weight*height){//判断是否吃完
			System.out.println("没想到你居然吃完了.....快到群里告诉我一声............反正我也不会给你奖励.........");
			on=-1;
		}

		y = 0;
		bk = 0;
	}
	boolean same (){//判断是否自己咬自己
		boolean b = false;
		for (int i =3;i<sn.size() ; i++) {
			if(sn.get(0).equals(sn.get(i))){
				b = true;
			}
		}
		return b ;
	}
	void wall(){//该块帮助蛇头穿墙到达正确位置
		if (sn.get(0)<=0||sn.get(0)>(weight*height)||((r==4)&&(sn.get(0)%weight==0))  ||  ((r==6)&&(sn.get(0)%weight==1))) {
			if (((r==4)&&(sn.get(0)%weight==0))  ||  ((r==6)&&(sn.get(0)%weight==1))) {
				if (sn.get(0)%weight==0) {
					sn.set(0,sn.get(0)+weight);
				}else{
					sn.set(0,sn.get(0) -weight);
				}
			}else{
				sn.set(0, Math.abs((weight*height) - (Math.abs(sn.get(0)))));
			}
		}
	}
	void food(){//随机产生食物
		while(true){
			food = (int)(Math.random()*weight*height);
			if (food != 0  && !sn.contains(food)) {
				break;
			}
		}
	}
	void bigFood(){//随机产生big食物
		while(true){
			bigFood = (int)(Math.random()*weight*height);
			if (bigFood != 0  && !sn.contains(bigFood) && bigFood!=food) {
				break;
			}
		}
		on =(int)(Math.sqrt(weight*height));//开始计数
	}
	void bigFoodTime(){
		if (on != 0) {
		System.out.println("     剩余步伐：" + on);
		}else{
			System.out.println();
		}
	}
	public void screan(){//显示
		for(int i = 0;i <= weight*height+1;i++){
			if(i==0 || i==weight*height+1){
				for(int a = 1;a<=weight+2;a++){
					System.out.print("-");//上下边框
				}
				System.out.println();
			}else{

				if(i%weight==1){//左边框
					System.out.print("|");
				}

				if(i == food){
					System.out.print("*");
				}else{
					if (i == bigFood) {
						System.out.print("#");
					}else{
						if(sn.contains(i)){
							if(i == sn.get(0)){
								System.out.print("∅");
							}else{
									System.out.print("⊙");
							}
						}else{
						System.out.print(" ");
						}
					}
				}
				if(i%weight==0){//右边框
					System.out.println("|");
				}
			}
		}
		System.out.println();
	}
}
