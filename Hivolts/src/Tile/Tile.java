package Tile;

import java.awt.Graphics;

import Entity.Entity;
import Entity.Player;
import GUI.ImageHandler;

public class Tile {
	
	private TileMap map;
	private int x,y,width,height,old_x,old_y,new_x,new_y;

	private boolean valid = true;
	private ImageHandler imgh;
	
	public Tile(int x, int y, int width, int height){
		this.imgh = new ImageHandler(this);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.setCoords();
		this.resetCoords();
	}

	public void setCoords(){
		this.new_x=this.x*74+5;
		this.new_y=this.y*74+5;
		if(this instanceof Player){System.out.println("WAU");}
	}
	
	void resetCoords(){
		this.old_x=this.new_x;
		this.old_y=this.new_y;
	}
	
	public Tile(int x,int y){
		this(x,y,64,64);
	}
	
	public void draw(Graphics g, int x, int y){
		if(this.isValid()){
			imgh.draw(g,x,y);
		}
	}
	
	public void draw(Graphics g){this.draw(g,old_x,old_y);}
	
	public void tick(){
		if(this instanceof Entity && this.isValid() && (old_x!=new_x || old_y!=new_y)){
			if(Math.abs(new_x-old_x)<6){old_x=new_x;}
			else{old_x-=6*normalize(old_x-new_x);}
			if(Math.abs(new_y-old_y)<6){old_y=new_y;}
			else{old_y-=6*normalize(old_y-new_y);}
		}
		if(this instanceof Player){System.out.println(old_x+","+old_y+" "+new_x+","+new_y);}
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
	public void setX(int x) {this.x=x;this.setCoords();}
	public void setY(int y) {this.y=y;this.setCoords();}
	public void setMap(TileMap map){this.map = map;}
	public boolean isValid(){return this.valid;}
	public void setValid(boolean valid){this.valid = valid;}
}
