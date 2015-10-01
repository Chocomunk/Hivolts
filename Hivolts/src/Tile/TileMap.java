package Tile;

import java.awt.Graphics;
import Entity.Entity;
import Entity.Mho;
import Entity.Player;
import GUI.GameBoard;

public class TileMap {
	private GameBoard board;
	private Player player;

	private Tile[][] grid;
	private Mho[] mhos = new Mho[12];
	private Fence[] fences = new Fence[64];
	
	public TileMap(int x, int y){
		initializeMap(x,y);
	}
	
	public TileMap(Tile[][] map){
		this.grid = map;
		initializeMap(map.length, map[0].length);
	}
	
	void initializeMap(int x, int y){
		generateFences(x,y);
		generateMhos(x,y);
		
	}
	
	public void generateFences(int x, int y){
		grid = new Tile[x][y];
		int wall_left = 44;
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
		
		int fences_left = 20;
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
	
	public void generateMhos(int x, int y){
		
		int mhos_left = mhos.length;
		while(mhos_left>0){
			int rand_x = (int) (Math.random()*grid.length);
			int rand_y = (int) (Math.random()*grid[0].length);
			if(grid[rand_x][rand_y] == null && mhos_left>0 && Math.random() < 0.05){
				Mho thisHo = new Mho(rand_x,rand_y);
				thisHo.setMap(this);
				grid[rand_x][rand_y] = thisHo;
				mhos_left-=1;
				mhos[mhos_left] = thisHo;
			}
		}	
	}
	
	public void nextTurn(){
		for(Tile[] i: grid){
			for(Tile j: i){
				if(j instanceof Entity){
					((Entity)j).nextTurn();
				}
			}
		}
	}
	
	public void Draw(Graphics g){
		for(Fence f: fences){f.draw(g);}
		for(Mho m: mhos){m.draw(g);}
		player.draw(g);
	}
	
	public Tile getTile(int x, int y){return grid[x][y];}
	public Tile[][] getGrid(){return this.grid;}
	public Mho[] getMhos(){return this.mhos;}
	public GameBoard getBoard(){return this.board;}
	public Player getPlayer(){return this.player;}
	
	public void setTile(int x, int y, Tile t){grid[x][y] = t;}
	public void setTile(Tile orig, Tile repl){grid[orig.getX()][orig.getY()] = repl;}
	public void delTile(int x, int y){grid[x][y] = null;}
	public void placePlayer(int x, int y, Player p){grid[x][y] = p;}
	public void placePlayer(int x, int y){grid[x][y] = this.player;}
	public void setPlayer(Player p){this.player = p;}
	public void setBoard(GameBoard b){this.board = b;}
}
