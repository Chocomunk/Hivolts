package Tile;

import java.awt.Color;
import java.awt.Graphics;

public class Fence extends Tile{
//	public int index;

	public Fence(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g){
		super.draw(g, Color.ORANGE);
//		g.setColor(Color.WHITE);
//		g.drawString(this.index+"", this.getX()*74 +35, this.getY()*74 +35);
	}
}
