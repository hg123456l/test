package hgl;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Tank extends GameObject{
	public int width=40;
	public int height=50;
	public int sleep=4;
	//����
	private Direction direction=Direction.UP;
	//�����ĸ������ϵ�ͼƬ
	private String upImg;
	private String downImg;
	private String leftImg;
	private String rightImg;
	boolean left,up,right,down;
	//������ȴ״̬
	private boolean attackCoolDown=true;
	//������ȴʱ����(ms)
	private int attactCoolDownTime=1000;
	
	//tank�Ĺ��캯��
	public Tank(String img,int x,int y,GamePanel gamepanel,
			String upImg,String downImg,String leftImg,String rightImg) {
		//���ø���
		super(img,x,y,gamepanel);
		
		this.upImg=upImg;
		this.downImg=downImg;
		this.leftImg=leftImg;
		this.rightImg=rightImg;
	}
	//���̿���̹���ƶ�
			public void leftward() {
				x -= sleep;
				setImg(leftImg);
				direction=Direction.LEFT;
			}
			public void upward() {
				y -=sleep;
				setImg(upImg);
				direction=Direction.UP;
			}
			public void rightward() {
				x +=sleep;
				setImg(rightImg);
				direction=Direction.RIGHT;
			}
			public void downward() {
				y +=sleep;
				setImg(downImg);
				direction=Direction.DOWN;
			}
			public void setImg(String img) {
				this.img=GameUtil.getImage(img);
			
			}
			
			public void attack() {
				if(attackCoolDown) {	
				  Point p=this.getHeadPoint();
				  Bullet bullet=new Bullet("images/bulletU.gif",p.x,p.y,this.gamepanel,direction);
				  this.gamepanel.bulletList.add(bullet);
				  new AttackCD().start();
				}
			}
			//�½��߳�
			 class AttackCD extends Thread{
				 @Override
				 public void run() {
					//�������������ó���ȴ״̬
					attackCoolDown=false;
					//����һ��
					try {
						Thread.sleep(attactCoolDownTime);
					}catch(Exception e) {
						e.printStackTrace();
					}
					//���������ܽ����ȴ״̬
					attackCoolDown=true;
					//��ֹ�߳�
					this.stop();
				}
			}
			
			public Point getHeadPoint() {
				switch(direction){
				case LEFT:
					return new Point(x,y+height/2);
				case RIGHT:
					return new Point(x+width,y+height/2);
				case UP:
					return new Point(x+width/2,y);
				case DOWN:
					return new Point(x+width/2,y+height);
				default:
						return null;
				}
			}

	@Override
	public abstract void paintSelf(Graphics g);
	@Override
	public abstract Rectangle gerRec();

}