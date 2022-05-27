package tankwar;

import java.awt.*;

public class Wall1 extends GameObject {

    public int width = 40;
    public int height = 40;
    public Wall1(String img, int x, int y, GamePanel gamePanel){
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
