package Tile;

import java.awt.Graphics;
import Entity.Mho;
import Entity.Player;
import GUI.GameBoard;
import GUI.GameBoard.gameState;

/**
 * Virtual representation of game grid
 * @author Alvin On
 * @author Frederic Maa
 */
public class TileMap {
	
	//External objects
	private GameBoard board;
	private Player player;

	//Grid objects
	private Tile[][] grid;
	private Mho[] mhos = new Mho[12];
	private Fence[] fences = new Fence[64];
	
	/**
	 * Create a TileMap
	 * @param x x-dimension
	 * @param y y-dimension
	 */
	public TileMap(int x, int y){initializeMap(x,y);}
	
	/**
	 * Generates map objects
	 * @param x x-dimension of grid
	 * @param y y-dimension of grid
	 */
	void initializeMap(int x, int y){
		generateFences(x,y);
		generateMhos(x,y);
	}
	
	/**
	 * Generates fences, then places them onto the grid
	 * @param x x-dimension of grid
	 * @param y y-dimension of grid
	 */
	public void generateFences(int x, int y){
		//Creates an empty grid
		grid = new Tile[x][y];
		//Count of wall to create
		int wall_left = 44;
		
		//Create border calls
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[i].length; j++){
				if((i==0 || j==0)||(i==x-1 || j==y-1)){
					Fence f = new Fence(i,j);
					grid[i][j] = f;
					wall_left -= 1;
					fences[wall_left+20] = f;
				}
			}
		}
		
		//Count of fences to create
		int fences_left = 20;
		//Generates 20 fences randomly on the board
		while(fences_left>0){
			int rand_x = (int) (Math.random()*grid.length);
			int rand_y = (int) (Math.random()*grid[0].length);
			if(grid[rand_x][rand_y] == null && fences_left>0 && Math.random() < 0.05){
				Fence f = new Fence(rand_x,rand_y);
				grid[rand_x][rand_y] = f;
				fences_left-=1;
				fences[fences_left] = f;
			}
		}	
	}
	
	/**
	 * Generates Mhos, then places them onto the grid
	 * @param x x-dimension of grid
	 * @param y y-dimension of grid
	 */
	public void generateMhos(int x, int y){
		//Mhos left to create
		int mhos_left = mhos.length;
		//Generates 12 mhos randomly on the board
		while(mhos_left>0){
			int rand_x = (int) (Math.random()*grid.length);
			int rand_y = (int) (Math.random()*grid[0].length);
			if(grid[rand_x][rand_y] == null && mhos_left>0 && Math.random() < 0.05){
				mhos_left-=1;
				Mho thisHo = new Mho(rand_x,rand_y);
				thisHo.setMap(this);
				grid[rand_x][rand_y] = thisHo;
				thisHo.setIndex(mhos_left);
				mhos[mhos_left] = thisHo;
			}
		}	
	}

	/**
	 * Sets the games state to Lose
	 */
	public void Lose(){this.board.Lose();}
	
	/**
	 * Advances the turn for all objects
	 */
	public void nextTurn(){
		//Tries to force Mhos to make a possible move, as sometimes possible moves are missed
		boolean canMove = true;
		while(canMove){
			canMove = false;
			for(Mho m: mhos){
				if(m!=null&&!m.hasMoved()&&m.isValid()&&m.getMoveTimes()<5){
					m.nextTurn();
					canMove=true;
				}
			}
		}
		//Advances Player turn
		this.player.nextTurn();
		//Resets movement information for moved Mhos
		for(Mho m: mhos){
			if(m!=null){
				m.resetMoved();
				m.resetTimes();
			}
		}
	}
	
	/**
	 * Draws all objects on the map
	 * @param g Graphics object (given in JFrame)
	 * @param scale Scale ratio of the object
	 * @param size Size of the longer dimension of the screen
	 * @param xscl Factor (equal to 1 or 0) of x
	 * @param yscl Factor (equal to 1 or 0) of y
	 */
	public void draw(Graphics g, double scale, double size, int xscl, int yscl){
		for(Fence f: fences){f.draw(g,scale,size,xscl,yscl);}
		for(int i=0; i<mhos.length; i++){
			if(mhos[i].isValid()){mhos[i].draw(g,scale,size,xscl,yscl);}
		}
		player.draw(g,scale,size,xscl,yscl);
	}
	
	/**
	 * Called every tick
	 */
	public void tick(){
		//Checks for a win state (all Mhos dead)
		boolean mhos_exist = false;
		for(Mho m: mhos){if(m.isValid()){mhos_exist=true;}}
		
		//If win state achieved then set the gamestate to a Win
		if(!mhos_exist && (this.board.currState == gameState.PLAYING)){System.out.println("winning");this.board.Win(); this.board.repaint();}
		//Otherwise, tick all tiles on the grid
		else{
			for(Tile[] i: grid){
				for(Tile t: i){
					if(t!=null){
						t.tick();
					}
				}
			}
		}
		
		//Tick the player
		player.tick();
	}
	
	//Setters
	/**Sets the Tile at a position on the grid
	 * @param x x-position
	 * @param y y-position
	 * @param t New tile*/
	public void setTile(int x, int y, Tile t){grid[x][y] = t;}
	/**Deletes a tile on the grid
	 * @param x x-position
	 * @param y y-position*/
	public void delTile(int x, int y){grid[x][y] = null;}
	/**Deletes the player from the map*/
	public void delPlayer(){this.player = null;}
	/**Deletes an Mho
	 * @param index Index of the Mho*/
	public void delMho(int index){mhos[index] = null;}
	/**Sets the player of the map
	 * @param p Player*/
	public void setPlayer(Player p){this.player = p;}
	/**Sets the GameBoard
	 * @param b GameBoard*/
	public void setBoard(GameBoard b){this.board = b;}
	
	//Getters
	/**@return The tile at position (x,y)*/
	public Tile getTile(int x, int y){return grid[x][y];}
	/**@return The grid of the TileMap*/
	public Tile[][] getGrid(){return this.grid;}
	/**@return The list of Mhos*/
	public Mho[] getMhos(){return this.mhos;}
	/**@return The GameBoard*/
	public GameBoard getBoard(){return this.board;}
	/**@return The Player*/
	public Player getPlayer(){return this.player;}
}
