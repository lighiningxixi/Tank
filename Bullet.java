package tankwar;

import java.awt.*;
import java.util.List;

public class Bullet extends GameObject{
    private int width = 10;
    private int height = 10;
    private int speed = 7;
    Direction direction;

    public Bullet(String img, int x, int y, Direction direction,GamePanel gamePanel) {
        super(img, x,  y, gamePanel);
        this.direction = direction;
    }

    public void go(){
        /**ÅÐ¶ÏÒÆ¶¯·½Ïò*/
        switch (direction){
            case UP:
                upward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
        }
    }
    //×Óµ¯ÒÆ¶¯
    public void leftward(){
        x -= speed;
        moveToBorder();
    }
    public void rightward(){
        x += speed;
        moveToBorder();
    }
    public void upward(){
        y -= speed;
        moveToBorder();
    }
    public void downward(){
        y += speed;
        moveToBorder();
    }

    /**×Óµ¯ÓëÌ¹¿ËÅö×²¼ì²â*/
    public void hitBot(){
        Rectangle next= this.getRec();
        List<Bot> bots = this.gamePanel.botList;
        //×Óµ¯ºÍbot
        for(Bot bot: bots){
            if(bot.getRec().intersects(next)){
                System.out.println("hit bot");
                this.gamePanel.blastList.add(new BlastObj(bot.x-34, bot.y-14));
                this.gamePanel.botList.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitBase(){
        Rectangle next = this.getRec();
        for(Base base: gamePanel.baseList) {
            if (base.getRec().intersects(next)) {
                this.gamePanel.baseList.remove(base);
                this.gamePanel.removeList.add(this);
                this.gamePanel.state = 4;
                break;
            }
        }
    }

    public void hitWall(){
        Rectangle next = this.getRec();
        List<Wall> walls = this.gamePanel.wallList;
        for(Wall w: walls) {
            if (w.getRec().intersects(next)) {
                this.gamePanel.wallList.remove(w);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }
    public void hitWall1(){
        Rectangle next = this.getRec();
        List<Wall1> wall1s = this.gamePanel.wall1List;
        for(Wall1 w: wall1s) {
            if (w.getRec().intersects(next)) {
            	
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void moveToBorder(){
        if (x < 0||x > this.gamePanel.getWidth()) {
            this.gamePanel.removeList.add(this);
        }
        if(y < 0||y > this.gamePanel.getHeight()){
            this.gamePanel.removeList.add(this);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
        //Åö×²¼ì²â
        hitBot();
        hitWall();
        hitBase();
        hitWall1();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
