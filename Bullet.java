package giao;

import java.awt.*;

public class Bullet extends GameObject{
    //长宽
    private int width = 10;
    private int height = 10;
    //速度
    private int speed = 7;
    //方向
    Direction direction;
    //构造函数
    public Bullet(String img, int x, int y, Direction direction,GamePanel gamePanel) {
        super(img, x,  y, gamePanel);
        this.direction = direction;
    }

    public void go(){
        switch (direction){
            case UP:
                upward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
        }
    }

    public void leftward(){
            x -= speed;
    }
    public void rightward(){
            x += speed;
    }
    public void upward(){
            y -= speed;
    }
    public void downward(){
            y += speed;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
