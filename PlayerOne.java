package ck;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerOne extends Tank {

	public PlayerOne(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage,
			GamePanel gamePanel) {
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void paintSelf(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img, x, y, null);
	}

	@Override
	public Rectangle getRec() {
		// TODO �Զ����ɵķ������
		return new Rectangle(x, y, width, height);
	}

}
