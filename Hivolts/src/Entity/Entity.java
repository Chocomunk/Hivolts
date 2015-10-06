package Entity;

import javax.swing.JOptionPane;

import Tile.Fence;
import Tile.Tile;

public abstract class Entity extends Tile{
	
	private boolean valid = true;
	
	public Entity(int x, int y){	
		super(x,y);
	}

	public void die(){
		this.setValid(false);
		this.getMap().delTile(this.getX(), this.getY());
	}
	
	public void moveX(int x){
		if(valid&&this.checkDeath(x,0)){this.changeX(x);}
	}
	
	public void moveY(int y){
		if(valid&&this.checkDeath(0,y)){this.changeY(y);}
	}
	
	public void moveDiagonal(int x, int y){
		if(valid&&this.checkDeath(x,y)){
			this.changeX(x);
			this.changeY(y);
		}
	}
	
	public boolean checkDeath(int x, int y){
		if(this.getMap().getGrid()[this.getX()+x][this.getY()+y] instanceof Fence){
			this.die();
			if(this instanceof Player){JOptionPane.showMessageDialog(null, "You died to Fence: "+((Fence)(this.getMap().getGrid()[this.getX()+x][this.getY()+y])).index);}
			return false;
		}else{return true;}
	}
	
	public abstract void nextTurn();
	
	public boolean isValid(){return this.valid;}
	public void setValid(boolean valid){this.valid = valid;}
}
