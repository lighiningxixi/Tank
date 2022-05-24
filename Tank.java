package ck;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tank extends GameObject{
	//尺寸
	public int width = 40;
	public int height = 50;
    //速度
	private int speed = 3;
	//方向
	private Direction direction = Direction.UP;
	//四个方向图片
	private String upImage; 
    private String downImage;
    private String rightImage;
    private String leftImage;
    
    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
    	super(img, x, y, gamePanel);
        this.upImage = upImage;
        this.leftImage = leftImage;
        this.downImage = downImage;
        this.rightImage = rightImage;
    }
    @Override
    public abstract void paintSelf(Graphics g);
    @Override
    public abstract Rectangle getRec();

}
