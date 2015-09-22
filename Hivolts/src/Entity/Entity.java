package Entity;

//import Tile.Tile;

public class Entity extends Tile{
	private boolean Fencehertz;
	movable = true;
	public Entity(int x, int y, boolean alive){	
		this.x = x;
		this.y = y;
		this.alive = alive;
	}
	public void Dead(){
		alive = false;
	}
}