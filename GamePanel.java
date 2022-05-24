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
	
	//ָ��ͼƬ
	Image select =GameUtil.getImage("images/tankR.gif");
	//��Ϸģʽ ��0��Ϸδ��ʼ��1 ����ģʽ��2˫��ģʽ��3 ����ģʽ
	int y=150;
	int state=0;
	int a=1;
	//����˫����ͼƬ
	Image offScreemImage=null;
	//����������1 PlayerOne
	PlayerOne playerone = new PlayerOne("images/TankU.gif",200,510,this,
			"images/TankU.gif","images/TankL.gif",
			"images/TankD.gif","images/TankR.gif");
	
	public void launch() {
		setTitle("̹�˴�ս");
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		this.addKeyListener(new GamePanel.KeyMonitor());
		//�ػ�ָ��
		new PaintThread().start();
	}
		
	@Override
	public void paint(Graphics g) {
		//����һ���ʹ���һ�����ͼƬ
		if(offScreemImage==null) {
			offScreemImage=this.createImage(width, height);
		}
		//��ȡ��ͼƬ�Ļ���
		Graphics gOff =offScreemImage.getGraphics();
		gOff.setColor(Color.gray);
		gOff.fillRect(0, 0, width, height);
		gOff.setColor(Color.green);
		gOff.setFont(new Font("΢���ź�",Font.BOLD,50));
		//��Ϸδ��ʼ
		if(state==0) {
			gOff.drawString("ѡ����Ϸģʽ", 100, 100);
			gOff.drawString("����ģʽ", 100, 200);
			gOff.drawString("˫��ģʽ", 100, 300);
			gOff.drawString("����ģʽ", 100, 400);
		//���ָ��
			gOff.drawImage(select, 50, y, null);
	}
	else if (state==1||state==2||state==3) {
		gOff.drawString("��Ϸ��ʼ", 100, 100);
		if (state==1){
			gOff.drawString("����ģʽ", 100, 150);
		}else if (state==2){
			gOff.drawString("˫��ģʽ", 100, 200);
		}else{
			gOff.drawString("����ģʽ", 100, 250);
		}
		//�����ϷԪ��
		playerone.paintSelf(gOff);
	}
		//�����������ƺõ�ͼ���������Ƶ������Ļ�����
		g.drawImage(offScreemImage, 0, 0, null);
	}
	//�����ػ�����
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
		//���¼���
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
		//�ɿ�����
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
