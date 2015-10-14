package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import Entity.Player;
import Input.KeyboardInputController;
import Tile.Tile;
import Tile.TileMap;

@SuppressWarnings("serial")
public class GameBoard extends JComponent{

	//Declares game aspects, and UI handlers
	private KeyboardInputController kbic;
	private ImageHandler imgh;
	private Player player;
	private TileMap map;
	
	//Controls the state of the game
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
    
    /**
     * Sets up Objects required for the game, and their initial state
     */
    void init(){
    	this.imgh = new ImageHandler(ImageType.BG);
    	
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
    
    /**
     * Called every tick, draws the background, visual grid, then game objects
     */
    public void paint(Graphics g) {
    	this.imgh.draw(g, 0, 0);
    	
    	//Only draw game objects if we are playing
    	if(this.currState==gameState.PLAYING){
        	for(int x=0; x<12; x++){
        		for(int y=0; y<12; y++){
//        			g.setColor(Color.DARK_GRAY);
//        			g.fillRect(x*74, y*74, 74, 74);
        			
        			g.setColor(Color.BLACK);
        			g.drawRect(x*74, y*74, 74, 74);
        		}
        	}
        	map.draw(g);
    	}else{
    		
    	}
    }
    
    /**
     * Called every tick, if the game is playing, then update all objects
     */
    public void Update(){if(currState == gameState.PLAYING){map.tick();}}
    
    /**
     * Passes the turn for all game objects
     */
    public void passTurn(){map.nextTurn(); }

    //Gamestate mutators
    /**Sets the gamestate to Lose*/
	public void Lose(){this.currState=gameState.LOSE;this.imgh.updateImage(ImageType.LOSE);}
	/**Sets the gamestate to Win*/
	public void Win(){this.currState=gameState.WIN;this.imgh.updateImage(ImageType.WIN);}
	
	//Accessors
	/**@return The KeyboardInputController used throughout the program*/
	public KeyboardInputController getController() {return kbic;}
	/**@return The sole player object of the program*/
	public Player getPlayer() {return player;}
	/**@return The sole map object of the program*/
	public TileMap getMap() {return map;}
}
