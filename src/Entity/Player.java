package Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public int playerX=0;	
	public int playerY=-8;
	
	
	public int speed;
	boolean isMoving=false;
	
	public Player(GamePanel gp,KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		setDefault();
		screenX =gp.screenWidth/2;
		screenY=gp.screenHeight/2;
	}
	
	public void setDefault() {
		worldX =gp.tileSize*16/2;
		worldY=gp.tileSize*6/2;
		speed=4;
		getPlayerImage();
		direction="down";
	}
	
	public void getPlayerImage() {
		try {
			up=ImageIO.read(getClass().getResourceAsStream("/player/Red-Still-Up.png"));
			down=ImageIO.read(getClass().getResourceAsStream("/player/Red-Still-Down.png"));
			left=ImageIO.read(getClass().getResourceAsStream("/player/Red-Still-Left.png"));
			right=ImageIO.read(getClass().getResourceAsStream("/player/Red-Still-Right.png"));
			upL=ImageIO.read(getClass().getResourceAsStream("/player/Red-leftLeg-Up.png"));
			downL=ImageIO.read(getClass().getResourceAsStream("/player/Red-leftLeg-Down.png"));
			leftL=ImageIO.read(getClass().getResourceAsStream("/player/Red-leftLeg-Left.png"));
			rightL=ImageIO.read(getClass().getResourceAsStream("/player/Red-leftLeg-Right.png"));
			upR=ImageIO.read(getClass().getResourceAsStream("/player/Red-rightLeg-Up.png"));
			downR=ImageIO.read(getClass().getResourceAsStream("/player/Red-RightLeg-Down.png"));
			leftR=ImageIO.read(getClass().getResourceAsStream("/player/Red-RightLeg-Left.png"));
			rightR=ImageIO.read(getClass().getResourceAsStream("/player/Red-RightLeg-Right.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.getDirection()==keyH.UP){
			direction ="up";
			worldY-=speed;
		}
		
		if(keyH.getDirection()==keyH.DOWN) {
			direction="down";
			worldY+=speed;
		}
		
		if(keyH.getDirection()==keyH.LEFT) {
			direction="left";
			worldX-=speed;
		}
		
		if(keyH.getDirection()==keyH.RIGHT) {
			direction="right";
			worldX+=speed;
			
		}
		if(worldX<=0) {
			worldX=0;
		}
		if(worldY<=0) {
			worldY=0;
		}
		
		
		
		spriteCounter++;
		if(spriteCounter>20) {
			switch(spriteNum) {
				case 1:
					spriteNum=2;
					break;
				case 2:
					spriteNum=1;
					break;
			}
			spriteCounter=0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image =null;
		boolean unPressed = (keyH.getDirection()==-1);
		switch(direction) {
			case "up":
				if(unPressed) {
					image=up;
				}
				else {
					image = (spriteNum==1)?upL:upR;
				}
				break;
			case "down":
				if(unPressed) {
					image=down;
				}
				else {
					image = (spriteNum==1)?downL:downR;
				}
				break;
			case "left":
				if(unPressed) {
					image=left;
				}
				else {
					image = (spriteNum==1)?leftL:leftR;
				}
				break;
			case "right":
				if(unPressed) {
					image=right;
				}
				else {
					image = (spriteNum==1)?rightL:rightR;
				}
				break;
		}
		g2.drawImage(image,(screenX),(screenY),gp.tileSize,gp.tileSize,null);
	}
}
