package com.sxt;

import java.awt.*;

public abstract class Tank extends GameObject{

    private String upImage; 
    private String downImage;
    private String rightImage;
    private String leftImage;
  
    protected int width = 40;
    protected int height = 50;
     private int speed = 3;
    Direction direction = Direction.UP;
    
   

    
    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.upImage = upImage;
        this.leftImage = leftImage;
        this.downImage = downImage;
        this.rightImage = rightImage;
    }

    public abstract void paintSelf(Graphics g);

    public abstract Rectangle getRec();
       
    }

