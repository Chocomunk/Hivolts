package Entity;

import Input.KeyboardInputController;
import Tile.Fence;
import Tile.Tile;

public class Player extends Entity{
	
	KeyboardInputController KIC;
	KeyboardInputController.movement direction;
	
	private boolean death_activated = false;
	
	public Player(int x, int y){	
		super(x,y);
	}
	
	public void init(){
		this.KIC = this.getMap().getBoard().getController();
		resetDir();
	}
	
	void updateDIR(){
		direction = KIC.getDirection();
	}
	
	void resetDir(){
		this.direction = KeyboardInputController.movement.NULL;
		KIC.resetDir();
	}
	
	public void jump(){
		boolean fencePossible = true;
		while(fencePossible){
			int x = (int)((Math.random()*11)+1);
			int y = (int)((Math.random()*11)+1);
			if(!(this.getMap().getTile(x,y) instanceof Fence)){
				fencePossible = false;
				Tile otherTile = this.getMap().getGrid()[x][y];
				if(otherTile instanceof Mho && ((Mho) otherTile).isValid()){
					this.die(); 
				}
				this.setX(x);
				this.setY(y);
				this.resetDir();
			}
		}
	}
	
	public void die(){
		this.death_activated = true;
	}
	
	public void activateDeath(){
		super.die();
		this.getMap().Lose();
	}
	
	void passTurn(){
		this.getMap().getBoard().passTurn();
	}
	
	public void nextTurn(){
		resetDir();
	}
	
	public void tick(){
		super.tick();
		if(this.death_activated&&!this.isAnimationActive){
			this.activateDeath();
		}else if(this.isValid()){

			updateDIR();
			
			boolean up = this.getY()>0;
			boolean down = this.getY()<this.getMap().getGrid()[0].length-1;
			boolean right = this.getX()<this.getMap().getGrid().length-1;
			boolean left = this.getX()>0;
			
			if(this.direction != KeyboardInputController.movement.NULL){
				if(this.direction !=KeyboardInputController.movement.JUMP){
					System.out.println("YOLO BOYS WE BACK HERE");
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
