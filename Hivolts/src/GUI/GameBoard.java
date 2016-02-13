package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import Core.Hivolts;
import Entity.Player;
import Input.Button;
import Input.KeyboardInputController;
import Input.MouseInputController;
import Tile.Tile;
import Tile.TileMap;

/**
 * Holds and manages all gameobjects
 * @author Alvin On
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
	private Button tryAgainButt;
	private Button continueButt;
	private Button playAgainButt;
	
	//Controls the state of the game
	public enum gameState {WIN, LOSE, PLAYING};
	public gameState currState = gameState.PLAYING;
	
	
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

		tryAgainButt = new Button(333,600,ImageType.TABUTT,ImageType.TABUTT_HV,ImageType.TABUTT_HD);
		continueButt = new Button(333,600,ImageType.CBUTT,ImageType.CBUTT_HV,ImageType.CBUTT_HD);
		playAgainButt = new Button(333,600,ImageType.PABUTT,ImageType.PABUTT_HV,ImageType.PABUTT_HD);
		tryAgainButt.setMIC(mic);
		continueButt.setMIC(mic);
		playAgainButt.setMIC(mic);
		
		reset();
    }
    
    /**
     * Resets the game to a initial state
     */
    void reset(){
    	if(Hivolts.level==4){
    		Hivolts.reset();
    	}
    	
		tryAgainButt.setInvalid();
		continueButt.setInvalid();
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
    	
    	//Calcualates screen ratio values
    	scale = this.getWidth()<this.getHeight()?this.getWidth():this.getHeight();
    	scale /= 888;
    	size = this.getWidth()>this.getHeight()?this.getWidth():this.getHeight();
    	xscale = size==this.getWidth()?1:0;
    	yscale = size==this.getHeight()?1:0;
    	
    	//Code to smooth pictures on scaling, idea from: http://stackoverflow.com/questions/25368499/how-can-i-draw-smooth-buffered-images-in-java
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	
    	//Only draw game objects if we are playing
    	if(this.currState==gameState.PLAYING){
    		//Draws the background
        	this.imgh.draw(g, 0, 0);

        	//Draws the grid
        	g.setColor(Color.BLACK);
        	for(int i=0; i<=12; i++){
        		int xpos = (int)((i*74*scale)+xscale*((size/2)-(444*scale)));
        		int ypos = (int)((i*74*scale)+yscale*((size/2)-(444*scale)));
        		int sizeShift = (int)((size/2)-444*scale);
        		g.drawLine(xpos, sizeShift*yscale, xpos, (int)(scale*888)+sizeShift*yscale);
        		g.drawLine(sizeShift*xscale, ypos, (int)(scale*888)+sizeShift*xscale, ypos);
        	}
        	
        	map.draw(g,scale,size,xscale,yscale);
        	
        	g.setColor(Color.BLUE);
        	g.drawString("Level: "+Hivolts.level, 25, 35);
    	}else{
    		//Draws endgame screen
    		this.imgh.drawExact(g, 0, 0, (int)(this.imgh.getImage().getWidth()*(this.getWidth()/888.0)), (int)(this.imgh.getImage().getHeight()*(this.getHeight()/888.0)));

    		if(this.currState == gameState.LOSE){
        		tryAgainButt.draw(g,scale,size,xscale,yscale);
    		}else if(this.currState == gameState.WIN){
    			if(Hivolts.level<4){
    	    		continueButt.draw(g,scale,size,xscale,yscale);
    			}else{
    	    		playAgainButt.draw(g,scale,size,xscale,yscale);
    			}
    		}
    	}
    }
	
    /**
     * Called every tick
     */
    public void Update(){
    	if(currState == gameState.PLAYING){
    		map.tick();
    	}else{
    		tryAgainButt.tick(MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x, 
    				MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y,
    				scale);
    		continueButt.tick(MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x, 
    				MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y,
    				scale);
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
		Hivolts.reset();
		this.currState=gameState.LOSE;
		this.imgh.updateImage(ImageType.LOSE);
		tryAgainButt.setValid();
	}
	/**
	 * Sets the gamestate to Win
	 * */
	public void Win(){
		Hivolts.passLevel();
		this.currState=gameState.WIN;
		
		if(Hivolts.level<4){
			continueButt.setValid();
			this.imgh.updateImage(ImageType.CONTINUE);
		}else{
			playAgainButt.setValid();
			this.imgh.updateImage(ImageType.WIN);
		}
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
