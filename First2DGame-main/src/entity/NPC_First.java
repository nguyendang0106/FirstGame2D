package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import main.GamePanel;

public class NPC_First extends Entity {

	public NPC_First(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;

		dialogueSet = -1;

		loadImages();
		setDialogue();
	}

	private static final Map<String, BufferedImage> imageCache = new HashMap<>();
	private synchronized void loadImages() {
		if (imageCache.isEmpty()) {
			// Walking animations
			cacheImage("down1", "/npc/nun_down_1");
			cacheImage("down2", "/npc/nun_down_2");
			cacheImage("up1", "/npc/nun_up_1");
			cacheImage("up2", "/npc/nun_up_2");
			cacheImage("left1", "/npc/nun_left_1");
			cacheImage("left2", "/npc/nun_left_2");
			cacheImage("right1", "/npc/nun_right_1");
			cacheImage("right2", "/npc/nun_right_2");

		}
		assignCachedImages();
	}

	private void cacheImage(String key, String path) {
		imageCache.putIfAbsent(key, setup(path, gp.tileSize, gp.tileSize));
	}
	private void assignCachedImages() {
		up1 = imageCache.get("down1");
		up2 = imageCache.get("down2");
		down1 = imageCache.get("down1");
		down2 = imageCache.get("down2");
		left1 = imageCache.get("down1");
		left2 = imageCache.get("down2");
		right1 = imageCache.get("down1");
		right2 = imageCache.get("down2");
	}

	
	public void setDialogue() {
		dialogues[0][0] = "Hello.";
		dialogues[0][1] = "So you've come here to find OnePiece?";
		dialogues[0][2] = "I used to be a talented wizard...\nBut now I seem to be a bit too old to go on \nan adventure.";
		dialogues[0][3] = "Well, good luck on you! ";
		dialogues[1][0] = "If you become tired, rest at the water. ";
		dialogues[1][1] = "However, the monsters reapper if you rest.\nI don't know why but that's how it works.";
		dialogues[1][2] = "In any case, don't push yourself too hard.";
		dialogues[2][0] = "I wonder how to open that door...";
	}

	@Override
	public void setAction() {
		if (onPath == true) {
			int goalCol = 12;
			int goalRow = 9;
			// int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			// int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			searchPath(goalCol, goalRow);
		} else {
			actionLockCounter++;
			if (actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1; // pick up a number from 1 to 100
				if (i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				if (i > 75 && i <= 100) {
					direction = "right";
				}
				actionLockCounter = 0;
			}
		}
	}

	@Override
	public void speak() {
		// Do this character specific stuff
		facePlayer();
		startDialogue(this, dialogueSet);
		dialogueSet++;
		if (dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
		// if(gp.player.life < gp.player.maxLife/3) {
		// dialogueSet =1;
		// }
		// onPath = true;
	}
}
