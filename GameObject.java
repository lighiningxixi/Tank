package giao;

import java.awt.*;

public abstract class GameObject {

    //��ϷԪ��ͼƬ
    Image img;
    //��ϷԪ�صĺ�����
    int x;
    //��ϷԪ�ص�������
    int y;
    //��ϷԪ�صĿ�
    int width;
    //��ϷԪ�صĸ�
    int height;
    //��ϷԪ�ص��ƶ��ٶ�
    int speed;
    //��ϷԪ�ص��ƶ�����
    Direction direction;
    //����������
    GamePanel gamePanel;

    public GameObject(){}
    public GameObject(String img, int x, int y, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamepanel) {
        this.gamePanel = gamePanel;
    }

    //�̳�Ԫ�ػ����Լ��ķ���
    public abstract void paintSelf(Graphics g);

    //��ȡ��ǰ��ϷԪ�صľ���,��Ϊ��ײ����д
    public abstract Rectangle getRec();
}
