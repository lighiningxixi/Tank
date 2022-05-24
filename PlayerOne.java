package ck;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerOne extends Tank {

	public PlayerOne(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage,
			GamePanel gamePanel) {
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void paintSelf(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img, x, y, null);
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return new Rectangle(x, y, width, height);
	}

}
