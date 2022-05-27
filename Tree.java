package tankwar;

import java.awt.*;

public class Tree extends GameObject {

    public int width = 60;
    public int height = 60;
    public Tree(String img, int x, int y, GamePanel gamePanel){
        super(img, x, y, gamePanel);
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