package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {
	
	int length=50;

	public Wall(String img, int x, int y, GamePanel gamepanel) {
		super(img, x, y, gamepanel);
		
	}

	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, gamepanel);
		
	}

	@Override
	public Rectangle gerRec() {
		
		return new Rectangle(x,y,length,length);
	}
	

}
