package com.hgl;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tank extends GameObject{
	public int width=40;
	public int height=50;
	public int sleep=4;
	//方向
	private Direction direction=Direction.UP;
	//定义四个方向上的图片
	private String upImg;
	private String downImg;
	private String leftImg;
	private String rightImg;
	boolean left,up,right,down;
	
	//tank的构造函数
	public Tank(String img,int x,int y,GamePanel gamepanel,
			String upImg,String downImg,String leftImg,String rightImg) {
		//调用父类
		super(img,x,y,gamepanel);
		
		this.upImg=upImg;
		this.downImg=downImg;
		this.leftImg=leftImg;
		this.rightImg=rightImg;
	}
	//键盘控制坦克移动
			public void leftward() {
				x -= sleep;
				setImg(leftImg);
				direction=Direction.LEFT;
			}
			public void upward() {
				y -=sleep;
				setImg(upImg);
				direction=Direction.UP;
			}
			public void rightward() {
				x +=sleep;
				setImg(rightImg);
				direction=Direction.RIGHT;
			}
			public void downward() {
				y +=sleep;
				setImg(downImg);
				direction=Direction.DOWN;
			}
			public void setImg(String img) {
				this.img=GameUtil.getImage(img);
			
			}

	@Override
	public abstract void paintSelf(Graphics g);
	@Override
	public abstract Rectangle gerRec();

}
