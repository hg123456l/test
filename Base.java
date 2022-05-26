package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Base extends GameObject{
	 int length=50;
	public Base(String img, int x, int y, GamePanel gamepanel) {
		super(img, x, y, gamepanel);
		
	}

	@Override
	public void paintSelf(Graphics g) {
		 g.drawImage(img, x, y, null);
		
	}

	@Override
	public Rectangle gerRec() {
		
		return new Rectangle(x, y, length, length);
	}
     
}
