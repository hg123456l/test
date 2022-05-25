package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public  class PlayerOne extends Tank{
	
	//playerone的构造函数
		public PlayerOne(String img,int x,int y,GamePanel gamepanel,
				String upImg,String downImg,String leftImg,String rightImg) {
			//调用父类
			super(img,x,y,gamepanel,upImg,downImg,leftImg,rightImg);
		}
		
		//添加键盘事件
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_A:
				left=true;
				break;
			case KeyEvent.VK_S:
				down=true;
				break;
			case KeyEvent.VK_D:
				right=true;
				break;
			case KeyEvent.VK_W:
				up=true;
				break;
				default:
					break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_A:
				left=false;
				break;
			case KeyEvent.VK_S:
				down=false;
				break;
			case KeyEvent.VK_D:
				right=false;
				break;
			case KeyEvent.VK_W:
				up=false;
				break;
			case KeyEvent.VK_SPACE:
				attack();
				default:
					break;
			}
		}
		
		public void move() {
			if(left) {
				leftward();
			}else if(right) {
				rightward();
			}else if(up) {
				upward();
			}else if(down) {
				downward();
			}
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public  void paintSelf(Graphics g) {
			g.drawImage(img, x, y, null);
			move();
		}
		
		@Override
		public  Rectangle gerRec() {
			return new Rectangle(x,y,width,height);
		}

}
