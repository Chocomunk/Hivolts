package Entity;

import Tile.Fence;
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
		this.setX(this.getX()+x);
		this.setY(this.getY()+y);
	}
	
	public void setMap(TileMap map){this.map = map;}
	public TileMap getMap(){return this.map;}
	
	public abstract void nextTurn();
	
	public void fencecheck(){
		boolean shouldhero = false;
		if(this.getMap().getTile(this.getX(), this.getY()+down) instanceof Fence){shouldhero=true;}
		if(this.getMap().getTile(x+right, y) instanceof Fence){shouldhero=true;}
		if(this.getMap().getTile(x+right, y+down) instanceof Fence){shouldhero=true;}
		if(shouldhero = true){Dead();}
	}
}
