package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JComponent;

import Entity.Player;
import Input.Button;
import Input.KeyboardInputController;
import Input.MouseInputController;
import Tile.Tile;
import Tile.TileMap;

/**
 * Holds and manages all gameobjects
 * @author Alvin On
 * @author Edan Sneh
 * @author Frederic Maa
 * @see JComponent
 */
@SuppressWarnings("serial")
public class GameBoard extends JComponent{
	
	//Declares game aspects, and UI handlers
	private KeyboardInputController kbic;
	private MouseInputController mic;
	private ImageHandler imgh;
	private Player player;
	private TileMap map;
	private Button playAgainButt;
	
	//Controls the state of the game
	public enum gameState {WIN, LOSE, PLAYING};
	private gameState currState = gameState.PLAYING;
	
	
	//Scale of the game
	private double scale,size;
	private int xscale,yscale;
	
    /**
     * Initializes component and variables
     * @param kbic 
     */
    public GameBoard(KeyboardInputController kbic){
    	this.kbic = kbic;
    	init();
    }
    
    /**
     * Sets up Objects required for the game, and their initial state
     */
    void init(){
    	scale = 1;
    	this.imgh = new ImageHandler(ImageType.BG);
    	this.mic = new MouseInputController(this);
    	this.addMouseListener(mic);

		playAgainButt = new Button(333,600); 
		playAgainButt.setMIC(mic);
		
		reset();
    }
    
    /**
     * Resets the game to a initial state
     */
    void reset(){
		playAgainButt.setInvalid();
		
		imgh.updateImage(ImageType.BG);
		
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
     * Called every tick, draws the background, visual grid, then game objects and buttons
     */
    public void paint(Graphics g) {
    	
    	scale = this.getWidth()<this.getHeight()?this.getWidth():this.getHeight();
    	scale /= 888;
    	size = this.getWidth()>this.getHeight()?this.getWidth():this.getHeight();
    	xscale = size==this.getWidth()?1:0;
    	yscale = size==this.getHeight()?1:0;
    	
    	//Only draw game objects if we are playing
    	if(this.currState==gameState.PLAYING){
        	this.imgh.draw(g, 0, 0);
        	
        	for(int x=0; x<12; x++){
        		for(int y=0; y<12; y++){
//        			g.setColor(Color.DARK_GRAY);
//        			g.fillRect(x*74, y*74, 74, 74);
        			
        			g.setColor(Color.BLACK);
        			g.drawRect((int)((x*74*scale)+xscale*((size/2)-(444*scale))), (int)((y*74*scale)+yscale*((size/2)-(444*scale))), (int)(74*scale), (int)(74*scale));
        		}
        	}
        	map.draw(g,scale,size,xscale,yscale);
    	}else{
    		this.imgh.drawExact(g, 0, 0, (int)(this.imgh.getImage().getWidth()*(this.getWidth()/888.0)), (int)(this.imgh.getImage().getHeight()*(this.getHeight()/888.0)));
    		playAgainButt.draw(g,scale,size,xscale,yscale);
    	}
    }

	/**
	 * Called when mouse is clicked on screen
	 * @param x x-position of mouse
	 * @param y y-position of mouse
	 */
	public void clicked(int x, int y){
		if(playAgainButt.isOver(x, y,scale)){
			newGame();
		}
	}
	
    /**
     * Called every tick
     */
    public void Update(){
    	if(currState == gameState.PLAYING){
    		map.tick();
    	}else{
    		playAgainButt.tick(MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x, 
    				MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y,
    				scale);
    	}
    }
    
    /**
     * Passes the turn for all game objects
     */
    public void passTurn(){map.nextTurn(); }
    
	/**
	 * Resets and starts a new game
	 */
	public void newGame(){this.Play();}
    
    //Gamestate mutators
    /**
     * Sets gamsetate to Playing
     */
    public void Play(){
    	this.currState=gameState.PLAYING;
    	reset();
    }
    /**
     * Sets the gamestate to Lose
     * */
	public void Lose(){
		this.currState=gameState.LOSE;
		this.imgh.updateImage(ImageType.LOSE);
		playAgainButt.setValid();
	}
	/**
	 * Sets the gamestate to Win
	 * */
	public void Win(){
		this.currState=gameState.WIN;
		this.imgh.updateImage(ImageType.WIN);
		playAgainButt.setValid();
	}
	
	//Accessors
	/**@return The KeyboardInputController used throughout the program*/
	public KeyboardInputController getKeyboardController() {return kbic;}
	/**@return The sole player object of the program*/
	public Player getPlayer() {return player;}
	/**@return The sole map object of the program*/
	public TileMap getMap() {return map;}
	/**@return The scale of the program*/
	public double getScale() {return scale;}
}
