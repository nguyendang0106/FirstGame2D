package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_tree extends InteractiveTile{
	
	GamePanel gp;
	int type;


	public IT_tree(GamePanel gp, int col, int row, int type) {
		super(gp,col,row);
		this.gp = gp;
        this.type = type;
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		System.out.println(type);
		down1 = setup("/tiles_interactive/"+type, gp.tileSize, gp.tileSize);

		destructible = true;
		life = 2;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;	
		}
		return isCorrectItem;
	}
	public void playSE() {
		gp.playSE(11);
	}
	public InteractiveTile getDestroyedForm() {
		int a;
		switch (type) {
			case 703:				a = 704;				break;
			case 705, 706, 730, 732,733:				a = 731;				break;
			case 757:				a = 758;				break;
			case 759,760,811:				a = 812;				break;
			case 784:				a = 785;				break;
			case 838:				a = 839;				break;
			default:				a = 0;				break;
		}
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize, a);
		return tile;
	}
	public Color getParticleColor() {
		Color color = new Color(65,50,30);
		return color;
	}
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
