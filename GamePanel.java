package ck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JFrame {
	
	int width = 800;
    int height = 610;
    public List<Tank> tankList = new ArrayList<>();
   
    //双缓冲图片
    Image offScreenImage = null;
    //图片
    private Image select = Toolkit.getDefaultToolkit().getImage("D:\\Java/workspace/tankwar/bin/images/selecttank.gif");
    //指针初始坐标
    private int y = 150;
   	public boolean start = false;
    //游戏模式
    int state = 0;
    int a = 1;
    //重绘次数
    int count = 0;
    //已生成敌人数量
    int enemyCount = 0;
    //游戏元素列表
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();//子弹
    public List<Bot> botList = new ArrayList<Bot>();//敌方坦克
    
    PlayerOne playerOne = new PlayerOne("D:\\Java/workspace/tankwar/bin/images/player1/p1tankU.gif", 125, 510,
            "D:\\Java/workspace/tankwar/bin/images/player1/p1tankU.gif",
            "D:\\Java/workspace/tankwar/bin/images/player1/p1tankD.gif",
            "D:\\Java/workspace/tankwar/bin/images/player1/p1tankL.gif",
            "D:\\Java/workspace/tankwar/bin/i/mages/player1/p1tankR.gif",this);
   
  
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

    	//重绘
        while (true){
        	//添加电脑坦克
        	if(count % 100 == 1 && enemyCount < 10) {
                Random random = new Random();
                int rnum = random.nextInt(800);
        	botList.add(new Bot("D:\\Java\\workspace\\tankwar\\bin\\images\\enemy\\enemy1U.gif", rnum, 110,
                    "D:\\Java\\workspace\\tankwar\\bin\\images\\enemy\\enemy1U.gif",
                    "D:\\Java\\workspace\\tankwar\\bin\\images\\enemy\\enemy1D.gif",
                    "D:\\Java\\workspace\\tankwar\\bin\\images\\enemy\\enemy1L.gif",
                    "D:\\Java\\workspace\\tankwar\\bin\\images\\enemy\\enemy1R.gif",this));
        	        enemyCount ++;
        	}
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
    	gImage.setColor(Color.gray);
        //实心矩形
    	gImage.fillRect(0, 0, width, height);
    	gImage.setColor(Color.blue);
        if(state == 0){
        	gImage.setFont(new Font("宋体",Font.BOLD,50));
            gImage.drawString("开始游戏",220,100);
            gImage.drawString("单人模式",220,200);
            gImage.drawString("双人模式",220,300);
            gImage.drawString("按1，2选择模式,按回车开始游戏", 0,400);
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
            
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            //循环子弹 
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            
            //重绘一次
            count++;
        }
        //将缓冲区绘制的图像整个绘制到画布中去
        
        g.drawImage(offScreenImage, 0, 0, null);
    }
    
  //键盘监视器
    private class KeyMonitor extends KeyAdapter{
    	
    	//按下键盘
        @Override
        public void keyPressed(KeyEvent e) {
            //返回键值
            int key = e.getKeyCode();
            
    		switch (key){
                case KeyEvent.VK_1:
                	if(!start) {
                	    a = 1;
                        y = 150;
                        }
                	 break;
                    //System.out.println(state);
                case KeyEvent.VK_2:
                	if(!start) {
                	    a = 2;
                        y = 250;
                	}
                	
                   // System.out.println(state);
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
    		System.out.print(e.getKeyChar());
        }
//        松开键盘
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