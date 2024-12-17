package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_TuiMu extends Entity {

    GamePanel gp;
    public static final String objName = "Surprise";

    public OBJ_TuiMu(GamePanel gp) {
        super(gp);

        this.gp = gp;
        type = type_consumable;
        name = objName;
        value = 2;
        down1 = setup("/objects/tuimu", gp.tileSize, gp.tileSize);
        description = "[Surprise]\nMana or Life or Exp. ";
        price = 25;
        stackable = true;

        setDialogue(this);
    }

    int i = new Random().nextInt(200);

    public void setDialogue(Entity entity) {
        if(i <= 25) {
            dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your mana has been recorvered by 3";
        } else if(25 < i && i <= 50) {
            dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your life has been recorvered by 3";

        } else if(50 < i && i <= 75) {
            if(entity.mana >= 2) {
                dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your mana has been lost ";
            } else if(entity.mana < 2) {
                dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your mana has been lost ";
            }
        } else if(75 < i && i <= 100) {
            dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your life has been losted by " + value + ".";
        } else if(100 < i) {
            dialogues[0][0] = "You drink the " + name + "!\n"
                             + "Your exp has been increased by 10."; // ko hiểu sao ko hiện đoạn text này nếu đủ level up nhưng tạm ổn
        }
       
    }
    public boolean use(Entity entity) {
        if(i <= 25) {
            startDialogue(this, 0);
            entity.mana += value + 1;
            gp.playSE(2);
            return true;
        } else if(25 < i && i <= 50) {
            startDialogue(this, 0);
            entity.life += value + 1;
            gp.playSE(2);
            return true;
        } else if(50 < i && i <= 75) {
            if(entity.mana >= 2) {
                startDialogue(this, 0); 
                entity.mana -= value;
                gp.playSE(2);
                return true;
            } else if(entity.mana < 2) {
                startDialogue(this, 0); 
                entity.mana = 0;
                gp.playSE(2);
                return true;
            }
        } else if(75 < i && i <= 100) {
            startDialogue(this, 0);
            entity.life -= value;
            gp.playSE(2);
            return true;
        } else {
            startDialogue(this, 0);
            entity.exp += 10;
            entity.checkLevelUp();
            gp.playSE(2);
            return true;
        }
        return false;
    }
}
