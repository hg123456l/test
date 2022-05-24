package com.hgl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	//定义双缓存图片
	Image offScreemImage=null;
	//定义变量玩家1 PlayerOne
	PlayerOne playerone = new PlayerOne("images/TankU.gif",200,510,this,
			"images/TankU.gif","images/TankL.gif",
			"images/TankD.gif","images/TankR.gif");
	
	public void launch() {
		setTitle("坦克大战");
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		this.addKeyListener(new GamePanel.KeyMonitor());
		//重绘指针
		new PaintThread().start();
	}
		
	@Override
	public void paint(Graphics g) {
		//创建一个和窗口一样大的图片
		if(offScreemImage==null) {
			offScreemImage=this.createImage(width, height);
		}
		//获取该图片的画笔
		Graphics gOff =offScreemImage.getGraphics();
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
		if (state==1){
			gOff.drawString("单人模式", 100, 150);
		}else if (state==2){
			gOff.drawString("双人模式", 100, 200);
		}else{
			gOff.drawString("多人模式", 100, 250);
		}
		//添加游戏元素
		playerone.paintSelf(gOff);
	}
		//将缓冲区绘制好的图形整个绘制到容器的画布中
		g.drawImage(offScreemImage, 0, 0, null);
	}
	//反复重画窗口
	class PaintThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(25);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
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
