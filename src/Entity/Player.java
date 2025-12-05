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
	
	public Player(GamePanel gp,KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		setDefault();
	}
	
	public void setDefault() {
		x =100;
		y=100;
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
		
		
		if(keyH.upPressed) {
			y-=speed;
			direction ="up";
		}
		
		if(keyH.downPressed) {
			y+=speed;
			direction="down";
		}
		
		if(keyH.leftPressed) {
			x-=speed;
			direction="left";
		}
		
		if(keyH.rightPressed) {
			x+=speed;
			direction="right";
		}
		
		if(y<0) {
			y=0;
		}
		if(y+gp.tileSize>=gp.screenHeight){
			y=gp.screenHeight-gp.tileSize;
		}
		
		if(x<0) {
			x=0;
		}
		if(x+gp.tileSize>gp.screenWidth){
			x=gp.screenWidth-gp.tileSize;
		}
		spriteCounter++;
		if(spriteCounter>10) {
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
	//	g2.setColor(Color.white);
	//  g2.fillRect(x,y,gp.tileSize,gp.tileSize);
		BufferedImage image =null;
		boolean unPressed = !((keyH.upPressed) || (keyH.downPressed)||(keyH.leftPressed)||(keyH.rightPressed));
		switch(direction) {
			case "up":
				if(unPressed) {
					image=up;
				}
				else {
					switch(spriteNum) {
					case 1:
						image=upL;
						break;
					case 2:
						image=upR;
						break;
					}
				}
				break;
			case "down":
				if(unPressed) {
					image=down;
				}
				else {
					switch(spriteNum) {
					case 1:
						image=downL;
						break;
					case 2:
						image=downR;
						break;
					}
				}
				break;
			case "left":
				if(unPressed) {
					image=left;
				}
				else {
					switch(spriteNum) {
					case 1:
						image=leftL;
						break;
					case 2:
						image=leftR;
						break;
					}
				}
				break;
			case "right":
				if(unPressed) {
					image=right;
				}
				else {
					switch(spriteNum) {
					case 1:
						image=rightL;
						break;
					case 2:
						image=rightR;
						break;
					}
				}
				break;
		}
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
	}
}
