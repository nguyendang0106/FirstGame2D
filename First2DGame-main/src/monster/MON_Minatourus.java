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

public class MON_Minatourus extends Entity {
    GamePanel gp;
    public MON_Minatourus(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Minatourus";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower = 5;
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 35;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 25;
        attackArea.height = 25;
        motion1_duration = 40;
        motion2_duration = 85;
        loadImages();
    }

    private static final Map<String, BufferedImage> imageCache = new HashMap<>();
    private synchronized void loadImages() {
        if (imageCache.isEmpty()) {
            // Walking animations
            cacheImage("up1", "/monster/minatourus_up_1");
            cacheImage("up2", "/monster/minatourus_up_2");
            cacheImage("down1", "/monster/minatourus_down_1");
            cacheImage("down2", "/monster/minatourus_down_2");
            cacheImage("left1", "/monster/minatourus_left_1");
            cacheImage("left2", "/monster/minatourus_left_2");
            cacheImage("right1", "/monster/minatourus_right_1");
            cacheImage("right2", "/monster/minatourus_right_2");
            
            // Attack animations
            cacheImage("attackUp1", "/monster/minatourus_attackup_1", 1, 2);
            cacheImage("attackUp2", "/monster/minatourus_attackup_2", 1, 2);
            cacheImage("attackDown1", "/monster/minatourus_attackdown_1", 1, 2);
            cacheImage("attackDown2", "/monster/minatourus_attackdown_2", 1, 2);
            cacheImage("attackLeft1", "/monster/minatourus_attackleft_1", 2, 1);
            cacheImage("attackLeft2", "/monster/minatourus_attackleft_2", 2, 1);
            cacheImage("attackRight1", "/monster/minatourus_attackright_1", 2, 1);
            cacheImage("attackRight2", "/monster/minatourus_attackright_2", 2, 1);
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
        up1 = imageCache.get("up1");
        up2 = imageCache.get("up2");
        down1 = imageCache.get("down1");
        down2 = imageCache.get("down2");
        left1 = imageCache.get("left1");
        left2 = imageCache.get("left2");
        right1 = imageCache.get("right1");
        right2 = imageCache.get("right2");
        
        attackUp1 = imageCache.get("attackUp1");
        attackUp2 = imageCache.get("attackUp2");
        attackDown1 = imageCache.get("attackDown1");
        attackDown2 = imageCache.get("attackDown2");
        attackLeft1 = imageCache.get("attackLeft1");
        attackLeft2 = imageCache.get("attackLeft2");
        attackRight1 = imageCache.get("attackRight1");
        attackRight2 = imageCache.get("attackRight2");
    }

    @Override
    public void setAction() {
        if (onPath == true) {
            // Check if it stop chasing
            checkStopChasingOrNot(gp.player, 15, 100);
            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            // Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            // Get a random direction
            getRandomDirection(120);
        }
        // Check if it attacks
        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true;
    }

    @Override
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) + 1;
        // SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
