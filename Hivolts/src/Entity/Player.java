package Entity;

import Input.KeyboardInputController;
import Tile.Fence;
import Tile.Tile;

/**
 * Represents a Player gameobject in Hivolts
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 * @see Entity
 */
public class Player extends Entity{
	
	//KeyboardInputController of the game, and its direction (movement orientation)
	KeyboardInputController KIC;
	KeyboardInputController.movement direction;
	
	//Whether the death of this player is activated
	private boolean death_activated = false;
	
	/**
	 * Instantiates an Player
	 * @param x X-pos on grid
	 * @param y Y-pos on grid
	 */
	public Player(int x, int y){super(x,y);}
	
	/**
	 * Initializes the player
	 */
	public void init(){
		this.KIC = this.getMap().getBoard().getKeyboardController();
		resetDir();
	}
	
	//Methods to control the games turns
	/**Tells the game to move on to the next turn*/
	void passTurn(){this.getMap().getBoard().passTurn();}
	/**Called when a new turn occurs*/
	public void nextTurn(){resetDir();}
	
	//Methods to control the Players direction
	/**Updates the players direction to the KeyboardInputControllers direction*/
	void updateDIR(){direction = KIC.getDirection();}
	/**Resets the movement of the KeyBoardController and this player to NULL*/
	void resetDir(){
		this.direction = KeyboardInputController.movement.NULL;
		KIC.resetDir();
	}

	//Methods to control the players Death
	/**Called when the player enters a situation to die
	 * Activates the possibility to die*/
	public void die(){this.death_activated = true;}
	/**Called when Death possibility is achieved and favorable*/
	public void activateDeath(){
		super.die();
		this.getMap().Lose();
	}
	
	/**Governs logic of the players jumping*/
	public void jump(){
		//Boolean variable to control the while loop
		boolean fencePossible = true;
		
		//While there is a possibility of a fence on the desired tile, select a new Tile
		while(fencePossible){
			//Random values for the new selected Tile
			int x = (int)((Math.random()*11)+1);
			int y = (int)((Math.random()*11)+1);
			
			if(!(this.getMap().getTile(x,y) instanceof Fence)){
				fencePossible = false;
				Tile otherTile = this.getMap().getGrid()[x][y];
				
				//If the other tile is a Mho then die
				if(otherTile instanceof Mho && ((Mho) otherTile).isValid()){this.die();}
				
				//Moves the Player, then resets the direction
				this.setX(x);
				this.setY(y);
				this.resetDir();
			}
		}
	}
	
	/**
	 * Called every tick updated
	 */
	@SuppressWarnings("incomplete-switch")
	public void tick(){
		//Calls tick for super (controlls animation)
		super.tick();
		//Check whether player can die, if so then die
		if(this.death_activated&&!this.isAnimationActive()){this.activateDeath();}
		else if(this.isValid()&&!this.isAnimationActive()){
			
			updateDIR();
			
			//If movement is within grid bounds
			boolean up = this.getY()>0;
			boolean down = this.getY()<this.getMap().getGrid()[0].length-1;
			boolean right = this.getX()<this.getMap().getGrid().length-1;
			boolean left = this.getX()>0;
			
			//Checks the direction, then moves in that direction
			if(this.direction != KeyboardInputController.movement.NULL){
				if(this.direction !=KeyboardInputController.movement.JUMP){
					switch(this.direction){
					case UP:
						if(up)
							moveY(-1);
						break;
					case DOWN:
						if(down)
							moveY(1);
						break;
					case RIGHT:
						if(right)
							moveX(1);
						break;
					case LEFT:
						if(left)
							moveX(-1);
						break;
					case UP_RIGHT:
						if(up&&right)
							moveDiagonal(1,-1);
						break;
					case UP_LEFT:
						if(up&&left)
							moveDiagonal(-1,-1);
						break;
					case DOWN_RIGHT:
						if(down&&right)
							moveDiagonal(1,1);
						break;
					case DOWN_LEFT:
						if(down&&left)
							moveDiagonal(-1,1);
						break;
					case SIT:
						break;
					}
					passTurn();
				}else{
					jump();
				}
			}
		
		}
	}

}
