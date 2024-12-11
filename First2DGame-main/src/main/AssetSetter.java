package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import monster.*;
import tile_interactive.*;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {

	}
	public void setNPC() {
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
		
		InputStream is = getClass().getResourceAsStream("/maps/nIT.txt");
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
