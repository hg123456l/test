package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EnemyBullet extends Bullet{

	public EnemyBullet(String img, int x, int y, GamePanel gamepanel, Direction direction) {
		super(img, x, y, gamepanel, direction);
		// TODO Auto-generated constructor stub
	}
	
	public void hitPlayer() {
		ArrayList<Tank> players=this.gamepanel.playerList;
		for(Tank player:players) {
			if(this.gerRec().intersects(player.gerRec())) {
				this.gamepanel.blastList.add(new Blast("",player.x-34,player.y-14,this.gamepanel));
				this.gamepanel.playerList.remove(player);
				this.gamepanel.removeList.add(this);
				player.alive=false;
				break;
			}
			
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
		this.hitPlayer();
	}

	@Override
	public Rectangle gerRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
}
