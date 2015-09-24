package GUI;

import java.awt.*;
import javax.swing.JComponent;

public class GameBoard extends JComponent{

    /**
     * Initializes component and variables
     */
    public GameBoard(){
        repaint();
    }
     
    public void paint(Graphics g) {
    	
    	int w = 0,h =0;
    	
    	for(int x=0; x<12; x++){
    		w+=64;
    		h=0;
    		for(int y=0; y<12; y++){
    			h+=64;
    			g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(x*64, y*64, 64, 64);
    			g.setColor(Color.BLUE);
    			g.drawRect(x*64, y*64, 64, 64);
    		}
    	}
    	
    	System.out.println("Expected Calculation: "+w+" | "+h);
    	System.out.println("Acctual Calculation: "+this.getWidth()+" | "+this.getHeight());
    }
}
