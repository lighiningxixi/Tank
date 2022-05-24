package ck;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class GamePanel extends JFrame {
	
	int width = 800;
    int height = 610;
    //˫����ͼƬ
    Image offScreenImage = null;
    //ͼƬ
    private Image select = Toolkit.getDefaultToolkit().getImage("D:\\Java\\workspace\\tankwar\\bin\\images/selecttank.gif");
    //ָ���ʼ����
    private int y = 150;
    //��Ϸģʽ
    int state = 0;
    int a = 1;
    PlayerOne playerOne = new PlayerOne("D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankU.gif", 125, 510,
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankU.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankD.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankL.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankR.gif",this);
	public boolean start;
    
    //������������
    public void launch(){                 
    	setTitle("ս��ȼ��");
    	setSize(width, height);
    	//���ھ���
    	setLocationRelativeTo(null);
    	//��ӹر��¼�
    	setDefaultCloseOperation(3);
    	//�û����ܵ��ڴ�С
    	setResizable(false);
    	//���ڿɼ�
    	setVisible(true);
    	//��Ӽ��̼�����
    	this.addKeyListener(new GamePanel.KeyMonitor());
    	while (true){
            repaint();
            try {
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    
    
    
  //paint����
    @Override
    public void paint(Graphics g) {      
    	if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }//�հ�ͼƬ
    	Graphics gImage= offScreenImage.getGraphics();
    	//������ɫ
        //������ɫ
    	gImage.setColor(Color.PINK);
        //ʵ�ľ���
    	gImage.fillRect(0, 0, width, height);
    	gImage.setColor(Color.white);
        if(state == 0) {
        gImage.setFont(new Font("����",Font.BOLD,50));
        gImage.drawString("ѡ����Ϸģʽ",220,100);
        gImage.drawString("����ģʽ",220,200);
        gImage.drawString("˫��ģʽ",220,300);
        //����ָ��
        gImage.drawImage(select,160,y,null);
    }
    else if(state == 1||state == 2){      
    	gImage.drawString("��Ϸ��ʼ",220,300);
        if(state == 1){
        	gImage.drawString("����ģʽ",220,200);
        }
        else{
        	gImage.drawString("˫��ģʽ",220,200);
        }
        //�����ϷԪ��
        playerOne.paintSelf(gImage);
    }
        g.drawImage(offScreenImage, 0, 0, null);
}
//���̼�����
class KeyMonitor extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        //���ؼ�ֵ
        int key = e.getKeyCode();
        
		switch (key){
            case KeyEvent.VK_1:
            	
            	    a = 1;
                    y = 150;
            	
      
                System.out.println(state);
                break;
            case KeyEvent.VK_2:
            	
            	    a = 2;
                    y = 250;
            	
                System.out.println(state);
                break;
            case KeyEvent.VK_ENTER:
            	state = a;
                start = true;
                System.out.println("state:"+""+state);
                break;
            default:
                break;
        }
    }
}
 
    public static void main(String[] args) {
    	 GamePanel gamePanel = new GamePanel();
         gamePanel.launch();
      }
    }