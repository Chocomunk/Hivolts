package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Mho;
import Entity.Player;
import Tile.Fence;
import Tile.Tile;

/**
 * Handles images of one object
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 */
public class ImageHandler {

	//Image of this object
	private BufferedImage img;
	private String location;
	
	//Type of visible object
	private ImageType type = ImageType.NULL;
	
	/**
	 * Constructor used to handle images of all Tile objects
	 * @param t Tile object to be drawn
	 */
	public ImageHandler(Tile t){
		initTypeTile(t);
		initImage();
	}
	
	//Constructors for non-Tiles
	/**Default constructor, creates ImageHandler of type NULL*/
	public ImageHandler(){this(ImageType.NULL);}
	/**Main constructor for non-tiles
	 * @param t Intended type for this handler*/
	public ImageHandler(ImageType t){this.updateImage(t);}
	
	/**
	 * Determines type of visible object from instance of Tile
	 * @param t Tile object to check
	 */
	private void initTypeTile(Tile t){
		if(t instanceof Mho){this.type = ImageType.MHO;}
		else if(t instanceof Player){this.type = ImageType.PLAYER;}
		else if(t instanceof Fence){this.type = ImageType.FENCE;}
	}
	
	/**Initializes the image based on the current type*/
	private void initImage(){this.setImage(this.type);}
	
	/**
	 * Sets the image based on a given type
	 * @param t Type of image to change to
	 */
	private void setImage(ImageType t){
		try {
			this.location = t.getLcoation();
			this.img = ImageIO.read(new File(t.getLcoation()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the image and type based on given type
	 * @param type Type of image update to
	 */
	public void updateImage(ImageType type){
		this.type = type;
		this.setImage(type);
	}
	
	/**
	 * Draws the current image
	 * @param g Graphics object (given in JFrame)
	 * @param x x-Position on screen
	 * @param y y-Position on screen
	 * @param scale Scale ratio of the object
	 * @param size Size of the longer dimension of the screen
	 * @param xscl Factor (equal to 1 or 0) of x
	 * @param yscl Factor (equal to 1 or 0) of y
	 * @param incr Multiplier to the dimensions of the image, for position
	 */
	public void draw(Graphics g, int x, int y, double scale, double size, int xscl, int yscl, double incr){
		double w = this.img.getWidth()*scale;
		double h = this.img.getHeight()*scale;
		double xpos = (x*scale)+xscl*((size/2)-(w*incr/2));
		double ypos = (y*scale)+yscl*((size/2)-(h*incr/2));
		g.drawImage(img,(int)(xpos),(int)(ypos),(int)(w),(int)(h),null);
	}
	
	/**
	 * Draws the current image
	 * @param g Graphics object (given in JFrame)
	 * @param x x-Position on screen
	 * @param y y-Position on screen
	 * @param scale Scale ratio of the object
	 */
	public void draw(Graphics g, int x, int y, double scale){this.draw(g, x, y, scale, 0, 0, 0, 0);}

	/**
	 * Draws the current image
	 * @param g Graphics object (given in JFrame)
	 * @param x x-Position on screen
	 * @param y y-Position on screen
	 */
	public void draw(Graphics g, int x, int y){this.draw(g, x, y, 1);}
	
	/**
	 * Draws the image by the exact parameters
	 * @param g Graphics object (given in JFrame)
	 * @param x x-Position on screen
	 * @param y y-Position on screen
	 * @param width Width of image
	 * @param height Height of image
	 */
	public void drawExact(Graphics g, int x, int y, int width, int height){
		g.drawImage(img, x, y, width, height, null);
	}
	
	//Getters
	/**@return The location of the image*/
	public String getLcoation(){return this.location;}
	/**@return The image*/
	public BufferedImage getImage(){return this.img;}
}
