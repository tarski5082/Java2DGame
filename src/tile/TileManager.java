package tile;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[]tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile = new Tile[3];
		mapTileNum =new int[gp.maxWorldRow][gp.maxWorldCol];
		getTileImage();
		loadMap();
	
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/tileMap.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for(int i = 0;i<gp.maxScreenRow;i++) {
				String lines = br.readLine();
				String numbers []= lines.split(" ");
				for(int j=0;j<gp.maxScreenCol;j++) {
					mapTileNum[i][j]=Integer.parseInt(numbers[j]);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTileImage() {
		int w=24;
		int h=20;
	
		
		try {
				int tileLen =16;
				
				tile[0] = new Tile();
				tile[0].image = ImageIO.read(getClass().getResourceAsStream("/ground/herbe1.png"));
				tile[1] = new Tile();
				tile[1].image = ImageIO.read(getClass().getResourceAsStream("/ground/stone.png"));
				tile[2] = new Tile();
				tile[2].image = ImageIO.read(getClass().getResourceAsStream("/ground/w1.png"));
				}
							
		
			
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int colMin=((gp.player.worldX)/gp.tileSize)-(gp.maxScreenCol/2)-1;
		colMin=Math.max(colMin,0);
		int colMax=((gp.player.worldX)/gp.tileSize)+(gp.maxScreenCol/2)+1;
		colMax=Math.min(colMax,gp.maxWorldCol);
		int rowMin=((gp.player.worldY)/gp.tileSize)-(gp.maxScreenRow/2)-1;
		rowMin=Math.max(rowMin,0);
		int rowMax=((gp.player.worldY)/gp.tileSize)+(gp.maxScreenRow/2)+1;
		rowMax = Math.min(gp.maxWorldRow, rowMax);
		for(int i=rowMin;i<rowMax;i++) {
			for(int j=colMin;j<colMax;j++) {
				int x = j*gp.tileSize;
				int y = i*gp.tileSize;
				int screenX=x-gp.player.worldX + gp.player.screenX;
				int screenY=y-gp.player.worldY + gp.player.screenY;;
				int t = mapTileNum[i][j];
				g2.drawImage(tile[t].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
			}
		}
				
	}
}
