package Tile;

import java.awt.Color;
import java.awt.Graphics;

import GUI.ImageHandler;

public class Tile {
	
	private TileMap map;
	private int x,y,width,height,old_x,old_y;

	private boolean valid = true;
	private ImageHandler imgh;
	
	public Tile(int x, int y, int width, int height){
		this.imgh = new ImageHandler(this);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.old_x=x;this.old_y=y;
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
	
	public void draw(Graphics g, int x, int y){
		if(this.isValid()){
			imgh.draw(g,x,y);
		}
	}
	
	public void draw(Graphics g){this.draw(g,this.getX()*74+5,this.getY()*74+5);}
	
	public void tick(Graphics g){
		if(old_x!=x || old_y!=y){
			if(Math.abs(x-old_x)<6){old_x=x;}
			else{old_x-=6*normalize(old_x-x);}
			if(Math.abs(y-old_y)<6){old_y=y;}
			else{old_y-=6*normalize(old_y-y);}
			
			this.draw(g,old_x,old_y);
		}
	}
	
	public void setGrid(int x, int y) {
		this.getMap().getGrid()[this.x][this.y]=null;
		this.getMap().getGrid()[x][y]=this;
	}
	

	public static int normalize(int x){
		if(x==0){return x;}
		else{return x/Math.abs(x);}
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
