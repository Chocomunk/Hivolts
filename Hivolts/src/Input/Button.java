package Input;

import java.awt.Graphics;

import GUI.ImageHandler;
import GUI.ImageType;

/**
 * Represents a Button on the screen
 * @author Alvin On
 * @author Edan Sneh
 * @author Frederic Maa
 */
public class Button{
	
	//UI for button
	private ImageHandler imgh;
	private MouseInputController mic;
	
	//Position variables
	private int posx,posy,width,height;
	
	//Button state variables
	private boolean hovering;
	private boolean holding;
	private boolean valid;
	
	/**
	 * Creates a button at position (x,y)
	 * @param x x-position
	 * @param y y-position
	 */
	public Button(int x, int y){
		this.posx = x;
		this.posy = y;
		imgh = new ImageHandler(ImageType.PLAYER);
		width = imgh.getImage().getWidth();
		height = imgh.getImage().getHeight();
		reset();
	}
	
	/**
	 * Resets all state variables
	 */
	private void reset(){
		hovering = false;
		holding = false;
		valid = false;
		setNone();
	}
	
	/**
	 * Decides whether the mouse is hovering over this button
	 * @param x mouse pos-x
	 * @param y mouse pos-y
	 * @return Whether mouse is over this button
	 */
	public boolean isOver(int x, int y){
		if(valid && (x>posx&&x<posx+width)&&(y>posy&&y<posy+height)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Sets the state of this button to hover
	 */
	public void setHover(){
		imgh.updateImage(ImageType.MHO);
		this.hovering = true;
	}
	
	/**
	 * Sets this button to an empty state
	 */
	public void setNone(){
		imgh.updateImage(ImageType.PLAYER);
		this.hovering = false;
	}
	
	/**
	 * Sets the state of this button to hold
	 */
	public void setHold(){
		imgh.updateImage(ImageType.FENCE);
		this.holding = true;
	}
	
	/**
	 * Undo the hopld state
	 */
	public void setRelease(){
		imgh.updateImage(ImageType.PLAYER);
		this.holding = false;
	}
	
	/**
	 * Draws the button using images
	 * @param g Graphics (given in JFrame)
	 */
	public void draw(Graphics g){imgh.draw(g, posx, posy);}
	
	/**
	 * Called every tick, compares position of button and mouse
	 * @param posx x-pos of mouse
	 * @param posy y-pos of mouse
	 */
	public void tick(int posx, int posy){
		if(valid){
			if(!this.isHovering()&&this.isOver(posx, posy)){
				this.setHover();
			}else if(!this.isOver(posx, posy)&&this.isHovering()){
				this.setNone();
			}else if(!this.isHolding()&&this.isOver(posx, posy)&&mic.isMouseDown()){
				this.setHold();
			}else if(!mic.isMouseDown()&&this.isHolding()&&!this.isOver(posx, posy)){
				this.setRelease();
			}else if(!mic.isMouseDown()&&this.isHolding()&&this.isOver(posx, posy)){
				mic.advanceGame();
				reset();
			}
		}
	}

	//Setters
	/**Sets the MouseInputController of this button
	 * @param mic MouseInputController*/
	public void setMIC(MouseInputController mic){this.mic = mic;}
	/**Sets the Button to a "non-active" state*/
	public void setInvalid(){this.valid = false;}
	/**Sets the button to an "active" state*/
	public void setValid(){this.valid = true;}
	
	//Getters
	/**@return Whether the mouse is hovering over this button*/
	public boolean isHovering(){return this.hovering;}
	/**@return Whether the mosue if being held on this button*/
	public boolean isHolding(){return this.holding;}
}
