package hgl;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class GamePanel extends JFrame {
	
	int width=800;
	int height=610;
	
	//指针图片
	Image select =GameUtil.getImage("images/tankR.gif");
	//游戏模式 ，0游戏未开始，1 单人模式，2双人模式，3 多人模式
	int y=150;
	int state=0;
	int a=1;
	//游戏子弹列表
	ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
	//添加多架敌方坦克
	ArrayList<Bot> botList=new ArrayList<Bot>();
	ArrayList<Bullet> removeList=new ArrayList<Bullet>();
	//添加玩家列表
	ArrayList<Tank> playerList=new ArrayList<Tank>();
	//新建围墙
	ArrayList<Wall> wallList=new ArrayList<Wall>();
	//重绘的次数
	int count =0;
	//已生成敌人的数量
	int enemycount=0;
	//定义双缓存图片
	Image offScreenImage=null;
	//定义敌方坦克
	Bot bot = new Bot("images/BadTank1.png",500,110,this,
			"images/enemy1U.gif","images/enemy1D.gif",
			"images/enemy1L.gif","images/enemy1R.gif");
	//定义变量玩家1 PlayerOne
	PlayerOne playerone = new PlayerOne("images/GoodTank1.png",200,510,this,
			"images/TankU.gif","images/TankD.gif",
			"images/TankL.gif","images/TankR.gif");
	public void launch() {
		setTitle("坦克大战");
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		this.addKeyListener(new GamePanel.KeyMonitor());
		//添加围墙
		for(int i=0;i<14;i++) {
			wallList.add(new Wall("images/walls.gif",i*60,170,this));
		}
		wallList.add(new Wall("images/square1.jpg",305,560,this));
		wallList.add(new Wall("images/square2.jpg",305,530,this));
		wallList.add(new Wall("images/square1.jpg",305,500,this));
		wallList.add(new Wall("images/square2.jpg",335,500,this));
		wallList.add(new Wall("images/square1.jpg",365,500,this));
		wallList.add(new Wall("images/square3.jpg",395,500,this));
		wallList.add(new Wall("images/square1.jpg",425,500,this));
		wallList.add(new Wall("images/square3.jpg",425,530,this));
		wallList.add(new Wall("images/square1.jpg",425,560,this));
		//重绘指针
		while(true) {
			//添加敌方坦克
			if(count % 100 == 1  && enemycount<10) {
				//使敌方坦克随机生成
				Random random=new Random();
				int rnum=random.nextInt(800);
			botList.add(new Bot("images/BadTank1.png",rnum,110,this,
					"images/enemy1U.gif","images/enemy1D.gif",
					"images/enemy1L.gif","images/enemy1R.gif"));
			enemycount ++;
			}
			repaint();
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		
	@Override
	public void paint(Graphics g) {
		//创建一个和窗口一样大的图片
		if(offScreenImage==null) {
			offScreenImage=this.createImage(width, height);
		}
		//获取该图片的画笔
		Graphics gOff =offScreenImage.getGraphics();
		gOff.setColor(Color.gray);
		gOff.fillRect(0, 0, width, height);
		gOff.setColor(Color.green);
		gOff.setFont(new Font("微软雅黑",Font.BOLD,50));
		//游戏未开始
		if(state==0) {
			gOff.drawString("选择游戏模式", 100, 100);
			gOff.drawString("单人模式", 100, 200);
			gOff.drawString("双人模式", 100, 300);
			gOff.drawString("多人模式", 100, 400);
		//添加指针
			gOff.drawImage(select, 50, y, null);
	}
	else if (state==1||state==2||state==3) {
		gOff.drawString("游戏开始", 100, 100);
		if (state==1) {
			gOff.drawString("单人模式", 100, 150);
		}else if (state==2){
			gOff.drawString("双人模式", 100, 200);
		}else{
			gOff.drawString("多人模式", 100, 250);
		}
		
		//添加游戏元素
		for(Tank player:playerList) {
		player.paintSelf(gOff);
		}
		//绘制子弹
		for(Bullet bullet: bulletList) {
			bullet.paintSelf(gOff);
		}
		bulletList.removeAll(removeList);
		//绘制敌方坦克
		for(Bot bot: botList) {
		bot.paintSelf(gOff);
		}
		//绘制围墙
				for(Wall wall:wallList) {
					wall.paintSelf(gOff);
				}
				
				
	}
		count++;
		//将缓冲区绘制好的图形整个绘制到容器的画布中
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	
	class KeyMonitor extends KeyAdapter {
		//按下键盘
		@Override
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_1:
				a=1;
				y=150;
				break;
			case KeyEvent.VK_2:
				a=2;
				y=250;
				break;
			case KeyEvent.VK_3:
				a=3;
				y=350;
				break;
			case KeyEvent.VK_ENTER:
				state=a;
				playerList.add(playerone);
				break;
				default:
					playerone.keyPressed(e);
			}
			
		}
		//松开键盘
		@Override
		public void keyReleased(KeyEvent e) {
			playerone.keyReleased(e);
		}
	}
	
	public static void main(String args[]) {
		GamePanel gp=new GamePanel();
		gp.launch();
	}

}
