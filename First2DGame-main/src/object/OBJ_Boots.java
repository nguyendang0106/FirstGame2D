package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{
		
	GamePanel gp;
	public static final String objName = "Boots";
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);	
		
		type = type_consumable;
		type = type_pickupOnly;
		this.gp = gp;
		name = objName;
		value = 1;
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
		description = "[Boots]\nIncrease speed " + value + ".";
        price = 25;
		stackable = true;
	}

	public void setDialogue() {
        dialogues[0][0] = "You use " + name + "!\n"
                + "Your speed " + value + ".";
    }
    public boolean use(Entity entity) {
        startDialogue(this, 0);
        entity.speed += value;
        gp.playSE(2);
        return true;
    }
}
