package monster;

import entity.Entity;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_RedSlime extends Entity{
	
	GamePanel gp;

	public MON_RedSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type  = type_monster;
		name = "Red Slime";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 8;
		life = maxLife;
		attack = 7;
		defense = 0;
		exp = 5;
		projectile = new OBJ_Rock(gp);
		
		solidArea.x = 3;
		solidArea.y  = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		loadImages();

	}

    private static final Map<String, BufferedImage> imageCache = new HashMap<>();
    private synchronized void loadImages() {
        if (imageCache.isEmpty()) {
            // Walking animations
            cacheImage("down1", "/monster/redslime_down_1");
            cacheImage("down2", "/monster/redslime_down_2");
        }
        
        assignCachedImages();
    }
    
    private void cacheImage(String key, String path) {
        imageCache.putIfAbsent(key, setup(path, gp.tileSize, gp.tileSize));
    }
    
    private void cacheImage(String key, String path, int widthMult, int heightMult) {
        imageCache.putIfAbsent(key, setup(path, gp.tileSize * widthMult, gp.tileSize * heightMult));
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


	@Override
	public void setAction() {
		
		if(onPath == true) {
			
			// Check if it stop chasing
			checkStopChasingOrNot(gp.player, 15, 100);
			
			// Search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			// Check if it shoots a projectile 
			checkShootOrNot(200, 30);
		}
		else {
			// Check if it starts chasing
			checkStartChasingOrNot(gp.player, 5, 100);
			
			// Get a random direction
			getRandomDirection(120);
		}	
	}

	@Override
	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}

	@Override
	public void checkDrop() {
		// CAST A DIE
		int i = new Random().nextInt(100) + 1;
		
		// SET THE MONSTER DROP
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if( i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if( i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}

}
