package hgl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;



public class Blast extends GameObject {
      //±¬Õ¨Í¼¼¯
	 static Image[] imgs =new Image[10];
	 int explodeCount=0;
	 
	 static {
			for(int i=0;i<10;i++) {
				imgs[i]=GameUtil.getImage("images1/0"+(i+1)+".gif");
			}
		}
	 
	public Blast(String img, int x, int y, GamePanel gamepanel) {
		super(img, x, y, gamepanel);
		
	}

	@Override
	public void paintSelf(Graphics g) {
		if(explodeCount<=9) {
			g.drawImage(imgs[explodeCount],(int)x,(int)y,null);
			explodeCount++;
		}
		
	}

	@Override
	public Rectangle gerRec() {
		
		return null;
	}

}
