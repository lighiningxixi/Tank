package giao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JFrame {

    /** ����˫����ͼƬ */
    Image offScreenImage = null;
    //��Ϸ״̬ 0 ��Ϸδ��ʼ�� 1 ����ģʽ�� 2 ˫��ģʽ�� 3 ��Ϸ��ͣ�� 4 ��Ϸʧ�ܣ� 5 ��Ϸʤ��
    int state= 0;
    //��Ϸ�Ƿ�ʼ
    private boolean start = false;
    //��ʱ����
    int a = 1;
    //���ڳ���
    int width = 800;
    int height = 610;
    //���弯��
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    //��Ϸָ��
    Image select = Toolkit.getDefaultToolkit().getImage("D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1\\p1tankU.gif");
    //ָ���ʼ�߶�
    int y = 150;
    //���
    private PlayerOne playerOne = new PlayerOne("D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1\\p1tankU.gif", 125, 510,
            "D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1/p1tankU.gif","D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1/p1tankD.gif",
            "D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1/p1tankL.gif","D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\player1/p1tankR.gif", this);

    //���ڵ���������
    public void launch(){
        //����
        setTitle("̹�˴�ս");
        //���ڳ�ʼ��С
        setSize(width, height);
        //ʹ��Ļ����
        setLocationRelativeTo(null);
        //��ӹر��¼�
        setDefaultCloseOperation(3);
        //�û����ܵ�����С
        setResizable(false);
        //ʹ���ڿɼ�
        setVisible(true);
        //��Ӽ����¼�
        this.addKeyListener(new GamePanel.KeyMonitor());

        while (true){
            repaint();
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        //����������һ����С��ImageͼƬ
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //��ø�ͼƬ�Ļ���
        Graphics gImage= offScreenImage.getGraphics();
        //���ñ�����ɫ
        gImage.setColor(Color.gray);
        //�����������
        gImage.fillRect(0, 0, width, height);
        //�ұ仭����ɫ
        gImage.setColor(Color.BLUE);
        //�ı����ִ�С����ʽ
        gImage.setFont(new Font("����",Font.BOLD,50));
        if(state == 0){
            //�������
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("������Ϸ",220,200);
            gImage.drawString("˫����Ϸ",220,300);
            gImage.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            //�������
            gImage.drawString("��Ϸ��ʼ",220,300);
            if(state == 1){
                gImage.drawString("����ģʽ",220,200);
            }
            else{
                gImage.drawString("˫��ģʽ",220,200);
            }
            //paint�ػ���ϷԪ��
            playerOne.paintSelf(gImage);
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
        }
        /** �����������ƺ�Ŷ��ͼ���������Ƶ������Ļ����� */
        g.drawImage(offScreenImage, 0, 0, null);
    }
    private class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    if(!start){
                        y = 150;
                        a = 1;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_2:
                    if(!start){
                        y = 250;
                        a = 2;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_ENTER:
                    tankList.add(playerOne);
                    state = a;
                    start = true;
                    //System.out.println("state:"+""+state);
                    break;
                default:
                    playerOne.keyPressed(e);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}