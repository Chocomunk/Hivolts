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

	private BufferedImage img;
	
	private enum TileType {MHO, PLAYER, FENCE, NULL};
	private TileType type = TileType.NULL;
	
	private Tile tile;
	
	public ImageHandler(Tile t){
		this.tile = t;
		initType();
		initImage();
	}
	
	private void initType(){
		if(this.tile instanceof Mho){this.type = TileType.MHO;}
		else if(this.tile instanceof Player){this.type = TileType.PLAYER;}
		else if(this.tile instanceof Fence){this.type = TileType.FENCE;}
	}
	
	private void initImage(){
		switch(this.type){
		case MHO:
		    try {img = ImageIO.read(new File("res/mho.png"));} catch (IOException e) {System.out.println("Image Handler Error: ");e.printStackTrace();}
			break;
		case PLAYER:
		    try {img = ImageIO.read(new File("res/player.png"));} catch (IOException e) {System.out.println("Image Handler Error: ");e.printStackTrace();}
			break;
		case FENCE:
			try {img = ImageIO.read(new File("res/fence.png"));} catch (IOException e) {System.out.println("Image Handler Error: ");e.printStackTrace();}
			break;
		case NULL:
			try {img = ImageIO.read(new File(""));} catch (IOException e) {System.out.println("Image Handler Error: ");e.printStackTrace();}
			break;
		}
	}
	
	public void draw(Graphics g){
		g.drawImage(img, this.tile.getX()*74+5, this.tile.getY()*74+5, null);
	}
}
