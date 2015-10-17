package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Manages keypresses in the entire game
 * @author Alvin On
 * @author Edan Sneh
 * @author Frederic Maa
 * @see KeyAdapter
 */
public class KeyboardInputController extends KeyAdapter{
	
	//Variables to control the current direction of movement
	/**Defines possible directions of movement*/
	public enum movement {UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, SIT, JUMP, NULL};
	private movement currDirection = movement.SIT;
	
	/**
	 * Called when a key is released, changes direction of motion based on the key
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_W:
			this.currDirection = movement.UP;
			break;
		case KeyEvent.VK_X:
			this.currDirection = movement.DOWN;
			break;
		case KeyEvent.VK_A:
			this.currDirection = movement.LEFT;
			break;
		case KeyEvent.VK_D:
			this.currDirection = movement.RIGHT;
			break;
		case KeyEvent.VK_Q:
			this.currDirection = movement.UP_LEFT;
			break;
		case KeyEvent.VK_E:
			this.currDirection = movement.UP_RIGHT;
			break;
		case KeyEvent.VK_Z:
			this.currDirection = movement.DOWN_LEFT;
			break;
		case KeyEvent.VK_C:
			this.currDirection = movement.DOWN_RIGHT;
			break;
		case KeyEvent.VK_S:
			this.currDirection = movement.SIT;
			break;
		case KeyEvent.VK_J:
			this.currDirection = movement.JUMP;
			break;
		}
	}
	
	//Controls the direction of motion
	/**Resets the direction to NULL, meaning no direction*/
	public void resetDir(){this.setDirection(movement.NULL);}
	/**Sets the direction to a given direction*/
	public void setDirection(movement dir){this.currDirection = dir;}
	
	/**@return The current direction of motion*/
	public movement getDirection(){return this.currDirection;}
	
}
