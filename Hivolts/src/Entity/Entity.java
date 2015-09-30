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
		int showx = this.getX();
		this.changeX(x);
		map.setTile(this.getX(), this.getY(), this);
		return x + showx;
	}
	public int moveY(int y){
		map.setTile(this.getX(), this.getY(), null);
		int showy = this.getY();
		this.changeY(y);
		map.setTile(this.getX(), this.getY(), this);
		return showy + y;
	}
	public void moveDiagonal(int x, int y){
		this.moveX(x);
		this.moveY(y);
	}
	
	public void setMap(TileMap map){this.map = map;}
	public TileMap getMap(){return this.map;}
	
	abstract void nextTurn();
}
