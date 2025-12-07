package main;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyListener;
import Entity.Player;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTINGS
	final int originalTitleSize=24;
	final int scale = 2;
	
	public final int tileSize = originalTitleSize*scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTINGS
	public final int maxWorldCol=17;
	public final int maxWorldRow=15;
	public final int worldWidth=maxWorldCol*tileSize;
	public final int worldHeight=maxWorldRow*tileSize;
	
	//FPS
	
	int FPS =60;
	
	public TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//Player position
	public Player player = new Player(this,keyH);
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(worldWidth,worldHeight));
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
		int second = 1000000000;
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
		tileM.getTileImage();
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
