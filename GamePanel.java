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
    //双缓冲图片
    Image offScreenImage = null;
    //图片
    private Image select = Toolkit.getDefaultToolkit().getImage("D:\\Java\\workspace\\tankwar\\bin\\images/selecttank.gif");
    //指针初始坐标
    private int y = 150;
    //游戏模式
    int state = 0;
    int a = 1;
    PlayerOne playerOne = new PlayerOne("D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankU.gif", 125, 510,
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankU.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankD.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankL.gif",
            "D:\\\\Java\\\\workspace\\\\tankwar\\\\bin\\\\images/player1/p1tankR.gif",this);
	public boolean start;
    
    //窗口启动方法
    public void launch(){                 
    	setTitle("战争燃起");
    	setSize(width, height);
    	//窗口居中
    	setLocationRelativeTo(null);
    	//添加关闭事件
    	setDefaultCloseOperation(3);
    	//用户不能调节大小
    	setResizable(false);
    	//窗口可见
    	setVisible(true);
    	//添加键盘监视器
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
    
    
    
  //paint方法
    @Override
    public void paint(Graphics g) {      
    	if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }//空白图片
    	Graphics gImage= offScreenImage.getGraphics();
    	//画笔颜色
        //画笔颜色
    	gImage.setColor(Color.PINK);
        //实心矩形
    	gImage.fillRect(0, 0, width, height);
    	gImage.setColor(Color.white);
        if(state == 0) {
        gImage.setFont(new Font("宋体",Font.BOLD,50));
        gImage.drawString("选择游戏模式",220,100);
        gImage.drawString("单人模式",220,200);
        gImage.drawString("双人模式",220,300);
        //绘制指针
        gImage.drawImage(select,160,y,null);
    }
    else if(state == 1||state == 2){      
    	gImage.drawString("游戏开始",220,300);
        if(state == 1){
        	gImage.drawString("单人模式",220,200);
        }
        else{
        	gImage.drawString("双人模式",220,200);
        }
        //添加游戏元素
        playerOne.paintSelf(gImage);
    }
        g.drawImage(offScreenImage, 0, 0, null);
}
//键盘监视器
class KeyMonitor extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        //返回键值
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