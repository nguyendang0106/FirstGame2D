package main;

import entity.NPC_BigRock;
import entity.NPC_First;
import entity.NPC_Merchant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		Map<Integer, Integer> mapIndices = new HashMap<>(); // Track indices for each map
		InputStream is = getClass().getResourceAsStream("/maps/object.txt");
		if (is == null) {
			System.err.println("Could not find object.txt file");
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]);
					int type = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					int x = Integer.parseInt(parts[3]);
					// Get or initialize index for this map
					int index = mapIndices.getOrDefault(mapNum, 0);
					switch (type) {
						case 1 -> gp.obj[mapNum][index] = new OBJ_Door_Iron(gp);
						case 2 -> gp.obj[mapNum][index] = new OBJ_Tent(gp);
					}
					gp.obj[mapNum][index].worldX = gp.tileSize * x;
					gp.obj[mapNum][index].worldY = gp.tileSize * y;
					mapIndices.put(mapNum, index + 1);
				} 
			}
		} catch (IOException e) {
			System.err.println("Error reading object.txt: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Error closing reader: " + e.getMessage());
			}
		}

		is = getClass().getResourceAsStream("/maps/chest.txt");
		if (is == null) {
			System.err.println("Could not find chest.txt file");
			return;
		}
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(is));
		try{
			while ((line = reader2.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]);
					int type = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					int x = Integer.parseInt(parts[3]);
					// Get or initialize index for this map
					int index = mapIndices.getOrDefault(mapNum, 0);
					gp.obj[mapNum][index] = new OBJ_Chest(gp);
					switch (type) {
						case 1 -> gp.obj[mapNum][index].setLoot(new OBJ_Coin_Bronze(gp));
						case 2 -> gp.obj[mapNum][index].setLoot(new OBJ_Axe(gp));
						case 3 -> gp.obj[mapNum][index].setLoot(new OBJ_Potion_Blue(gp));
						case 4 -> gp.obj[mapNum][index].setLoot(new OBJ_Potion_Blue(gp));
						case 5 -> gp.obj[mapNum][index].setLoot(new OBJ_Potion_Red(gp));
						case 6 -> gp.obj[mapNum][index].setLoot(new OBJ_Lantern(gp));
						case 7 -> gp.obj[mapNum][index].setLoot(new OBJ_Potion_Red(gp));
						case 8 -> gp.obj[mapNum][index].setLoot(new OBJ_Shield_Wood(gp));
						case 9 -> gp.obj[mapNum][index].setLoot(new OBJ_Coin_Bronze(gp));
						case 10 -> gp.obj[mapNum][index].setLoot(new OBJ_Tent(gp));
						case 11 -> gp.obj[mapNum][index].setLoot(new OBJ_Sword_Normal(gp));
						case 12 -> gp.obj[mapNum][index].setLoot(new OBJ_SuperSword(gp));
						case 13 -> gp.obj[mapNum][index].setLoot(new OBJ_Pickaxe(gp));
						case 14 -> gp.obj[mapNum][index].setLoot(new OBJ_MegaSuperSword(gp));
					}
					gp.obj[mapNum][index].worldX = gp.tileSize * x;
					gp.obj[mapNum][index].worldY = gp.tileSize * y;
					// Increment and store the index for this map
					mapIndices.put(mapNum, index + 1);
				}
			}

		int mapNum = 4;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*62;
    	gp.obj[mapNum][i].worldY = gp.tileSize*36;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*62;
    	gp.obj[mapNum][i].worldY = gp.tileSize*35;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*70;
    	gp.obj[mapNum][i].worldY = gp.tileSize*45;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*51;
    	gp.obj[mapNum][i].worldY = gp.tileSize*35;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*61;
    	gp.obj[mapNum][i].worldY = gp.tileSize*35;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*61;
    	gp.obj[mapNum][i].worldY = gp.tileSize*36;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*61;
    	gp.obj[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*62;
    	gp.obj[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*63;
    	gp.obj[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*63;
    	gp.obj[mapNum][i].worldY = gp.tileSize*36;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*63;
    	gp.obj[mapNum][i].worldY = gp.tileSize*35;
		i++;
		}
		catch (IOException e) {
			System.err.println("Error reading chest.txt: " + e.getMessage());
		}
		finally {
			try {
				reader2.close();
			} catch (IOException e) {
				System.err.println("Error closing reader: " + e.getMessage());
			}
		}


	}
	public void setNPC() {
		Map<Integer, Integer> mapIndices = new HashMap<>(); // Track indices for each map
		InputStream is = getClass().getResourceAsStream("/maps/npc.txt");
		if (is == null) {
			System.err.println("Could not find npc.txt file");
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]);
					int type = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					int x = Integer.parseInt(parts[3]);
					// Get or initialize index for this map
					int index = mapIndices.getOrDefault(mapNum, 0);
					switch (type) {
						case 1 -> gp.npc[mapNum][index] = new NPC_First(gp);
						case 2 -> gp.npc[mapNum][index] = new NPC_BigRock(gp);
						case 3 -> gp.npc[mapNum][index] = new NPC_Merchant(gp);
					}
					gp.npc[mapNum][index].worldX = gp.tileSize * x;
					gp.npc[mapNum][index].worldY = gp.tileSize * y;
					// Increment and store the index for this map
					mapIndices.put(mapNum, index + 1);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading npc.txt: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Error closing reader: " + e.getMessage());
			}
		}

		// MAP 7
		int mapNum = 7;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 54;
		gp.npc[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.npc[mapNum][i] = new NPC_First(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 50;
		gp.npc[mapNum][i].worldY = gp.tileSize *25;
		i++;

		mapNum = 6;
		i = 0;
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 54;
		gp.npc[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.npc[mapNum][i] = new NPC_First(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 50;
		gp.npc[mapNum][i].worldY = gp.tileSize *25;
		i++;
	}
	public void setMonster() {
		Map <Integer, Integer> mapIndices = new HashMap<>(); // Track indices for each map
		InputStream is = getClass().getResourceAsStream("/maps/monster.txt");
		if (is == null) {
			System.err.println("Could not find monster.txt file");
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		try{
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]);
					int type = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					int x = Integer.parseInt(parts[3]);
					// Get or initialize index for this map
					int index = mapIndices.getOrDefault(mapNum, 0);
					switch (type) {
						case 1 -> gp.monster[mapNum][index] = new MON_GreenSlime(gp);
						case 2 -> gp.monster[mapNum][index] = new MON_RedSlime(gp);
						case 3 -> gp.monster[mapNum][index] = new MON_Orc(gp);
						case 4 -> gp.monster[mapNum][index] = new MON_Olap(gp);
						case 5 -> gp.monster[mapNum][index] = new MON_HaiTac(gp);
						case 6 -> gp.monster[mapNum][index] = new MON_Voi(gp);
						case 7 -> gp.monster[mapNum][index] = new MON_SuperVoi(gp);
						case 8 -> gp.monster[mapNum][index] = new MON_TuanLoc(gp);
						case 9 -> gp.monster[mapNum][index] = new MON_Minatourus(gp);
						case 10 -> gp.monster[mapNum][index] = new MON_VipMinatourus(gp);
						case 11 -> gp.monster[mapNum][index] = new MON_Bat(gp);
						case 12 -> gp.monster[mapNum][index] = new MON_Ghost(gp);
						case 13 -> gp.monster[mapNum][index] = new MON_Xacuop(gp);
						case 14 -> gp.monster[mapNum][index] = new MON_SkeletonLord(gp);
					}
					gp.monster[mapNum][index].worldX = gp.tileSize * x;
					gp.monster[mapNum][index].worldY = gp.tileSize * y;
					// Increment and store the index for this map
					mapIndices.put(mapNum, index + 1);
				}
			}
		}
		catch (IOException e) {
			System.err.println("Error reading monster.txt: " + e.getMessage());
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Error closing reader: " + e.getMessage());
			}
		}
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
				String[] parts = line.split(" ");
				if (parts.length == 4) {
					int mapNum = Integer.parseInt(parts[0]); 
					int type = Integer.parseInt(parts[1]);
					int x = Integer.parseInt(parts[2]);
					int y = Integer.parseInt(parts[3]);
					int index = mapIndices.getOrDefault(mapNum, 0);
					// Get or initialize index for this map
					switch (type) {
						case 1 -> gp.iTile[mapNum][index] = new IT_MetalPlate(gp, y, x);
						case 2 -> gp.iTile[mapNum][index] = new IT_DestructibleWall(gp, y, x);
						default -> gp.iTile[mapNum][index] = new IT_tree(gp, y, x, type);
					}
					// Increment and store the index for this map
					mapIndices.put(mapNum, index + 1);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading ITobject.txt: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Error closing reader: " + e.getMessage());
			}
		}
	}
}
