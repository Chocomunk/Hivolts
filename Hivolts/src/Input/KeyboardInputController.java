package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInputController extends KeyAdapter{
	
	public enum movement {UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, SIT, JUMP, NULL};
	
	movement currDirection = movement.SIT;
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_W:
			this.currDirection = movement.UP;
			break;
		case KeyEvent.VK_X:
			this.currDirection = movement.DOWN;
			break;
		case KeyEvent.VK_A:
			this.currDirection = movement.RIGHT;
			break;
		case KeyEvent.VK_D:
			this.currDirection = movement.LEFT;
			break;
		case KeyEvent.VK_Q:
			this.currDirection = movement.UP_RIGHT;
			break;
		case KeyEvent.VK_E:
			this.currDirection = movement.UP_LEFT;
			break;
		case KeyEvent.VK_Z:
			this.currDirection = movement.DOWN_RIGHT;
			break;
		case KeyEvent.VK_C:
			this.currDirection = movement.DOWN_LEFT;
			break;
		case KeyEvent.VK_S:
			this.currDirection = movement.SIT;
			break;
		case KeyEvent.VK_J:
			this.currDirection = movement.JUMP;
			break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		this.currDirection = movement.NULL;
	}
	
	public movement getDirection(){return this.currDirection;}
	
}
