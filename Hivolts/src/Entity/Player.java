package Entity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import Input.KeyboardInputController;
import Tile.Fence;
import Tile.Tile;

public class Player extends Entity{
	
	KeyboardInputController KIC;
	KeyboardInputController.movement direction;
	
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
//		System.out.println("dir resetting");
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
//				int p1 = 0,p2 = 0;
//				if(otherTile!=null){p1=otherTile.getX();p2=otherTile.getY();}
//				System.out.println(otherTile+": "+x+","+y+" "+p1+","+p2);
				if(otherTile instanceof Mho && ((Mho) otherTile).isValid()){
					this.die(); 
				}else{
					this.setX(x);
					this.setY(y);
					this.resetDir();
					this.updateScreen();
				}
			}
		}
	}
	
	public void die(){
		super.die();
		this.getMap().Lose();
		this.updateScreen();
	}
	
	void passTurn(){
		this.getMap().getBoard().passTurn();
	}
	
	public void nextTurn(){
		resetDir();
	}
	
	public void draw(Graphics g){
		if(this.isValid()){super.draw(g,Color.RED);}
	}
	
	public void tick(){
		if(this.isValid()){

			updateDIR();
			
			boolean up = this.getY()>0;
			boolean down = this.getY()<this.getMap().getGrid()[0].length-1;
			boolean right = this.getX()<this.getMap().getGrid().length-1;
			boolean left = this.getX()>0;
			
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
