package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bot extends Tank{

	public Bot(String img, int x, int y, GamePanel gamepanel, String upImg, String downImg, String leftImg,
			String rightImg) {
		super(img, x, y, gamepanel, upImg, downImg, leftImg, rightImg);
		
	}

	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
		
	}

	@Override
	public Rectangle gerRec() {
		return new Rectangle(x,y,width,height);
	}

}
