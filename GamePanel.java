package tankwar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class GamePanel extends JFrame {

    
    private Image offScreenImage = null;
    
    public int state= 0;
   
    private int a = 1;
   
    public int count = 0;
    
    private int width = 800;
    private int height = 610;
    
    private int enemyCount = 0;
    
    private int y = 150;
   
    private boolean start = false;

    public List<Bullet> bulletList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Base> baseList = new ArrayList<>();
    public List<BlastObj> blastList = new ArrayList<>();
    
    public Image background = Toolkit.getDefaultToolkit().getImage("D:\\Java/images/background.jpg");
    
    private Image select = Toolkit.getDefaultToolkit().getImage("D:\\Java/images/selecttank.gif");
    
    private Base base = new Base("D:\\Java/images/star.gif", 380, 560, this);
    
    private PlayerOne playerOne = new PlayerOne("D:\\Java/images/player1/p1tankU.gif", 125, 510,
            "D:\\Java/images/player1/p1tankU.gif","D:\\Java/images/player1/p1tankD.gif",
            "D:\\Java/images/player1/p1tankL.gif","D:\\Java/images/player1/p1tankR.gif", this);
    private PlayerTwo playerTwo = new PlayerTwo("D:\\Java/images/player2/p2tankU.gif", 625, 510,
            "D:\\Java/images/player2/p2tankU.gif","D:\\Java/images/player2/p2tankD.gif",
            "D:\\Java/images/player2/p2tankL.gif","D:\\Java/images/player2/p2tankR.gif", this);

    
    public void launch(){
        
        setTitle("开始游戏");
        
        setSize(width, height);
        
        setResizable(false);
        
        setVisible(true);
  
        setLocationRelativeTo(null);
       
        setDefaultCloseOperation(3);
       
        this.addKeyListener(new GamePanel.KeyMonitor());
      
        for(int i = 0; i< 14; i ++){
            wallList.add(new Wall("D:\\Java/images/walls.gif", i*60 ,170, this ));
        }
        wallList.add(new Wall("D:\\Java/images/walls.gif", 305 ,560,this ));
        wallList.add(new Wall("D:\\Java/images/walls.gif", 305 ,500,this ));
        wallList.add(new Wall("D:\\Java/images/walls.gif", 365 ,500,this ));
        wallList.add(new Wall("D:\\Java/images/walls.gif", 425 ,500,this ));
        wallList.add(new Wall("D:\\Java/images/walls.gif", 425 ,560,this ));
      
        baseList.add(base);

        while (true){
            if(botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            if(tankList.size() == 0 && (state == 1 || state == 2)){

                state = 4;
            }
            if(state == 1 || state == 2){
                if (count % 100 == 1 && enemyCount < 10) {
                    Random r = new Random();
                    int rnum =r.nextInt(800);
                    botList.add(new Bot("D:\\Java/images/enemy/enemy1U.gif", rnum, 110,
                            "D:\\Java/images/enemy/enemy1U.gif","D:\\Java/images/enemy/enemy1D.gif",
                            "D:\\Java/images/enemy/enemy1L.gif","D:\\Java/images/enemy/enemy1R.gif", this));
                    enemyCount++;
                    //System.out.println("bot: " + botList.size());
                }
            }
            repaint();
            try {
               
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println(bulletList.size());
        //System.out.println("tank"+tankList.size());
        
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        
        Graphics gImage= offScreenImage.getGraphics();
       
        gImage.setColor(Color.gray);
        
        gImage.fillRect(0, 0, width, height);
        
        gImage.setColor(Color.yellow);
        
        gImage.setFont(new Font("宋体",Font.BOLD,50));
        if(state == 0){
            
        	gImage.setFont(new Font("宋体",Font.BOLD,50));
            gImage.drawString("开始游戏",220,100);
            gImage.drawString("单人模式",220,200);
            gImage.drawString("双人模式",220,300);
            gImage.drawString("按1，2选择模式,按回车开始游戏", 0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            gImage.setColor(Color.red);
            gImage.setFont(new Font("宋体",Font.BOLD,20));
            gImage.drawString("WASD移动",0,510);
            gImage.drawString("空格射击",0,550);
            if(state == 2){
                gImage.drawString("方向键移动",575,510);
                gImage.drawString("k射击",575,550);
            }

           
            for(Tank tank : tankList){
                tank.paintSelf(gImage);
            }
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall: wallList){
                wall.paintSelf(gImage);
            }
            for(Base base : baseList){
                base.paintSelf(gImage);
            }
            for(BlastObj blast : blastList){
                blast.paintSelf(gImage);
            }
           
            count++;
        }
        else if(state == 3){
            gImage.drawString("您的队友已阵亡",220,200);
        }
        else if(state == 4){
            gImage.drawString("游戏失败",220,200);
        }
        else if(state == 5){
            gImage.drawString("恭喜通关",220,200);
        }
       
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    y = 150;
                    a = 1;
                    break;
                case KeyEvent.VK_2:
                    y = 250;
                    a = 2;
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    
                    if(state == 1 && !start){
                        tankList.add(playerOne);
                    }else{
                        tankList.add(playerOne);
                        tankList.add(playerTwo);
                    }
                    start = true;
                    break;
                case KeyEvent.VK_P:
                    if(state != 3){
                        a = state;
                        state = 3;
                    }
                    else{
                        state = a;
                        if(a == 0) {
                            a = 1;
                        }
                    }
                    break;
                default:
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            playerOne.keyReleased(e);
            playerTwo.keyReleased(e);
        }
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}