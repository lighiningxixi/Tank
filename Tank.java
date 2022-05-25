package ck;

import java.awt.*;

public class Tank extends GameObject{

    //方向图片
    private String upImage; 
    private String downImage;
    private String rightImage; 
    private String leftImage;
    //尺寸
    int width = 40;
    int height = 50;
    //方向
    Direction direction = Direction.UP;
    //速度
    private int speed = 3;

    
    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.upImage = upImage;
        this.leftImage = leftImage;
        this.downImage = downImage;
        this.rightImage = rightImage;
    }

    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImage);
        this.x -= speed;

    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        this.x += speed;

    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        this.y -= speed;

    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
         this.y += speed;

    }
    //子弹设置
    public void attack(){
        Point p = this.getHeadPoint();
        Bullet bullet = new Bullet("D:\\Java/workspace/tankwar/bin/images/bullet/bulletGreen.gif",p.x,p.y,direction, this.gamePanel);
        this.gamePanel.bulletList.add(bullet);
        //线程开始
        
    }
    	
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x + width/2, y );
            case LEFT:
                return new Point(x, y + height/2);
            case DOWN:
                return new Point(x + width/2, y + height);
            case RIGHT:
                return new Point(x + width, y + height/2);
            default:
                return null;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}