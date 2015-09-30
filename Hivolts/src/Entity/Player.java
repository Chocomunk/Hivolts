package Entity;

import java.awt.Color;
import java.awt.Graphics;
import Input.KeyboardInputController;
public class Player extends Entity{
	
	KeyboardInputController KIC;
	KeyboardInputController.movement direction;
	
	public Player(int x, int y){	
		super(x,y);
	}
	
	public void init(){
		this.KIC = this.getMap().getBoard().getController();
	}
	
	void updateDIR(){
		direction = KIC.getDirection();
	}
	
	public void tick(){
		updateDIR();
		switch(this.direction){
		case UP:
			System.out.println(direction);
			moveY(-1);
			break;
		case DOWN:
			moveY(1);
			break;
		case RIGHT:
			moveX(1);
			break;
		case LEFT:
			moveX(-1);
			break;
		case UP_RIGHT:
			moveDiagonal(-1,1);
			break;
		case UP_LEFT:
			moveDiagonal(-1,-1);
			break;
		case DOWN_RIGHT:
			moveDiagonal(1,1);
			break;
		case DOWN_LEFT:
			moveDiagonal(1,-1);
			break;
		case JUMP:
			jump();
			break;
		case SIT:
			break;
		case NULL:
			break;
		}
		nextTurn();
	}
	
	public void jump(){
		int x = (int)((Math.random()*10)+1);//1 to 10
		int y = (int)((Math.random()*10)+1);
	}
	
	public void Dead(){
		
	}
	
	void nextTurn(){
		
	}
	
	public void draw(Graphics g){
		super.draw(g,Color.GREEN);
	}

}
