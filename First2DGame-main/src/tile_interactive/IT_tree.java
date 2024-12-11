package tile_interactive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class IT_tree extends InteractiveTile{
	public IT_tree(GamePanel gp, int col, int row, int type) {
		super(gp,col,row);
		this.gp = gp;
        this.type = type;
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		down1 = setup("/tiles_interactive/"+type, gp.tileSize, gp.tileSize);
		destructible = true;
		life = 2;
	}
	@Override
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;	
		}
		return isCorrectItem;
	}
	@Override
	public void playSE() {
		gp.playSE(11);
	}
	@Override
	public InteractiveTile getDestroyedForm() {
		int a = switch (type) {
			case 703 -> 704;
			case 705, 706, 730, 732, 733 -> 731;
			case 757 -> 758;
			case 759, 760, 811 -> 812;
			case 784 -> 785;
			case 838 -> 839;
			default -> 0;
		};
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize, a);
		return tile;
	}
	@Override
	public Color getParticleColor() {
		Color color = new Color(65,50,30);
		return color;
	}
	@Override
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	@Override
	public int getParticleSpeed() {
		int particleSpeed = 1;
		return particleSpeed;
	}
	@Override
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
