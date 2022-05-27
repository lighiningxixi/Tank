package tankwar;

import java.awt.*;
import java.util.Random;

public class Bot extends Tank{
    int moveTime = 0;
    public Bot(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
    }

    public void go(){
        attack();
        if(moveTime>=20) {
            direction=randomDirection();
            moveTime=0;
        }else {
            moveTime+=1;
        }
        switch(direction) {
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
            case LEFT:
                leftward();
                break;
        }
    }

    //����̹���������
    public Direction randomDirection() {
        Random r = new Random();
        int rnum = r.nextInt(4);
        switch(rnum) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            default:
                return Direction.DOWN;
        }
    }

    //ֻ��2%���ʹ���
    public void attack() {
        Point p = getHeadPoint();
        Random r = new Random();
        int rnum =r.nextInt(100);
        //System.out.println("r: "+rnum);
        if(rnum<2) {
            System.out.println(rnum);
            EnemyBullet enemyBullet = new EnemyBullet("images/bullet/aixin.gif",p.x,p.y,direction,gamePanel);
            this.gamePanel.bulletList.add(enemyBullet);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
