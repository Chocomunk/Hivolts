package Entity;

import Tile.Tile;

public abstract class Entity extends Tile{
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
		moveX(x);
		moveY(y);
	}
	
	abstract void nextTurn();
	
}