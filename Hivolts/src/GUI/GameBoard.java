package GUI;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Entity.Player;
import Input.KeyboardInputController;
import Tile.Tile;
import Tile.TileMap;

public class GameBoard extends JComponent{

	private KeyboardInputController kbic;
	private Player player;
	private TileMap map;
	
	public enum gameState {WIN, LOSE, PLAYING};
	private gameState currState = gameState.PLAYING;
	
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
    	map.setBoard(this);
    	boolean playerOnBoard = false;
    	while(!playerOnBoard){
    		int x = (int) (Math.random()*10)+1;
        	int y = (int) (Math.random()*10)+1;
        	
        	if(!(map.getTile(x, y) instanceof Tile)){
//        		System.out.println(x+","+y);
        		player = new Player(x,y);
        		player.setMap(map);
        		map.setPlayer(player);
        		playerOnBoard = true;
        		player.init();
        	}
    	}
    }
    
    public void paint(Graphics g) {
    	switch (currState){
    	case PLAYING:
//        	int w = 0,h =0;
        	for(int x=0; x<12; x++){
//        		w+=74;
//        		h=0;
        		for(int y=0; y<12; y++){
//        			h+=74;
        			g.setColor(Color.LIGHT_GRAY);
        			g.fillRect(x*74, y*74, 74, 74);
        			g.setColor(Color.BLUE);
        			g.drawRect(x*74, y*74, 74, 74);
        		}
        	}
//        	System.out.println("Expected Calculation: "+w+" | "+h);
//        	System.out.println("Acctual Calculation: "+this.getWidth()+" | "+this.getHeight());
        	map.Draw(g);
    	case WIN:
    		/**
    		 * WIN STATE HERE
    		 */
    		System.out.println("We won!");
    	case LOSE:
    		/**
    		 * LOSE STATE HERE
    		 */
    		System.out.println("We Lost!");
    	}
    }
    
    public void Update(){
    	if(currState == gameState.PLAYING){
//    		System.out.println("Update board ticked");
        	map.tick();
    	}
    }
    
    public void passTurn(){
		map.nextTurn();
		this.getMap().getBoard().repaint();
    }

	public KeyboardInputController getController() {return kbic;}
	public Player getPlayer() {return player;}
	public TileMap getMap() {return map;}
	public void Lose(){this.currState=gameState.LOSE; JOptionPane.showMessageDialog(null, "We WIN boys");}
	public void Win(){this.currState=gameState.WIN; JOptionPane.showMessageDialog(null, "We LOSE boys");}
}
