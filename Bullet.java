package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
		int width=10;
	int height=10;
	int sleep=8;
	Direction direction;
	
	public Bullet(String img, int x, int y, GamePanel gamepanel,Direction direction) {
		super(img, x, y, gamepanel);
		this.direction=direction;
	}
	
	
	public void leftward() {
		x -= sleep;
	}
	public void upward() {
		y -=sleep;
	}
	public void rightward() {
		x +=sleep;
	}
	public void downward() {
		y +=sleep;
	}
	
	public void go() {
		switch(direction) {
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
	
	public void setImg(String img) {
		this.img=GameUtil.getImage(img);
	
	}

	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		this.go();
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
