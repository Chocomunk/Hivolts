package GUI;

import java.awt.*;

import javax.swing.JFrame;

import Input.KeyboardInputController;

/**
 * Holds and manages all graphical objects/settings
 * @author Alvin On
 * @author Edan Sneh
 * @author Frederic Maa
 * @see JFrame
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame{

    //Background
    private final Color bg = Color.BLACK;
    final GameBoard gb;
 
    //Size of program, hardcoded values to fit components better
    int width = 768+127;
    int height = 768+149;
     
    /**
     * Creates a window to host the game in, and adds the KeyboardInputController
     * @param kbic 
     */
    public GameWindow (KeyboardInputController kbic) {
    	super("Hivolts: A remake by Edan Sneh, Frederic Maa, and Alvin On");
        init();
        
        gb = new GameBoard(kbic);
        this.add(gb, BorderLayout.CENTER);
        this.addKeyListener(kbic);
    }
  
    /**
     * Sets options for the window
     */
    public void init() {
        setSize(width, height);
        setBackground(bg);
        this.setSize(888-11, 888+11);
        repaint();
    }
    
    /**
     * Called every tick, updates all objects on local gameboard
     */
    public void Update(){
    	gb.Update();
    }
}
