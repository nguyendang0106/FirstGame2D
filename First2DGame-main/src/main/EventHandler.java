package main;

import data.Progress;
import entity.Entity;

public class EventHandler{
	
	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventMaster = new Entity(gp);
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		setDialogue();
	}
	public void setDialogue() {
		
		eventMaster.dialogues[0][0] = "You fall into a pit!";
		
		eventMaster.dialogues[1][0] = "You drink the holy water. \nYour life and mana have been recovered.\n"
				+ "(The progress has been saved)";
		eventMaster.dialogues[1][1] = "Damn, this is good water.";

	}
	public void checkEvent() {
		
		// Check if the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent == true) {

			//new teleport ẻvent
			if(hit(0,18,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,19,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,20,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,21,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,22,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,23,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,24,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,25,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,26,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,27,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,28,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,29,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,30,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,31,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,32,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,33,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,34,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,35,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,36,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,37,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,38,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,39,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,40,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,41,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,42,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,43,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,44,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,45,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,46,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,47,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,48,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,49,55,"any") == true) teleport2(1,44,15,gp.oasis);	
			if(hit(0,50,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,51,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,52,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,53,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,54,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(0,55,55,"any") == true) teleport2(1,44,15,gp.oasis);
			if(hit(1,44,15,"any") == true) teleport2(0,45,55,gp.oasis);


			if(hit(2,14,46,"any") == true) teleport2(1,13,48,gp.oasis);
			if(hit(1,13,48,"any") == true) teleport2(2,14,46,gp.land);
			if(hit(1,14,48,"any") == true) teleport2(2,14,46,gp.land);
			if(hit(1,13,49,"any") == true) teleport2(2,14,46,gp.land);
			if(hit(1,14,49,"any") == true) teleport2(2,14,46,gp.land);

			if(hit(2,24,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,25,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,26,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,27,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,28,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,29,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,30,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,31,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,32,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,33,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,34,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,35,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,36,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,37,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,38,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,39,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,40,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,41,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,42,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,43,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,44,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,45,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,46,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,47,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,48,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,49,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,50,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,51,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,52,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,53,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,54,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,55,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,56,4,"any") == true) teleport2(3,33,47,gp.land);
			if(hit(2,57,4,"any") == true) teleport2(3,33,47,gp.land);

			if(hit(3,33,48,"any") == true) teleport2(2,24,4,gp.land);

			if(hit(2,29,40,"any") == true) teleport2(4, 8, 3, gp.maze);
			if(hit(2,30,41,"any") == true) teleport2(4, 8, 3, gp.maze);
			if(hit(2,29,41,"any") == true) teleport2(4, 8, 3, gp.maze);
			if(hit(2,30,40,"any") == true) teleport2(4, 8, 3, gp.maze);

			if(hit(4,53,35,"any") == true) skeletonLord();
			if(hit(4,53,36,"any") == true) skeletonLord();
			if(hit(4,62,45,"any") == true) skeletonLord();
			if(hit(4,71,45,"any") == true) skeletonLord();
			if(hit(4,70,45,"any") == true) skeletonLord();


			// if(hit(2,17,39,"any") == true) teleport2(6,29,40,gp.land);
			// if(hit(6,29,40,"any") == true) teleport2(2,17,39,gp.land);

			if(hit(3,93,11,"any") == true) teleport2(5,54,48,gp.land);
			if(hit(3,92,11,"any") == true) teleport2(5,55,48,gp.land);
			if(hit(3,93,12,"any") == true) teleport2(5,54,48,gp.land);
			if(hit(3,92,12,"any") == true) teleport2(5,55,48,gp.land);

			if(hit(5,54,49,"any") == true) teleport2(3,93,11,gp.land);
			if(hit(5,55,49,"any") == true) teleport2(3,93,11,gp.land);
			
			// if(hit(5,29,40,"any") == true) teleport2(3,93,11,gp.land);
		}
	}
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	public void teleport(int gameState, int col, int row) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!!!";
		gp.player.worldX = gp.tileSize*col;
		gp.player.worldY = gp.tileSize*row;
	}
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1;
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}		
	}
	public void teleport2(int map, int col, int row, int area) {
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.playSE(13);
	}
	public void speak(Entity entity) {	
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	public void skeletonLord() {	
		if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonLord;
		}
	}
}
