package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Map extends TileManager{
	BufferedImage worldMap[];
	public boolean miniMapOn = false;

	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		createWorldMap();
	}
	public void createWorldMap() {
		
		worldMap = new BufferedImage[gp.maxMap];
		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		
		for(int i = 0; i < gp.maxMap; i++) {
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize * col;
				int y = gp.tileSize * row;
				g2.drawImage(tile[tileNum].image, x, y, null);
				col++;
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			g2.dispose();
		}
	}
	public void drawFullMapScreen(Graphics2D g2) {
		// Background Color
		g2.setColor(Color.black);
		g2.fillRect(0,  0, gp.screenWidth, gp.screenHeight);
		// Draw Map
		int width = 500;
		int height = 500;
		int x = gp.screenWidth/2 - width/2;
		int y = gp.screenHeight/2 - height/2;
		g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
		// Draw Player
		double scale = (double)(gp.tileSize * gp.maxWorldCol)/width;
		int playerX = (int)(x + gp.player.worldX/scale);
		int playerY = (int)(y + gp.player.worldY/scale);
		int playerSize= (int)(gp.tileSize/scale);
		g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
		// Hint
		g2.setFont(gp.ui.maruMonica.deriveFont(32f));
		g2.setColor(Color.white);
		g2.drawString("Press M To Close", 750, 550);
	}
	public void drawMiniMap(Graphics2D g2) {
		if (miniMapOn == true) {
			// Draw Map
			int width = 150;
			int height = 75;
			double scale = (double)(gp.tileSize * 150) / width;
			int x = gp.screenWidth - width - 50;
			int y = 50;
			int mapPortionX =(int)(5*scale); // Starting x-coordinate of the portion
			int mapPortionY =(int)(5*scale); // Starting y-coordinate of the portion
			int mapPortionWidth =(int)(100*scale); // Width of the portion
			int mapPortionHeight = (int)(50*scale); // Height of the portion
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2.drawImage(worldMap[gp.currentMap], x, y, x + width, y + height, mapPortionX, mapPortionY, mapPortionX + mapPortionWidth, mapPortionY + mapPortionHeight, null);
			// Draw Player
			int playerX = (int)(x + 1.5*(gp.player.worldX - mapPortionX)/scale)-8;
			int playerY = (int)(y + 1.5*(gp.player.worldY - mapPortionY)/ scale)-12;
			int playerSize = (int)(gp.tileSize / 3);
			g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
}
