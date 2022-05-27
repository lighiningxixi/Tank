package tankwar;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;



public class GamePanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static URL url;
	static AudioClip ac;//����

    /** ����˫����ͼƬ */
    private Image offScreenImage = null;
    //��Ϸ״̬ 0 ��Ϸδ��ʼ��1 ����ģʽ��2 ˫��ģʽ�� 3 ��Ϸ��ͣ�� 4 ��Ϸʧ�ܣ�5 ��Ϸ�ɹ�
    public int state= 0;
    
    //��ʱ����
    private int a = 1;
    //�ػ����
    public int count = 0;
    //���ڳ���
    private int width = 1100;
    private int height = 670;
    //��������
    private int enemyCount = 0;
    //�߶�
    private int y = 460;//ָ��λ��
    private int y1 = -180;//tank.pngλ��
    private int y2 = 20;//����λ��
    //�Ƿ�ʼ
    private boolean start = false;
    //���弯��
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Base> baseList = new ArrayList<>();
    public List<BlastObj> blastList = new ArrayList<>();
    public List<Tree> treeList = new ArrayList<>();
    public List<Blood> bloodList = new ArrayList<>();
    public List<River> riverList = new ArrayList<>();
    public List<Wall1> wall1List = new ArrayList<>();
    
    //����ͼƬ
    public Image background = Toolkit.getDefaultToolkit().getImage("images/bj.png");
    //ָ��ͼƬ
    private Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    private Image select1 = Toolkit.getDefaultToolkit().getImage("images/fm.png");//tank pngͼƬ
    private Image select2 = Toolkit.getDefaultToolkit().getImage("images/dt.gif");//����ͼƬ
    //����
    private Base base = new Base("images/star.gif", 560, 630, this);
    //���
    private PlayerOne playerOne = new PlayerOne("images/player1/xxu.png", 125, 510,
            "images/player1/xxu.png","images/player1/xxd.png",
            "images/player1/xxl.png","images/player1/xxr.png", this);
    private PlayerTwo playerTwo = new PlayerTwo("images/player2/xxu.png", 920, 510,
            "images/player2/xxu.png","images/player2/xxd.png",
            "images/player2/xxl.png","images/player2/xxr.png", this);
    

    //���ڵ���������
    public void launch(){
        //����
        setTitle("̹�˴�ս");
        //���ڳ�ʼ��С
        setSize(width, height);
        //�û����ܵ�����С
        setResizable(false);
        //���ô���һֱ�����ϲ�
        super.setAlwaysOnTop(true);
        //ʹ���ڿɼ�
        setVisible(true);
        //��ȡ��Ļ�ֱ��ʣ�ʹ��������ʱ����
        setLocationRelativeTo(null);
        //��ӹر��¼�
        setDefaultCloseOperation(3);
        //��Ӽ����¼�
        this.addKeyListener(new GamePanel.KeyMonitor());
        //���Χǽ
        for(int i = 0; i< 19; i ++){
            wallList.add(new Wall("images/walls.gif", i*60 ,170, this ));
        }
        
        for(int i = 0; i< 11; i ++){
            wallList.add(new Wall("images/walls.gif", 550,i*60 , this ));//���ŵ�
        }
        
        wallList.add(new Wall("images/walls.gif", 490 ,610,this ));
        wallList.add(new Wall("images/walls.gif", 490 ,570,this ));
        wallList.add(new Wall("images/walls.gif", 550 ,570,this ));
        wallList.add(new Wall("images/walls.gif", 610 ,570,this ));
        wallList.add(new Wall("images/walls.gif", 610 ,610,this ));//��������
        //��Ӳݵ�
        for(int i = 0; i< 30; i ++){
            treeList.add(new Tree("images/tree.gif", i*40 ,300, this ));
        }
         bloodList.add(new Blood("images/xb.png", 300 ,400,this ));//
         
         for(int i = 6; i< 8; i ++){
         riverList.add(new River("images/river.jpg",40*i,350,this));
         }
        
      
           wall1List.add(new Wall1("images/metalWall.gif",200 ,300, this ));
           wall1List.add(new Wall1("images/metalWall.gif",600 ,300, this ));
           wall1List.add(new Wall1("images/metalWall.gif",650 ,300, this ));
           wall1List.add(new Wall1("images/metalWall.gif",690 ,300, this ));
      
        //��ӻ���
        baseList.add(base);

        while (true){
            if(botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            if(tankList.size() == 0 && (state == 1 || state == 2)){

                state = 4;
            }
            if(tankList.size() == 0 && enemyCount == 10 && (state == 1 || state == 2)){

                state = 6;
            }
            if(state == 1 || state == 2){ 
            	
                if (count % 100 == 1 && enemyCount < 10) {
                    Random r = new Random();
                    int rnum =r.nextInt(800);
                   
                    botList.add(new Bot("images/enemy/xxu.png", rnum, 110,
                            "images/enemy/xxu.png","images/enemy/xxd.png",
                            "images/enemy/xxl.png","images/enemy/xxr.png", this));//�з�̹��

                    enemyCount++;
                    //System.out.println("bot: " + botList.size());
                }
            }
            repaint();
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //paint����
    @Override
    
    public void paint(Graphics g) {
    	
        //System.out.println(bulletList.size());
        //System.out.println("tank"+tankList.size());
        //����������һ����С��ImageͼƬ
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        
        //��ø�ͼƬ�Ļ���
        Graphics gImage= offScreenImage.getGraphics();
        //���ñ�����ɫ
        gImage.setColor(Color.white);
        //�����������
        gImage.fillRect(0, 0, width, height);
        //�ı仭�ʵ���ɫ
        gImage.setColor(Color.orange);
        //�ı����ִ�С����ʽ
        //�������
        gImage.setFont(new Font("����",Font.BOLD,10));
        if(state == 0){
        	
        	gImage.drawString("<�˲�Ʒ���ڿ����׶�Ϊ���԰�  ����Ȩ�������Ŷ�>",400,50);
        }
        
        gImage.setFont(new Font("����",Font.BOLD,50));
        if(state == 0){
            
            gImage.drawString("ѡ����Ϸģʽ",350,400);
            gImage.drawString("������Ϸ",400,500);
            gImage.drawString("˫����Ϸ",400,600);
            
            
            gImage.drawImage(select,350,y,null);//ָ��߶�
            gImage.drawImage(select1,-250,y1,null);//tank.png�߶�
            gImage.drawImage(select2,400,y2,null);//����ͼƬ�߶�
        }
        gImage.setFont(new Font("����",Font.BOLD,15));
        if(state == 0){
        	gImage.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",400,650);
        }
        
        else if(state == 1||state == 2){
            gImage.setColor(Color.red);
            gImage.setFont(new Font("����",Font.BOLD,20));
            gImage.drawString("WASD�����ƶ�",15,510);
            gImage.drawString("�ո����",15,550);
            gImage.drawString("ʣ�����:, "+ botList.size(),10, 60);

            if(state == 2){
                gImage.drawString("����������ƶ�",800,510);
                gImage.drawString("K���",800,550);
            }
            

            //paint�ػ���ϷԪ��
            
            
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
            for(Tree tree : treeList){
                tree.paintSelf(gImage);
            }
            for(Blood blood: bloodList){
            	blood.paintSelf(gImage);
            }
            for(River river: riverList){
            	river.paintSelf(gImage);
            }
            for(Wall1 wall1: wall1List){
            	wall1.paintSelf(gImage);
            }
            //�ػ����+1
            count++;
        }
        gImage.setFont(new Font("����",Font.BOLD,100));
        if(state == 3){
        	gImage.setFont(new Font("����",Font.BOLD,100));
            gImage.drawString("��Ϣһ��",350,200);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("���鰲���ˣ�ƽ���ˣ�����Ҳ�Ϳ����ˣ��޺��ˡ�",100,400);
            
        }
        else if(state == 4){
            gImage.drawString("GAME OVER",330,400);
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            state = 0;
        }
        else if(state == 5){
            gImage.drawString("��Ϸʤ��",350,400);
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            state = 0;
        }
        
        else if(state == 7){
        	gImage.setFont(new Font("����",Font.BOLD,80));
            gImage.drawString("��Ϸ˵��",350,150);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("��F��������Ϸҳ��",100,200);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("���һ���ƶ���WSAD �ո�����",100,300);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("��Ҷ����ƶ��������   K�����",100,400);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("P����Ϸ��ͣ",100,500);
            gImage.setFont(new Font("����",Font.BOLD,40));
            gImage.drawString("Q����Ϸ˵��",100,600);
            gImage.setFont(new Font("����",Font.BOLD,20));
            gImage.drawString("Сtips������Ϸ�ʺ�>=16������û�ʹ�� ������Ϸ������۾�ƣ�� Ϊ�����Ľ��� ����������Ϸʱ��",100,650);
        }
        /** �����������ƺõ�ͼ���������Ƶ������Ļ����� */ 
        g.drawImage(offScreenImage, 0, 0, null);
    }

    
    private class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    y = 460;
                    a = 1;
                    break;
                case KeyEvent.VK_2:
                    y = 560;
                    a = 2;
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    //������
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
                    
                    case KeyEvent.VK_Q:
                        if(state != 7){
                            a = state;
                            state = 7;
                        }//��Ϸ˵��
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

    public static void main(String[] args) throws MalformedURLException {
    	File f1 = new File("music\\war1.wav");  
    	 url= f1.toURL();  
    	 ac= Applet.newAudioClip(url);  
    	 ac.loop();
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}