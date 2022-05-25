package hgl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
public abstract class GameObject {
	public Image img;
	public int x;
	public int y;
	public GamePanel gamepanel;

   public GameObject(String img,int x,int y,GamePanel gamepanel) {
	this.img = GameUtil.getImage(img);
	this.x = x;
	this.y = y;
	this.gamepanel=gamepanel;
   }
  public abstract void paintSelf(Graphics g);
  //�����Լ����εķ������鿴�Ƿ�����ײ
  public abstract Rectangle gerRec();
}