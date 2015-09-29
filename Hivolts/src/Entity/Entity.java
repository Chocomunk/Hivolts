package Entity;

import Tile.Tile;

public abstract class Entity extends Tile{
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
		moveX(x);
		moveY(y);
	}
	abstract void nextTurn();
	
}