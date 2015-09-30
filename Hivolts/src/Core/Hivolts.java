package Core;

import GUI.GameWindow;
import Input.KeyboardInputController;

public class Hivolts {

	static KeyboardInputController kbic = new KeyboardInputController();
	static GameWindow gw = new GameWindow(kbic);
	
	public static void main(String[] args){
		
		Thread updater = new Thread(new Updater());
		updater.start();
		
        gw.setDefaultCloseOperation(gw.EXIT_ON_CLOSE);
        gw.setVisible(true);
	}
	
	public static void Update(){
//		System.out.println("Update game ticked");
		gw.Update();
	}
	
}
