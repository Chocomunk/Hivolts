package Tile;

import java.awt.Color;
import java.awt.Graphics;

import GUI.ImageHandler;

public class Tile {
	
	private TileMap map;
	private int x,y,width,height;

	private boolean valid = true;
	private ImageHandler imgh;
	
	public Tile(int x, int y, int width, int height){
		this.imgh = new ImageHandler(this);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public Tile(int x,int y){
		this(x,y,64,64);
	}
	
//	public void draw(Graphics g){
//		this.draw(g,Color.BLUE);
//	}
//	
//	public void draw(Graphics g, Color c){
//		g.setColor(c);
//		g.fillRect(x*74+5, y*74+5, width, height);
//	}
	
	public void draw(Graphics g){
		if(this.isValid()){
			imgh.draw(g);
		}
	}

	public void setGrid(int x, int y) {
		this.getMap().getGrid()[this.x][this.y]=null;
		this.getMap().getGrid()[x][y]=this;
	}
	
	public void updateScreen(){this.getMap().getBoard().repaint();}
	
	public void changeX(int x) {this.setX(this.x+x);}
	public void changeY(int y) {this.setY(this.y+y);}
	
	public void changeGrid(int x, int y) {this.setGrid(this.x+x, this.y+y);}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public TileMap getMap(){return this.map;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public void setMap(TileMap map){this.map = map;}
	public boolean isValid(){return this.valid;}
	public void setValid(boolean valid){this.valid = valid;}
}
