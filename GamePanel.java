package com.sxt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GamePanel extends JFrame{
	
	int width = 800;
	int height = 610;
	Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
	int y = 150;
	int state = 0;
	int a = 1;
	
	public void launch() {
		setTitle("坦克大作战");
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);
		this.addKeyListener(new GamePanel.KeyMonitor());;
		while(true) {
			repaint();
			try {
				Thread.sleep(25);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		g.setFont(new Font("仿宋", Font.BOLD, 50));
		if(state == 0) {
		
		g.drawString("选择游戏模式", 220, 100);
		g.drawString("单人模式", 220, 200);
		g.drawString("双人模式", 220, 300);
		g.drawImage(select, 160, y, null);
		} else if(state == 1 || state == 2) {
			g.drawString("游戏开始", 220, 100);
			if(state == 1) {
				g.drawString("单人模式", 220, 200);
			}
			else {
				g.drawString("双人模式", 220, 200);
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_1:
				a = 1;
				y = 150;
				break;
			case KeyEvent.VK_2:
				a = 2;
				y = 250;
				break;
			case KeyEvent.VK_ENTER:
				state = a;
				break;
			}
			System.out.print(e.getKeyChar());
		}
		
	}
	
	public static void main(String[] args) {
		GamePanel gp = new GamePanel();
		gp.launch();
	}

}
