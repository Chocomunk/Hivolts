package Tile;

public class Tile {
	public int x,y,width,height;
	public Tile(int x, int y, int width, int height){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}	
	public Tile(int x,int y){
		this(x,y,64,64);
	}
}
