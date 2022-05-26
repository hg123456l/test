package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;





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
		this.hitWallt();
		this.moveToBorder();
		this.hitBase();
	}
	
	public void hitBot() {
		ArrayList<Bot> bots=this.gamepanel.botList;
		for(Bot bot:bots) {
			if(this.gerRec().intersects(bot.gerRec())) {
				this.gamepanel.blastList.add(new Blast("",bot.x-34,bot.y-14,this.gamepanel));
				this.gamepanel.botList.remove(bot);
				this.gamepanel.removeList.add(this);
				break;
			}
			
		}
	}
	
	public void hitBase(){
		ArrayList<Base> baseList=this.gamepanel.baseList;
        for(Base base: baseList) {
            if (this.gerRec().intersects(base.gerRec())) {
                this.gamepanel.baseList.remove(base);
                this.gamepanel.removeList.add(this);
                break;
            }
        }
    }

	public void hitWallt() {
		ArrayList<Wall> walls=this.gamepanel.wallList;
		for(Wall wall:walls) {
			if(this.gerRec().intersects(wall.gerRec())) {
				this.gamepanel.wallList.remove(wall);
				this.gamepanel.removeList.add(this);
				break;
			}
			
		}
	}
	
	public void moveToBorder(){
        if (x < 0||x > this.gamepanel.getWidth()) {
            this.gamepanel.removeList.add(this);
        }
        if(y < 0||y > this.gamepanel.getHeight()){
            this.gamepanel.removeList.add(this);
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
		this.hitBot();
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
