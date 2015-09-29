package Entity;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{
	public Player(int x, int y){	
		super(x,y);
	}
	public void Dead(){
	}
	public int moveX(int x){
		int showx = this.getX();
		this.changeX(x);
		return showx + x;
	}
	public int moveY(int y){
		int showy = this.getY();
		this.changeY(y);
		return y + showy;
	}
	public void moveDiagonal(int x, int y){
		moveX(x);
		moveY(y);
	}
	public void jump(){
		int x = (int)((Math.random()*10)+1);//1 to 10
		int y = (int)((Math.random()*10)+1);
		
	}
	void nextTurn(){
		
	}
	
	public void draw(Graphics g){
		super.draw(g,Color.GREEN);
	}

}
