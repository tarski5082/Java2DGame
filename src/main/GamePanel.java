package main;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyListener;
import Entity.Player;
public class GamePanel extends JPanel implements Runnable{
	final int originalTitleSize=24;
	final int scale = 3;
	
	public final int tileSize = originalTitleSize*scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//FPS
	
	int FPS =60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//Player position
	Player player = new Player(this,keyH);
	
	//int playerX = 100;
	//int playerY = 100;
	
	//int playerSpeed = 4;
	

	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		long drawInterval = 1000000000/FPS;
		long delta=0;
		long currentTime;
		long lastTime = System.nanoTime() +drawInterval;
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
		
			delta =(currentTime-lastTime)/drawInterval;
			
			if(delta>=1) {
				lastTime=currentTime;
				update();
				repaint();
				delta=0;
			}
			
			
		}
	}
	
	public void update() {
		player.update();	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		g2.dispose();
	}
}
