package hgl;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public abstract class Tank extends GameObject{
	public int width=40;
	public int height=50;
	public int speed=4;
	//方向
	private Direction direction=Direction.UP;
	//生命
	public boolean alive=false;
	//定义四个方向上的图片
	private String upImg;
	private String downImg;
	private String leftImg;
	private String rightImg;
	boolean left,up,right,down;
	//攻击冷却状态
	private boolean attackCoolDown=true;
	//攻击冷却时间间隔(ms)
	private int attactCoolDownTime=1000;
	
	//tank的构造函数
	public Tank(String img,int x,int y,GamePanel gamepanel,
			String upImg,String downImg,String leftImg,String rightImg) {
		//调用父类
		super(img,x,y,gamepanel);
		
		this.upImg=upImg;
		this.downImg=downImg;
		this.leftImg=leftImg;
		this.rightImg=rightImg;
	}
	//键盘控制坦克移动
			public void leftward() {
				direction=Direction.LEFT;
				setImg(leftImg);
				if(!hitWall(x-speed,y)) {
					this.x-=speed;
				}
			}
			public void upward() {
				direction=Direction.UP;
				if(!hitWall(x,y-speed)) {
					this.y-=speed;
				}
				setImg(upImg);
			}
			public void rightward() {
				
				setImg(rightImg);
				direction=Direction.RIGHT;
				if(!hitWall(x+speed,y)) {
					this.x+=speed;
				}
			}
			public void downward() {
				
				setImg(downImg);
				direction=Direction.DOWN;
				if(!hitWall(x,y+speed)) {
					this.y+=speed;
				}
			}
			public void setImg(String img) {
				this.img=GameUtil.getImage(img);
			
			}
			
			public void attack() {
				if(attackCoolDown && alive) {	
				  Point p=this.getHeadPoint();
				  Bullet bullet=new Bullet("images/bulletU.gif",p.x,p.y,this.gamepanel,direction);
				  this.gamepanel.bulletList.add(bullet);
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
			//与围墙碰撞检测
			public boolean hitWall(int x, int y){
		        //假设玩家坦克前进，下一个位置形成的矩形
				ArrayList<Wall> walls = this.gamepanel.wallList;
		        //地图里所有的墙体
		        Rectangle next=new Rectangle(x,y,width,height);
		        //判断两个矩形是否相交（即是否撞墙）
		        for(Wall wall:walls){
		            if(next.intersects(wall.gerRec())){
		                return true;
		            }
		        }
		        return false;
		    }

	@Override
	public abstract void paintSelf(Graphics g);
	@Override
	public abstract Rectangle gerRec();

}