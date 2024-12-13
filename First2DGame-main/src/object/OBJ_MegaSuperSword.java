package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MegaSuperSword extends Entity {

    public static final String objName = "MegaSuper Sword";

    public OBJ_MegaSuperSword(GamePanel gp) {
        super(gp);

        type = type_megasupersword;
        name = objName;
        down1 = setup("/objects/megasupersword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 24;
        attackArea.height = 24;
        description = "[" + name + "]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
