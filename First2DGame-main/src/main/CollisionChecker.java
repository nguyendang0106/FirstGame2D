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
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    
        // Calculate initial positions
        int entityLeftCol = Math.min(entityLeftWorldX / gp.tileSize, gp.maxWorldCol - 1);
        int entityRightCol = Math.min(entityRightWorldX / gp.tileSize, gp.maxWorldCol - 1);
        int entityTopRow = Math.min(entityTopWorldY / gp.tileSize, gp.maxWorldRow - 1);
        int entityBottomRow = Math.min(entityBottomWorldY / gp.tileSize, gp.maxWorldRow - 1);
    
        // Ensure indices are within bounds
        entityLeftCol = Math.max(0, entityLeftCol);
        entityRightCol = Math.max(0, entityRightCol);
        entityTopRow = Math.max(0, entityTopRow);
        entityBottomRow = Math.max(0, entityBottomRow);
    
        int tileNum1, tileNum2;
    
        switch(direction) {
            case "up" -> {
                entityTopRow = Math.max(0, Math.min((entityTopWorldY - entity.speed) / gp.tileSize, gp.maxWorldRow - 1));
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "down" -> {
                entityBottomRow = Math.max(0, Math.min((entityBottomWorldY + entity.speed) / gp.tileSize, gp.maxWorldRow - 1));
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "left" -> {
                entityLeftCol = Math.max(0, Math.min((entityLeftWorldX - entity.speed) / gp.tileSize, gp.maxWorldCol - 1));
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                entity.collisionOn = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
            }
            case "right" -> {
                entityRightCol = Math.max(0, Math.min((entityRightWorldX + entity.speed) / gp.tileSize, gp.maxWorldCol - 1));
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