package Entity;

import Tile.Fence;
import Tile.Tile;

/**
 * Represents any movable tile/gameobject on the grid
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 * @see Tile
 */
public abstract class Entity extends Tile{
	
	/**
	 * Instantiates an entity
	 * @param x X-pos on grid
	 * @param y Y-pos on grid
	 */
	public Entity(int x, int y){super(x,y);}

	/**
	 * Abstract class, called after a turn finishes
	 */
	public abstract void nextTurn();
	
	/**
	 * Called when Entity dies
	 */
	public void die(){
		this.setValid(false);
		this.getMap().delTile(this.getX(), this.getY());
	}
	
	/**
	 * Confirms whether the movement lands onto a fence
	 * @param x Amount to move horizontally
	 * @param y Amount to move vertically
	 * @return Whether the movement is safe from fences
	 */
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
	
	/**
	 * Move the Entity diagonally
	 * @param x Amount to move horizontally
	 * @param y Amount to move vertically
	 */
	public void moveDiagonal(int x, int y){
		if(this.isValid()&&this.checkDeath(x,y)){
			this.changeX(x);
			this.changeY(y);
		}
	}

	/**
	 * Move the Entity horizontally
	 * @param x Amount to move
	 */
	public void moveX(int x){if(this.isValid()&&this.checkDeath(x,0)){this.changeX(x);}}
	/**
	 * Move the Entity vertically
	 * @param y Amount to move
	 */
	public void moveY(int y){if(this.isValid()&&this.checkDeath(0,y)){this.changeY(y);}}
}
