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
	
	public int moveX(int x){
		map.setTile(this.getX(), this.getY(), null);
		this.changeX(x);
		map.setTile(this.getX(), this.getY(), this);
		
		return this.getX();
	}
	
	public int moveY(int y){
		map.setTile(this.getX(), this.getY(), null);
		this.changeY(y);
		map.setTile(this.getX(), this.getY(), this);

		return this.getY();
	}
	
	public void moveDiagonal(int x, int y){
		this.setX(this.getX()+x);
		this.setY(this.getY()+y);
	}
	
	public void setMap(TileMap map){this.map = map;}
	public TileMap getMap(){return this.map;}
	
	public abstract void nextTurn();
}
