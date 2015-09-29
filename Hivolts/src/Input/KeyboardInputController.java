package Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInputController extends KeyAdapter{
	
	private enum movement {UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, SIT, JUMP};
	
	movement currDirection = movement.SIT;
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_W:
			this.currDirection = movement.UP;
		case KeyEvent.VK_X:
			this.currDirection = movement.DOWN;
		case KeyEvent.VK_A:
			this.currDirection = movement.RIGHT;
		case KeyEvent.VK_D:
			this.currDirection = movement.LEFT;
		case KeyEvent.VK_Q:
			this.currDirection = movement.UP_RIGHT;
		case KeyEvent.VK_E:
			this.currDirection = movement.UP_LEFT;
		case KeyEvent.VK_Z:
			this.currDirection = movement.DOWN_RIGHT;
		case KeyEvent.VK_C:
			this.currDirection = movement.DOWN_LEFT;
		case KeyEvent.VK_S:
			this.currDirection = movement.SIT;
		case KeyEvent.VK_J:
			this.currDirection = movement.JUMP;
		}
		
	}
	
}
