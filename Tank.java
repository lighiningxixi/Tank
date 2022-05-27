package tankwar;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class Tank extends GameObject{

    private boolean attackCoolDown =true;//������ȴ״̬
    private int attackCoolDownTime =1000;//������ȴʱ�������1000ms�����ӵ�
    private String upImage; //�����ƶ�ʱ��ͼƬ
    private String downImage;//�����ƶ�ʱ��ͼƬ
    private String rightImage;//�����ƶ�ʱ��ͼƬ
    private String leftImage;//�����ƶ�ʱ��ͼƬ
    boolean alive = true;
    //̹��size
    int width = 40;
    int height = 50;
    //̹�˳�ʼ����
    Direction direction = Direction.UP;
    //̹���ٶ�
    private int speed = 3;
    //̹��ͷ������
    Point p;

    //̹�����꣬����ͼƬ���������
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
        if(!hitWall(x-speed, y) &&!hitWall1(x-speed, y) &&!hitRiver(x-speed, y) && !moveToBorder(x-speed, y) && alive){
            this.x -= speed;
        }
    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        if(!hitWall(x+speed, y) &&!hitWall1(x+speed, y) &&!hitRiver(x+speed, y) && !moveToBorder(x+speed, y) && alive){
            this.x += speed;
        }
    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        if(!hitWall(x, y-speed) &&!hitWall1(x, y-speed) &&!hitRiver(x, y-speed) && !moveToBorder(x, y- speed) && alive){
            this.y -= speed;
        }
    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
        if(!hitWall(x, y+speed) &&!hitWall1(x, y+speed) &&!hitRiver(x, y+speed) && !moveToBorder(x, y+speed) && alive){
            this.y += speed;
        }
    }
 
    public void attack(){
        Point p = getHeadPoint();
        if(attackCoolDown && alive){
            Bullet bullet = new Bullet("images/bullet/yt.png",p.x,p.y,direction, this.gamePanel);
            this.gamePanel.bulletList.add(bullet);
            attackCoolDown = false;
            new AttackCD().start();
        }
    }

    public boolean hitWall(int x, int y){
        //�������̹��ǰ������һ��λ���γɵľ���
        Rectangle next = new Rectangle(x, y, width, height);
        //��ͼ�����е�ǽ��
        List<Wall> walls = this.gamePanel.wallList;
        //�ж����������Ƿ��ཻ�����Ƿ�ײǽ��
        for(Wall w:walls){
            if(w.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }
    public boolean hitWall1(int x, int y){
        //�������̹��ǰ������һ��λ���γɵľ���
        Rectangle next = new Rectangle(x, y, width, height);
        //��ͼ�����е�ǽ��
        List<Wall1> wall1s = this.gamePanel.wall1List;
        //�ж����������Ƿ��ཻ�����Ƿ�ײǽ��
        for(Wall1 w:wall1s){
            if(w.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }
    public boolean hitRiver(int x, int y){
        //�������̹��ǰ������һ��λ���γɵľ���
        Rectangle next = new Rectangle(x, y, width, height);
        //��ͼ�����е�ǽ��
        List<River> rivers = this.gamePanel.riverList;
        //�ж����������Ƿ��ཻ�����Ƿ�ײǽ��
        for(River r:rivers){
            if(r.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }


    public boolean moveToBorder(int x, int y){
        if(x < 0){
            return true;
        }else if(x > this.gamePanel.getWidth()-width){
            return true;
        }
        if(y < 0){
            return true;
        }else if(y > this.gamePanel.getHeight()-height){
            return true;
        }
        return false;
    }

    public class AttackCD extends Thread{
        @SuppressWarnings("deprecation")
		public void run(){
            attackCoolDown=false;//��������������Ϊ��ȴ״̬
            try{
                Thread.sleep(attackCoolDownTime);//����1��
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            attackCoolDown=true;//���������ܽ����ȴ״̬
            this.stop();
        }
    }

    //���ݷ���ȷ��ͷ��λ�ã�x��y�����½ǵĵ�
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
