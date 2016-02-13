
package Tile;

import java.awt.Graphics;

import Entity.Entity;
import GUI.ImageHandler;

/**
 * Represents any Tile object on the grid in Hivolts
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 */
public class Tile {

	//Variables to control dimension and position
	private int x,y,width,height;
	private double ratioy,ratiox,old_x,old_y,new_x,new_y;
	
	//Objects needed to refer to and access behaviors
	private TileMap map;
	private ImageHandler imgh;

	//Boolean variables about the current state of the Tile
	//Says whether the current tile is "active" or "valid"
	private boolean valid = true;
	//Tells the state of animation
	private boolean animation_active = false;
	
	/**
	 * Creates a new Tile of given position and dimension
	 * @param x x-position
	 * @param y y-position
	 * @param width width of Tile
	 * @param height height of Tile
	 */
	public Tile(int x, int y, int width, int height){
		this.imgh = new ImageHandler(this);
		
		this.width = width;
		this.height = height;
		
		this.x = x;
		this.y = y;
		
		this.setCoords();
		this.resetCoords();
	}

	/**
	 * Creates Tile of given position
	 * @param x x-position
	 * @param y y-position
	 */
	public Tile(int x,int y){this(x,y,64,64);}
	
	/**
	 * Draws the Tile given position
	 * @param g Graphics object (given in JFrame)
	 * @param x x-position on screen
	 * @param y y-position on screen
	 * @param scale Scale ratio of the object
	 * @param size Size of the longer dimension of the screen
	 * @param xscl Factor (equal to 1 or 0) of x
	 * @param yscl Factor (equal to 1 or 0) of y
	 */
	public void draw(Graphics g, int x, int y, double scale, double size, int xscl, int yscl){
		if(this.isValid()){
			imgh.draw(g,x,y,scale,size,xscl,yscl, 12);
		}
	}
	
	/**Draws Tile based on information stored in Tile
	 * @param g Graphics object (given in JFrame)
	 * @param scale Scale ratio of the object*/
	public void draw(Graphics g, double scale, double size, int xscl, int yscl){this.draw(g,(int)old_x,(int)old_y, scale,size,xscl,yscl);}
	
	/**
	 * Sets the coordinates of this tile on the screen based on its position on the grid
	 */
	public void setCoords(){
		this.new_x=this.x*74;
		this.new_y=this.y*74;
		
		double ly = this.new_y-this.old_y;
		double lx = this.new_x-this.old_x;
		double len = Math.abs(ly)>Math.abs(lx) ? Math.abs(ly):Math.abs(lx);
		
		ratiox = lx/len;
		ratioy = ly/len;
	}
	
	/**
	 * Resets positional variables for animation
	 */
	void resetCoords(){
		this.old_x=this.new_x;
		this.old_y=this.new_y;
	}
	
	/**
	 * Sets this tiles position on the grid
	 * @param x new x-position
	 * @param y new y-position
	 */
	public void setGrid(int x, int y) {
		this.getMap().getGrid()[this.x][this.y]=null;
		this.getMap().getGrid()[x][y]=this;
	}
	
	/**
	 * Returns the normalized form of an int (used in movement behaviors)
	 * @param x Integer to be processed
	 * @return Normalized integer value
	 */
	public static int normalize(int x){
		if(x==0){return x;}
		else{return x/Math.abs(x);}
	}

	//Methods to control movement on grid
	/**Changes position on the horizontal axis
	 * @param x Amount to change by*/
	public void changeX(int x) {this.setX(this.x+x);}
	/**Changes position on the vertical axis
	 * @param y Amount to change by*/
	public void changeY(int y) {this.setY(this.y+y);}
	/**Changes position on the grid
	 * @param x Amount to change horizontally
	 * @param y Amount to change vertically*/
	public void changeGrid(int x, int y) {this.setGrid(this.x+x, this.y+y);}
	
	/**
	 * Called every tick, manages visual positions for animation
	 */
	public void tick(){
		if(this instanceof Entity && this.isValid() && (old_x!=new_x || old_y!=new_y)){
			animation_active = true;
			
			if(Math.abs(new_x-old_x)<6){old_x=new_x;}
			else{old_x+=6*ratiox;}
			if(Math.abs(new_y-old_y)<6){old_y=new_y;}
			else{old_y+=6*ratioy;}
			
		}else{animation_active = false;}
	}

	//Setters
	/**Sets the x-value on grid
	 * @param x x-position*/
	public void setX(int x) {this.x=x;this.setCoords();}
	/**Sets the y-value on grid
	 * @param y y-position*/
	public void setY(int y) {this.y=y;this.setCoords();}
	/**Sets the Tiles state
	 * @param valid Boolean condition of Tiles state*/
	public void setValid(boolean valid){this.valid = valid;}
	/**Sets the TileMap this Tile belongs to
	 * @param map Tile Map*/
	public void setMap(TileMap map){this.map = map;}
	
	//Getters
	/**@return The x-position of this Tile*/
	public int getX() {return x;}
	/**@return The y-position of this Tile*/
	public int getY() {return y;}
	/**@return The Width of this Tile*/
	public int getWidth() {return width;}
	/**@return The height of this Tile*/
	public int getHeight() {return height;}
	/**@return Whether this Tile is valid*/
	public boolean isValid(){return this.valid;}
	/**@return Whether this Tile is animating*/
	public boolean isAnimationActive(){return this.animation_active;}
	/**@return The map this Tile belongs to*/
	public TileMap getMap(){return this.map;}
}
