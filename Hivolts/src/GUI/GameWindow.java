package GUI;

import java.awt.*;

import javax.swing.JFrame;

import Input.KeyboardInputController;

public class GameWindow extends JFrame{

    //Background
    private final Color bg = Color.BLACK;
    final GameBoard gb;
 
    //Scale of program
    int width = 768+137;
    int height = 768+159;
     
    /**
     * Creates a FlagFrame with a FlagComponent
     * @param kbic 
     */
    public GameWindow (KeyboardInputController kbic) {
        init();
        gb = new GameBoard(kbic);
        this.add(gb, BorderLayout.CENTER);
        this.addKeyListener(kbic);
        width = gb.getWidth()+137;
        height = gb.getHeight()+159;
    }
  
    /**
     * sets size of the window with width and height value
     * with a hard-coded value added to height in order to
     * fit the flag to the screen better at the start
     */
    public void init() {
        setSize(width, height);
        setBackground(bg);
        repaint();
//    	System.out.println("Frame size: "+this.getWidth()+" | "+this.getHeight());
    }
    
    public void Update(){
//		System.out.println("Update window ticked");
    	gb.Update();
    }
}
