package hgl;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
	//游戏模式 ，0游戏未开始，1 单人模式，2双人模式，3 多人模式,4游戏暂停， 5 游戏失败，6游戏成功
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
	//添加基地列表
	ArrayList<Base> baseList=new ArrayList<Base>();
	//添加爆炸特效
	ArrayList<Blast> blastList=new ArrayList<Blast>();
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
	PlayerOne playerone = new PlayerOne("images/GoodTank1.png",125,510,this,
			"images/TankU.gif","images/TankD.gif",
			"images/TankL.gif","images/TankR.gif");
	//定义变量玩家2 
	PlayerTwo playertwo = new PlayerTwo("images2/GoodTank2.png",625,510,this,
				"images2/p2tankU.gif","images2/p2tankD.gif",
				"images2/p2tankL.gif","images2/p2tankR.gif");
	//背景图片
    public Image background = GameUtil.getImage("images/back.png");
	//添加基地图片
	Base base = new Base("images/star.gif", 375, 570, this);
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
			wallList.add(new Wall("images/walls.gif",i*60,240,this));
		}
		wallList.add(new Wall("images/walls.gif",305,560,this));
		wallList.add(new Wall("images/walls.gif",305,500,this));
		wallList.add(new Wall("images/walls.gif",365,500,this));
		wallList.add(new Wall("images/walls.gif",425,500,this));
		wallList.add(new Wall("images/walls.gif",425,560,this));
		//添加基地
		 baseList.add(base);
		//重绘指针
		while(true) {
			//游戏胜利
			if(botList.size() == 0 && enemycount == 10){
                state = 6;
            }
			//游戏失败规定
            if((playerList.size() == 0 && (state == 1 || state == 2||state == 3))||baseList.size()==0){
                state = 5;
            }
			//添加敌方坦克
			if(count % 100 == 1  && enemycount<10 && state == 1 || state == 2||state == 3) {
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
		gOff.setFont(new Font("微软雅黑",Font.BOLD,45));
		//游戏未开始
		if(state==0) {
			gOff.drawString("选择游戏模式", 100, 100);
			gOff.drawString("单人模式", 100, 200);
			gOff.drawString("双人模式", 100, 300);
			gOff.drawString("多人模式", 100, 400);
			gOff.drawString("按1，2，3选择模式，按回车开始游戏",10,500);
		//添加指针
			gOff.drawImage(select, 50, y, null);
	}
	else if (state==1||state==2||state==3) {
		gOff.setFont(new Font("黑体",Font.BOLD,20));
		gOff.setColor(Color.pink);
		gOff.drawString("剩余敌人："+botList.size(), 5,50);
		
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
		//绘制基地
				for(Base base:baseList) {
					base.paintSelf(gOff);
				}
				count++;
		//绘制blast的元素
				for(Blast blast:blastList) {
					blast.paintSelf(gOff);
				}
				
	}
	else if(state == 4){
		gOff.drawString("游戏暂停",220,200);
    }
    else if(state == 5){
    	gOff.drawString("游戏失败",220,200);
    }
    else if(state == 6){
    	gOff.drawString("游戏胜利",220,200);
    }
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
        //添加游戏玩家 
				playerList.add(playerone);
				if(state==2) {
					playerList.add(playertwo);
					playertwo.alive=true;
				}
				playerone.alive=true;
				break;
			case KeyEvent.VK_P:
                if(state != 4){
                    a = state;
                    state = 4;
                }
                else {
                	state = a;
                    if(a == 0) {
                    	a = 1;
                    }
                }
                break;
				default:
					playerone.keyPressed(e);
					playertwo.keyReleased(e);
			}
			
		}
		//松开键盘
		@Override
		public void keyReleased(KeyEvent e) {
			playerone.keyReleased(e);
			playertwo.keyReleased(e);
		}
	}
	
	public static void main(String args[]) {
		GamePanel gp=new GamePanel();
		gp.launch();
	}

}
