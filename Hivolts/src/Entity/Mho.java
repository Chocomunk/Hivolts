package Entity;

import Tile.Fence;

public class Mho extends Entity{
	//Index of this Mho, Used to track this Mho
	private int index;
	
	//Variables to control Mhos movement
	private boolean moved = false;
	private int moveTimes = 0;
	

	/**
	 * Instantiates an Mho
	 * @param x X-pos on grid
	 * @param y Y-pos on grid
	 */
	public Mho(int x, int y){super(x, y);}
	
	/**
	 * Called when the turn ends
	 */
	public void nextTurn(){CalcMove();}
	
	private void CalcMove(){
		if(this.isValid()&&this.getMap().getPlayer().isValid()){
			//Stores x and y pos of the player
			int px = this.getMap().getPlayer().getX();
			int py = this.getMap().getPlayer().getY();
			
			//Returns movement information based on position of player
			int horiz = -1*normalize(this.getX()-px);
			int vert = -1*normalize(this.getY()-py);
			
			//Returns the expected new position of this Mho
			int posX = this.getX()+horiz;
			int posY = this.getY()+vert;
			
			//Booleans for position of Fences around this Mho
			boolean hasFenceX = this.getMap().getGrid()[posX][this.getY()] instanceof Fence;
			boolean hasFenceY = this.getMap().getGrid()[this.getX()][posY] instanceof Fence;
			boolean hasFenceDiagonal = this.getMap().getGrid()[posX][posY] instanceof Fence;
			
			//Booleans for positions of other Mhos
			boolean hasMhoX = this.getMap().getGrid()[posX][this.getY()] instanceof Mho && ((Mho)this.getMap().getGrid()[posX][this.getY()]).isValid();
			boolean hasMhoY = this.getMap().getGrid()[this.getX()][posY] instanceof Mho && ((Mho)this.getMap().getGrid()[this.getX()][posY]).isValid();
			boolean hasMhoDiagonal = this.getMap().getGrid()[posX][posY] instanceof Mho && ((Mho)this.getMap().getGrid()[posX][posY]).isValid();
			
			//This has moved
			this.setMoved();
			
			//AI decision code based on movement instruction
			if(vert==0 && !hasMhoX){moveX(horiz);}
			else if(horiz==0 && !hasMhoY){moveY(vert);}
			else if(!hasFenceDiagonal && !hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasFenceY && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasFenceX && !hasMhoX){moveX(horiz);}
			else if(!hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasMhoX){moveX(horiz);}
			//If this couldnt move this time, reset the moved boolean and increment the moveTimes variable
			else{this.resetMoved();moveTimes++;}
			
			//If, after moving, our position in the same as the Player, kill the player
			if(this.getX()==px && this.getY()==py){this.getMap().getPlayer().die();}
		}
	}
	
	//Setters
	/**Sets the index of this Mho*/
	public void setIndex(int index){this.index = index;}
	/**Sets the moved boolean to true*/
	public void setMoved(){this.moved=true;}
	/**Resets the moved boolean to false*/
	public void resetMoved(){this.moved=false;}
	/**Reset the times moved to 0*/
	public void resetTimes(){this.moveTimes=0;}
	
	//Getters
	/**Returns the index of this Mho*/
	public int getIndex(){return this.index;}
	/**Returns the times this Mho has tried to move*/
	public int getMoveTimes(){return this.moveTimes;}
	/**Returns whether this Mho has moved*/
	public boolean hasMoved(){return this.moved;}
}


