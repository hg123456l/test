package hgl;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Tank{
	
	int moveTime=0;
	Direction direction;
	public Bot(String img, int x, int y, GamePanel gamepanel, String upImg, String downImg, String leftImg,
			String rightImg) {
		super(img, x, y, gamepanel, upImg, downImg, leftImg, rightImg);
		this.direction=direction;
	}
	public Direction getRandomDirection() {
		Random random=new Random();
		int rnum=random.nextInt(4);
		switch(rnum) {
		case 0:
			return Direction.LEFT;
		case 1:
			return Direction.RIGHT;
		case 2:
			return Direction.DOWN;
		case 3:
			return Direction.UP;
			default:
				return null;
		}
	}
	//新建一个移动方法
	public void go() {
		attack();
		
		if(moveTime>=20) {
			direction=getRandomDirection();
			moveTime=0;
		}else {
			moveTime++;
		}
		switch(direction){
		case LEFT:
			leftward();
			break;
		case UP:
			upward();
			break;
		case RIGHT:
			rightward() ;
			break;
		case DOWN:
			downward();
			break;
	     }
	}
	public void attack() {
		 Point p=this.getHeadPoint();
		 Random random=new Random();
		 int rnum=random.nextInt(400);
		 if(rnum<4) {
			 this.gamepanel.bulletList.add(new EnemyBullet("images/bulletYellow.gif",p.x,p.y,this.gamepanel,direction));
		 }
	}
	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
		go();
	}

	@Override
	public Rectangle gerRec() {
		return new Rectangle(x,y,width,height);
	}

}
