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
	
	//ָ��ͼƬ
	Image select =GameUtil.getImage("images/tankR.gif");
	//��Ϸģʽ ��0��Ϸδ��ʼ��1 ����ģʽ��2˫��ģʽ��3 ����ģʽ
	int y=150;
	int state=0;
	int a=1;
	//��Ϸ�ӵ��б�
	ArrayList<Bullet> bulletList=new ArrayList<Bullet>();
	//��Ӷ�ܵз�̹��
	ArrayList<Bot> botList=new ArrayList<Bot>();
	ArrayList<Bullet> removeList=new ArrayList<Bullet>();
	//�������б�
	ArrayList<Tank> playerList=new ArrayList<Tank>();
	//�½�Χǽ
	ArrayList<Wall> wallList=new ArrayList<Wall>();
	//�ػ�Ĵ���
	int count =0;
	//�����ɵ��˵�����
	int enemycount=0;
	//����˫����ͼƬ
	Image offScreenImage=null;
	//����з�̹��
	Bot bot = new Bot("images/BadTank1.png",500,110,this,
			"images/enemy1U.gif","images/enemy1D.gif",
			"images/enemy1L.gif","images/enemy1R.gif");
	//����������1 PlayerOne
	PlayerOne playerone = new PlayerOne("images/GoodTank1.png",200,510,this,
			"images/TankU.gif","images/TankD.gif",
			"images/TankL.gif","images/TankR.gif");
	public void launch() {
		setTitle("̹�˴�ս");
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		this.addKeyListener(new GamePanel.KeyMonitor());
		//���Χǽ
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
		//�ػ�ָ��
		while(true) {
			//��ӵз�̹��
			if(count % 100 == 1  && enemycount<10) {
				//ʹ�з�̹���������
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
		//����һ���ʹ���һ�����ͼƬ
		if(offScreenImage==null) {
			offScreenImage=this.createImage(width, height);
		}
		//��ȡ��ͼƬ�Ļ���
		Graphics gOff =offScreenImage.getGraphics();
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
		if (state==1) {
			gOff.drawString("����ģʽ", 100, 150);
		}else if (state==2){
			gOff.drawString("˫��ģʽ", 100, 200);
		}else{
			gOff.drawString("����ģʽ", 100, 250);
		}
		
		//�����ϷԪ��
		for(Tank player:playerList) {
		player.paintSelf(gOff);
		}
		//�����ӵ�
		for(Bullet bullet: bulletList) {
			bullet.paintSelf(gOff);
		}
		bulletList.removeAll(removeList);
		//���Ƶз�̹��
		for(Bot bot: botList) {
		bot.paintSelf(gOff);
		}
		//����Χǽ
				for(Wall wall:wallList) {
					wall.paintSelf(gOff);
				}
				
				
	}
		count++;
		//�����������ƺõ�ͼ���������Ƶ������Ļ�����
		g.drawImage(offScreenImage, 0, 0, null);
		
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
				playerList.add(playerone);
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
