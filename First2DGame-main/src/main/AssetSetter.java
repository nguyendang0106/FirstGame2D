package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.*;
import java.util.HashMap;
import java.util.Map;
import monster.*;
import object.*;
import tile_interactive.*;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		// gp.obj[mapNum][i] = new OBJ_Axe(gp);
		// gp.obj[mapNum][i].worldX = gp.tileSize * 33;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 7;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Lantern(gp);
		// gp.obj[mapNum][i].worldX = gp.tileSize * 18;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Tent(gp);
		// gp.obj[mapNum][i].worldX = gp.tileSize * 19;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		// i++;
		// // gp.obj[mapNum][i] = new OBJ_Door(gp);
		// // gp.obj[mapNum][i].worldX = gp.tileSize*14;
		// // gp.obj[mapNum][i].worldY = gp.tileSize*28;
		// // i++;
		// // gp.obj[mapNum][i] = new OBJ_Door(gp);
		// // gp.obj[mapNum][i].worldX = gp.tileSize*12;
		// // gp.obj[mapNum][i].worldY = gp.tileSize*12;
		// // i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 29;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 17;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 16;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		// i++;
		// mapNum = 2;
		// i = 0;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 40;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 41;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 13;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 34;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_Chest(gp);
		// gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		// gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		// i++;
		// // gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		// // gp.obj[mapNum][i].worldX = gp.tileSize*18;
		// // gp.obj[mapNum][i].worldY = gp.tileSize*23;
		// // i++;
		// mapNum = 3;
		// i = 0;
		// gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		// gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		// i++;
		// gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
		// gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		// gp.obj[mapNum][i].worldY = gp.tileSize * 8;
		// i++;
	}
	public void setNPC() {
		// int mapNum = 0;
		// int i = 0;
		// // MAP 0
		// gp.npc[mapNum][i] = new NPC_First(gp);
		// gp.npc[mapNum][i].worldX = gp.tileSize * 21;
		// gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		// i++;
		// // MAP 1
		// mapNum = 1;
		// i = 0;
		// gp.npc[mapNum][i] = new NPC_Merchant(gp);
		// gp.npc[mapNum][i].worldX = gp.tileSize * 12;
		// gp.npc[mapNum][i].worldY = gp.tileSize * 7;
		// i++;
		// mapNum = 2;
		// i = 0;
		// gp.npc[mapNum][i] = new NPC_BigRock(gp);
		// gp.npc[mapNum][i].worldX = gp.tileSize * 20;
		// gp.npc[mapNum][i].worldY = gp.tileSize * 25;
		// i++;
		// gp.npc[mapNum][i] = new NPC_BigRock(gp);
		// gp.npc[mapNum][i].worldX = gp.tileSize * 11;
		// gp.npc[mapNum][i].worldY = gp.tileSize * 18;
		// i++;
		// gp.npc[mapNum][i] = new NPC_BigRock(gp);
		// gp.npc[mapNum][i].worldX = gp.tileSize * 23;
		// gp.npc[mapNum][i].worldY = gp.tileSize * 14;
		// i++;
	}
	public void setMonster() {
		// int mapNum = 0;
		// int i = 0;
		// gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 21;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 38;
		// i++;
		// gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		// i++;
		// gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 24;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 37;
		// i++;
		// gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		// i++;
		// gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 38;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		// i++;
		// gp.monster[mapNum][i] = new MON_RedSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 7;
		// i++;
		// gp.monster[mapNum][i] = new MON_RedSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 38;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 9;
		// i++;
		// gp.monster[mapNum][i] = new MON_RedSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 36;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 10;
		// i++;
		// gp.monster[mapNum][i] = new MON_RedSlime(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 38;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 6;
		// i++;
		// gp.monster[mapNum][i] = new MON_Orc(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 12;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 33;
		// i++;
		// gp.monster[mapNum][i] = new MON_Olap(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 12;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 11;
		// i++;
		// /*
		//  * gp.monster[mapNum][i] = new MON_Xacuop(gp);
		//  * gp.monster[mapNum][i].worldX = gp.tileSize * 122;
		//  * gp.monster[mapNum][i].worldY = gp.tileSize * 122;
		//  * i++;
		//  */
		// gp.monster[mapNum][i] = new MON_Ghost(gp);
		// gp.monster[mapNum][i].worldX = gp.tileSize * 22;
		// gp.monster[mapNum][i].worldY = gp.tileSize * 22;
		// i++;
		// mapNum = 2;
		// i++;
		// // gp.monster[mapNum][i] = new MON_Bat(gp);
		// // gp.monster[mapNum][i].worldX = gp.tileSize*34;
		// // gp.monster[mapNum][i].worldY = gp.tileSize*39;
		// // i++;
		// //
		// // gp.monster[mapNum][i] = new MON_Bat(gp);
		// // gp.monster[mapNum][i].worldX = gp.tileSize*36;
		// // gp.monster[mapNum][i].worldY = gp.tileSize*25;
		// // i++;
		// //
		// // gp.monster[mapNum][i] = new MON_Bat(gp);
		// // gp.monster[mapNum][i].worldX = gp.tileSize*39;
		// // gp.monster[mapNum][i].worldY = gp.tileSize*26;
		// // i++;
		// //
		// // gp.monster[mapNum][i] = new MON_Bat(gp);
		// // gp.monster[mapNum][i].worldX = gp.tileSize*28;
		// // gp.monster[mapNum][i].worldY = gp.tileSize*11;
		// // i++;
		// //
		// // gp.monster[mapNum][i] = new MON_Bat(gp);
		// // gp.monster[mapNum][i].worldX = gp.tileSize*10;
		// // gp.monster[mapNum][i].worldY = gp.tileSize*19;
		// // i++;
		// mapNum = 3;
		// i++;
		// if (Progress.skeletonLordDefeated == false) {
		// 	gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
		// 	gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		// 	gp.monster[mapNum][i].worldY = gp.tileSize * 16;
		// 	i++;
		// }
	}
	public void setInteractiveTile() {
		Map<Integer, Integer> mapIndices = new HashMap<>(); // Track indices for each map
		
		InputStream is = getClass().getResourceAsStream("/maps/ITobject.txt");
		if (is == null) {
			System.err.println("Could not find ITobject.txt file");
			return;
		}
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		
		try {
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]); 
					int type = Integer.parseInt(parts[1]);
					int x = Integer.parseInt(parts[2]);
					int y = Integer.parseInt(parts[3]);
					
					// Get or initialize index for this map
					int index = mapIndices.getOrDefault(mapNum, 0);
					
					if (type == 704 || type == 731 || type == 758 || 
						type == 812 || type == 839 || type == 785) {
						gp.iTile[mapNum][index] = new IT_Trunk(gp, y, x, type);
					} else {
						gp.iTile[mapNum][index] = new IT_tree(gp, y, x, type);
					}
					// Increment and store the index for this map
					mapIndices.put(mapNum, index + 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}}
