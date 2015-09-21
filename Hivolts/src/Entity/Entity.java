package Entity;

import Tile.Tile;

public class Entity extends Tile{
	private boolean alive = true;
	private boolean Fencehertz;
	movable = true;
	public Entity(int x, int y){	
		this.x = x;
		this.y = y;
	}
	public void Dead(){
		alive = false;
	}
}