package hgl;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public  class PlayerTwo extends Tank{
	
	//playerTwo�Ĺ��캯��
		public PlayerTwo(String img,int x,int y,GamePanel gamepanel,
				String upImg,String downImg,String leftImg,String rightImg) {
			//���ø���
			super(img,x,y,gamepanel,upImg,downImg,leftImg,rightImg);
		}
		
		//��Ӽ����¼�
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT:
				left=true;
				break;
			case KeyEvent.VK_DOWN:
				down=true;
				break;
			case KeyEvent.VK_RIGHT:
				right=true;
				break;
			case KeyEvent.VK_UP:
				up=true;
				break;
			case KeyEvent.VK_K:
				attack();
				default:
					break;
			}
		}
		
		public void keyReleased(KeyEvent e) {
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT:
				left=false;
				break;
			case KeyEvent.VK_DOWN:
				down=false;
				break;
			case KeyEvent.VK_RIGHT:
				right=false;
				break;
			case KeyEvent.VK_UP:
				up=false;
				break;
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
