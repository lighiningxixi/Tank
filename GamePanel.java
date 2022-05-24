package com.sxt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamePanel extends JFrame {
	Image offScreenImage = null;

    /**
	 * 
	 */
	 static final long serialVersionUID = -9031056538267314846L;
	
    int width = 800;
    int height = 610;
     PlayerOne playerOne = new PlayerOne("images/player1/p1tankU.gif", 125, 510,
            "images/player1/p1tankU.gif","images/player1/p1tankD.gif",
            "images/player1/p1tankL.gif","images/player1/p1tankR.gif", this);
    Image select = Toolkit.getDefaultToolkit().getImage("D:\\BaiduNetdiskDownload\\tank\\tank\\images\\images\\selecttank.gif");
    
    int y = 150;
    
    int state = 0;
    int a = 1;
    
    public void launch(){
       
        setTitle("坦克大战");
        
        setSize(width, height);
       
        setLocationRelativeTo(null);
       
        setDefaultCloseOperation(3);
        
        setResizable(false);
        
        setVisible(true);
        
        this.addKeyListener(new GamePanel.KeyMonitor());
        
        while (true){
            repaint();
            try {
                
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        } 
    }
    @Override
    public void paint(Graphics g) {
    	
    	Image offScreenImage = null;
		if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
            Graphics gImage= offScreenImage.getGraphics();
     
            g.setColor(Color.gray);
       
            g.fillRect(0, 0, width, height);
        
            g.setColor(Color.BLUE);
        
           g.setFont(new Font("仿宋",Font.BOLD,50));
        
        if(state == 0) {
        
        	g.drawString("选择游戏模式",220,100);
        	g.drawString("单人模式",220,200);
        	g.drawString("双人模式",220,300);
        	g.drawString("1 2选择模式，enter进入游戏",0,400);
        	g.drawImage(select,160,y,null);
    }
        
        else if(state == 1||state == 2){
            
        	g.drawString("游戏开始",220,300);
            if(state == 1){
            	g.drawString("单人模式",220,200);
            }
            else{
            	g.drawString("双人模式",220,200);
            	
            }
            }
        }
    }
    
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:                 
                        
                        a = 1;
                        y = 150;
                    break;
                case KeyEvent.VK_2:
                	
                	a = 2;
                	y = 250;
                	break;
                case KeyEvent.VK_ENTER:
                	state = a;
                	break;
   
           
            }
        }
    }
   
    
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}