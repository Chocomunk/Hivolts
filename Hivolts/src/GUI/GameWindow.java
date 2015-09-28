package GUI;

import java.awt.*;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

    //Background
    private final Color bg = Color.BLACK;
 
    //Scale of program
    int width = 768+137;
    int height = 768+159;
     
    /**
     * Creates a FlagFrame with a FlagComponent
     */
    public GameWindow () {
        init();
        final GameBoard gb = new GameBoard();
        this.add(gb, BorderLayout.CENTER);
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
    	System.out.println("Frame size: "+this.getWidth()+" | "+this.getHeight());
    }
}
