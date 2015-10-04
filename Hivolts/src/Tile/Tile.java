package Tile;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	
	private TileMap map;
	private int x,y,width,height;
	
	public Tile(int x, int y, int width, int height){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public Tile(int x,int y){
		this(x,y,64,64);
	}
	
	public void draw(Graphics g){
		this.draw(g,Color.BLUE);
	}
	
	public void draw(Graphics g, Color c){
		g.setColor(c);
		g.fillRect(x*74+5, y*74+5, width, height);
	}
	
	public void changeX(int x) {this.setX(this.x+x);}
	public void changeY(int y) {this.setY(this.y+y);}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public TileMap getMap(){return this.map;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setMap(TileMap map){this.map = map;}
}
