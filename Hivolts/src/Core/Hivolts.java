package Core;

import GUI.GameWindow;
import Input.KeyboardInputController;

public class Hivolts {

	public static void main(String[] args){
		
		KeyboardInputController kbic = new KeyboardInputController();
		
		GameWindow gw = new GameWindow(kbic);
        gw.setDefaultCloseOperation(gw.EXIT_ON_CLOSE);
        gw.setVisible(true);
	}
	
}
