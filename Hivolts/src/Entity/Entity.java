package Entity;

import java.awt.Color;
import java.awt.Graphics;

import Tile.Fence;
import Tile.Tile;

public abstract class Entity extends Tile{
	
	
	public Entity(int x, int y){	
		super(x,y);
	}

	public void die(){
		this.setValid(false);
		this.getMap().delTile(this.getX(), this.getY());
	}
	
	public void moveX(int x){
		if(this.isValid()&&this.checkDeath(x,0)){this.changeX(x);}
	}
	
	public void moveY(int y){
		if(this.isValid()&&this.checkDeath(0,y)){this.changeY(y);}
	}
	
	public void moveDiagonal(int x, int y){
		if(this.isValid()&&this.checkDeath(x,y)){
			this.changeX(x);
			this.changeY(y);
		}
	}
	
	public boolean checkDeath(int x, int y){
		if(this.getMap().getGrid()[this.getX()+x][this.getY()+y] instanceof Fence){
			this.die();
			return false;
		}else{
			if(this instanceof Mho){
				this.changeGrid(x,y);
			}
			return true;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(this.getX()*74+5, this.getY()*74+5, this.getWidth(), this.getHeight());
	}
	
	public abstract void nextTurn();
}
