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
        	for(int x=0; x<12; x++){
        		for(int y=0; y<12; y++){
        			g.setColor(Color.DARK_GRAY);
        			g.fillRect(x*74, y*74, 74, 74);
        			g.setColor(Color.BLACK);
        			g.drawRect(x*74, y*74, 74, 74);
        		}
        	}
        	map.draw(g);
        	break;
    	case WIN:
    		/**
    		 * WIN STATE HERE
    		 */
//    		System.out.println("We won!");
    		break;
    	case LOSE:
    		/**
    		 * LOSE STATE HERE
    		 */
//    		System.out.println("We Lost!");
    		break;
    	}
    }
    
    public void Update(){
    	if(currState == gameState.PLAYING){
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
	public void Lose(){this.currState=gameState.LOSE;}
	public void Win(){this.currState=gameState.WIN;}
}
