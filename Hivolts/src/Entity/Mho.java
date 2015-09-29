package Entity;

import java.awt.Color;
import java.awt.Graphics;

public class Mho extends Entity{
	public Mho(int x, int y){
		super(x, y);
	}
	
	public void draw(Graphics g){
		super.draw(g,Color.RED);
	}

	@Override
	void nextTurn() {
		// TODO Auto-generated method stub
		
	}
}
