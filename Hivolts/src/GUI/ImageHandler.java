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

public class ImageHandler {

	//Image of this object
	private BufferedImage img;
	
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
	 */
	public void draw(Graphics g, int x, int y){
		g.drawImage(img,x,y,null);
	}
}
