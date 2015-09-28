package Tile;

import java.awt.Color;
import java.awt.Graphics;

public class Fence extends Tile{

	public Fence(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g){
		super.draw(g, Color.ORANGE);
	}
}
