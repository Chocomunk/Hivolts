package Entity;

import Tile.Tile;
import Tile.TileMap;

public abstract class Entity extends Tile{
	private TileMap map;
	
	public Entity(int x, int y){	
		super(x,y);
	}
	
	public void Dead(){
	}
	
	public void moveX(int x){
		this.changeX(x);
	}
	
	public void moveY(int y){
		this.changeY(y);
		
	}
	
	public void moveDiagonal(int x, int y){
		this.moveX(x);
		this.moveY(y);
	}
	
	public void setMap(TileMap map){this.map = map;}
	public TileMap getMap(){return this.map;}
	
	public abstract void nextTurn();
}
