package Input;

import java.awt.Graphics;

import GUI.ImageHandler;
import GUI.ImageType;

/**
 * Represents a Button on the screen
 * @author Alvin On
 * @author Frederic Maa
 */
public class Button{
	
	//UI for button
	private ImageHandler imgh;
	private MouseInputController mic;
	
	//Position variables
	private int posx,posy,width,height;
	private double px,py,w,h;
	
	//Button state variables
	private boolean hovering;
	private boolean holding;
	private boolean valid;
	
	//Images variables
	private ImageType normal;
	private ImageType hover;
	private ImageType hold;
	
	/**
	 * Creates a button at position (x,y)
	 * @param x x-position
	 * @param y y-position
	 * @param button default button image
	 * @param hover hover button image
	 * @param hold hold button image
	 */
	public Button(int x, int y, ImageType button, ImageType hover, ImageType hold){
		this.posx = x;
		this.posy = y;
		imgh = new ImageHandler(button);
		width = imgh.getImage().getWidth();
		height = imgh.getImage().getHeight();
		
		this.normal = button;
		this.hover = hover;
		this.hold = hold;
		
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
	 * @param scale Scale ratio of the object
	 * @return Whether mouse is over this button
	 */
	public boolean isOver(int x, int y, double scale){
		if(valid && (x>(px)*scale&&x<(px+w)*scale)&&(y>(py)*scale&&y<(py+h)*scale)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Decides whether the mouse is hovering over this button
	 * @param x mouse pos-x
	 * @param y mouse pos-y
	 * @return Whether mouse is over this button
	 */
//	public boolean isOver(int x, int y){return this.isOver(x, y, 1);}
	
	/**
	 * Sets the state of this button to hover
	 */
	public void setHover(){
		imgh.updateImage(hover);
		this.hovering = true;
	}
	
	/**
	 * Sets this button to an empty state
	 */
	public void setNone(){
		imgh.updateImage(normal);
		this.hovering = false;
	}
	
	/**
	 * Sets the state of this button to hold
	 */
	public void setHold(){
		imgh.updateImage(hold);
		this.holding = true;
	}
	
	/**
	 * Undo the hopld state
	 */
	public void setRelease(){
		imgh.updateImage(normal);
		this.holding = false;
	}
	
	/**
	 * Draws the button using images
	 * @param g Graphics (given in JFrame)
	 * @param scale Scale ratio of the object
	 * @param size Size of the longer dimension of the screen
	 * @param xscl Factor (equal to 1 or 0) of x
	 * @param yscl Factor (equal to 1 or 0) of y
	 */
	public void draw(Graphics g, double scale, double size, int xscl, int yscl){
		double widths = xscl==yscl?888:(scale*888*yscl)+(size*xscl);
		double heights = xscl==yscl?888:(scale*888*xscl)+(size*yscl);
		w = this.imgh.getImage().getWidth()*scale;
		h = this.imgh.getImage().getHeight()*scale;
		px = (widths/2)-(w/2);
		py = heights*(posy/888.0);
		
		imgh.drawExact(g, (int)px, (int)py, (int)w, (int)h);
	}
	
	/**
	 * Called every tick, compares position of button and mouse
	 * @param posx x-pos of mouse
	 * @param posy y-pos of mouse
	 */
	public void tick(int posx, int posy, double scale){
		posx*=scale;
		posy*=scale;
		if(valid){
			if(!this.isHovering()&&this.isOver(posx, posy, scale)){
				this.setHover();
			}else if(!this.isOver(posx, posy, scale)&&this.isHovering()){
				this.setNone();
			}else if(!this.isHolding()&&this.isOver(posx, posy, scale)&&mic.isMouseDown()){
				this.setHold();
			}else if(!mic.isMouseDown()&&this.isHolding()&&!this.isOver(posx, posy, scale)){
				this.setRelease();
			}else if(!mic.isMouseDown()&&this.isHolding()&&this.isOver(posx, posy, scale)){
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
	/**@return Whether the mouse if being held on this button*/
	public boolean isHolding(){return this.holding;}
}
