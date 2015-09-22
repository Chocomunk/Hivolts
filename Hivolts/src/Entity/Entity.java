package Entity;

import Tile.Tile;

public class Entity extends Tile{
	private boolean alive = true;
	private boolean Fencehertz = false;
	boolean movable = true;
	public Entity(int x, int y){	
		super(x, y);
	}
	public void Dead(){
		alive = false;
	}
}
