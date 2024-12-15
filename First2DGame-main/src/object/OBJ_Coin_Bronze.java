package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

	GamePanel gp;
	public static final String objName = "Bronze Coin";

	public OBJ_Coin_Bronze(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_consumable;
		name = objName;
		value = 10;
		down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
		description = "[Bronze Coin]\nAncient coin" + value + ".";
		stackable = true;

		setDialogue();
	}
	public void setDialogue() {
        dialogues[0][0] = "You use the " + name + "!\n"
                + "You earn " + value + " coin.";
    }
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		gp.player.coin += value;
		gp.playSE(1);
		return true;
	}
	// public boolean useP(Entity entity) {
	// 	gp.playSE(1);
	// 	gp.ui.addMessage("Coin +" + value);
	// 	gp.player.coin += value;
	// 	return true;
	// }
}
