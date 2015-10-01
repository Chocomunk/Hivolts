package Entity;

import Tile.Tile;
import Tile.TileMap;

public abstract class Entity extends Tile{
	private TileMap map;
	
	public Entity(int x, int y){	
		super(x,y);
	}

	public  void Die(){
		this.getMap().delTile(this.getX(), this.getY());
	}
	
	public void moveX(int x){
		this.changeX(x);
	}
	
	public void moveY(int y){
		this.changeY(y);
		
	}
	
	public void moveDiagonal(int x, int y){
		this.setX(this.getX()+x);
		this.setY(this.getY()+y);
	}
	
	public void setMap(TileMap map){this.map = map;}
	public TileMap getMap(){return this.map;}
	
	public abstract void nextTurn();
}
