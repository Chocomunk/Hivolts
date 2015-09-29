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
		int showx = this.getX();
		this.changeX(x);
		return x + showx;
	}
	public int moveY(int y){
		int showy = this.getY();
		this.changeY(y);
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
