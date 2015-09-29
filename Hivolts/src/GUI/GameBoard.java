package GUI;

import java.awt.*;

import javax.swing.JComponent;

import Entity.Player;
import Input.KeyboardInputController;
import Tile.Tile;
import Tile.TileMap;

public class GameBoard extends JComponent{

	private KeyboardInputController kbic;
	private Player player;
	private TileMap map;
	
    /**
     * Initializes component and variables
     * @param kbic 
     */
    public GameBoard(KeyboardInputController kbic){
    	this.kbic = kbic;
    	init();
        repaint();
    }
     
    void init(){
    	map = new TileMap(12,12);
    	boolean playerOnBoard = false;
    	while(!playerOnBoard){
    		int x = (int) (Math.random()*10)+1;
        	int y = (int) (Math.random()*10)+1;
        	
        	if(!(map.getTile(x, y) instanceof Tile)){
//        		System.out.println(x+","+y);
        		player = new Player(x,y);
        		map.placePlayer(x, y, player);
        		playerOnBoard = true;
        	}
    	}
    }
    
    public void paint(Graphics g) {
//    	int w = 0,h =0;
    	for(int x=0; x<12; x++){
//    		w+=74;
//    		h=0;
    		for(int y=0; y<12; y++){
//    			h+=74;
    			g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(x*74, y*74, 74, 74);
    			g.setColor(Color.BLUE);
    			g.drawRect(x*74, y*74, 74, 74);
    		}
    	}
//    	System.out.println("Expected Calculation: "+w+" | "+h);
//    	System.out.println("Acctual Calculation: "+this.getWidth()+" | "+this.getHeight());
    	
    	map.Draw(g);
    }

	public KeyboardInputController getController() {return kbic;}
	public Player getPlayer() {return player;}
	public TileMap getMap() {return map;}
}
