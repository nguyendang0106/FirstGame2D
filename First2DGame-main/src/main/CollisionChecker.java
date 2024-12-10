package main;

import entity.Entity;
import java.awt.Rectangle;

public class CollisionChecker {
    private static final int NO_COLLISION = 999;
    private final GamePanel gp;
    
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    
    private String getEffectiveDirection(Entity entity) {
        return entity.knockBack ? entity.knockBackDirection : entity.direction;
    }
    
    private void updateEntityPosition(Entity entity, Rectangle tempArea, String direction) {
        switch(direction) {
            case "up" -> tempArea.y -= entity.speed;
            case "down" -> tempArea.y += entity.speed;
            case "left" -> tempArea.x -= entity.speed;
            case "right" -> tempArea.x += entity.speed;
        }
    }
    
    public void checkTile(Entity entity) {
        String direction = getEffectiveDirection(entity);
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
        }
    }
    
    public int checkObject(Entity entity, boolean player) {
        Rectangle tempArea = new Rectangle(
            entity.worldX + entity.solidArea.x,
            entity.worldY + entity.solidArea.y,
            entity.solidArea.width,
            entity.solidArea.height
        );
        
        String direction = getEffectiveDirection(entity);
        updateEntityPosition(entity, tempArea, direction);
        
        for(int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] != null) {
                Rectangle objectArea = new Rectangle(
                    gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x,
                    gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y,
                    gp.obj[gp.currentMap][i].solidArea.width,
                    gp.obj[gp.currentMap][i].solidArea.height
                );
                
                if(tempArea.intersects(objectArea)) {
                    if(gp.obj[gp.currentMap][i].collision) {
                        entity.collisionOn = true;
                    }
                    if(player) {
                        return i;
                    }
                }
            }
        }
        return NO_COLLISION;
    }
    
    public int checkEntity(Entity entity, Entity[][] target) {
        Rectangle tempArea = new Rectangle(
            entity.worldX + entity.solidArea.x,
            entity.worldY + entity.solidArea.y,
            entity.solidArea.width,
            entity.solidArea.height
        );
        
        String direction = getEffectiveDirection(entity);
        updateEntityPosition(entity, tempArea, direction);
        
        for(int i = 0; i < target[1].length; i++) {
            if(target[gp.currentMap][i] != null && target[gp.currentMap][i] != entity) {
                Rectangle targetArea = new Rectangle(
                    target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x,
                    target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y,
                    target[gp.currentMap][i].solidArea.width,
                    target[gp.currentMap][i].solidArea.height
                );
                
                if(tempArea.intersects(targetArea)) {
                    entity.collisionOn = true;
                    return i;
                }
            }
        }
        return NO_COLLISION;
    }
    
    public boolean checkPlayer(Entity entity) {
        Rectangle tempArea = new Rectangle(
            entity.worldX + entity.solidArea.x,
            entity.worldY + entity.solidArea.y,
            entity.solidArea.width,
            entity.solidArea.height
        );
        
        updateEntityPosition(entity, tempArea, entity.direction);
        
        Rectangle playerArea = new Rectangle(
            gp.player.worldX + gp.player.solidArea.x,
            gp.player.worldY + gp.player.solidArea.y,
            gp.player.solidArea.width,
            gp.player.solidArea.height
        );
        
        if(tempArea.intersects(playerArea)) {
            entity.collisionOn = true;
            return true;
        }
        return false;
    }
}