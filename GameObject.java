package com.sxt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class GameObject {

    //娓告垙鍏冪礌鍥剧墖
    Image img;
    //娓告垙鍏冪礌鐨勬í鍧愭爣
    int x;
    //娓告垙鍏冪礌鐨勭旱鍧愭爣
    int y;
    //娓告垙鍏冪礌鐨勫
    int width;
    //娓告垙鍏冪礌鐨勯珮
   
    //娓告垙鍏冪礌鐨勭Щ鍔ㄦ柟鍚�

    //寮曞叆涓荤晫闈�
    GamePanel gamePanel;

    
    public GameObject(String img, int x, int y, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

 
	public abstract void paintSelft(Graphics g);
	
	public abstract Rectangle gerRec();
}
		


