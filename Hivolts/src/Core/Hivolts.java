package Core;

import GUI.GameWindow;
import Input.KeyboardInputController;

/**
 * <h1>Hivolts: A remake by Frederic Maa, and Alvin On</h1>
 * Core class of the program, executes Hivolts
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 */
public class Hivolts {

	//Initializes the static Attributes
	static KeyboardInputController kbic = new KeyboardInputController();
	static GameWindow gw = new GameWindow(kbic);
	
	public static int level;
	
	/* TO BE IMPLEMENTED LATER
	 * from a scale of 0-5 tests how many "challenges" to add in
	 */
	static int difficulty;
	
	/**
	 * Called on running the program, executes the game code.
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		reset();
		
		//Creates a new Updater thread from the Updater class, then starts it.
		Thread updater = new Thread(new Updater());
		updater.start();
		
        gw.setDefaultCloseOperation(gw.EXIT_ON_CLOSE);
        gw.setVisible(true);
	}
	
	public static void reset(){
		level = 1;
	}
	
	/**
	 * Called every tick from the Updater class.
	 */
	public static void Update(){
		gw.Update();
		gw.repaint();
	}
	
	/**Moves to the next level*/
	public static void passLevel(){level++;}
}
