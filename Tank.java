package giao;

import java.awt.*;

public class Tank extends GameObject{

    private String upImage; //�����ƶ�ʱ��ͼƬ
    private String downImage;//�����ƶ�ʱ��ͼƬ
    private String rightImage;//�����ƶ�ʱ��ͼƬ
    private String leftImage;//�����ƶ�ʱ��ͼƬ
    //̹��size
    int width = 40;
    int height = 50;
    //̹�˳�ʼ����
    Direction direction = Direction.UP;
    //̹���ٶ�
    private int speed = 3;

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

    public void attack(){
        Point p = getHeadPoint();
        Bullet bullet = new Bullet("D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\bullet\\BulletGreen.gif",p.x,p.y,direction, this.gamePanel);
        this.gamePanel.bulletList.add(bullet);
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
