package Tile;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Entity.Mho;

public class TileMap {

	private Tile[][] grid;
	private Mho[] mhos = new Mho[12];
	
	public TileMap(int type, int x, int y){
		initializeMap(x,y);
	}
	
	void initializeMap(int x, int y){
		generateFences(x,y);
		generateMhos(x,y);
		
	}
	
	public void generateFences(int x, int y){
		grid = new Tile[x][y];
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[i].length; j++){
				if((i==0 || j==0)||(i==x-1 || j==y-1)){
					grid[i][j] = new Fence(i,j);
				}
			}
		}
		
		int fences_left = 20;
		while(fences_left>0){
			int rand_x = (int) (Math.random()*grid.length);
			int rand_y = (int) (Math.random()*grid[0].length);
			if(grid[rand_x][rand_y] == null && fences_left>0 && Math.random() < 0.05){
				grid[rand_x][rand_y] = new Fence(rand_x,rand_y);
				fences_left-=1;
			}
		}	
	}
	
	public void generateMhos(int x, int y){
		
		int mhos_left = mhos.length;
		while(mhos_left>0){
			int rand_x = (int) (Math.random()*grid.length);
			int rand_y = (int) (Math.random()*grid[0].length);
			if(grid[rand_x][rand_y] == null && mhos_left>0 && Math.random() < 0.05){
				grid[rand_x][rand_y] = new Mho(rand_x,rand_y);
				mhos_left-=1;
			}
		}	
	}
	
	public TileMap(Tile[][] map){
		this.grid = map;
		initializeMap(map.length, map[0].length);
	}
	
	public void Draw(Graphics g){
		for(Tile[] i: grid){
			for(Tile j: i){
				if(j!=null){
					j.draw(g);
				}
			}
		}
		
	}
	
//	public boolean allExists(){
//		boolean exists = false;
//		for(Tile[] i: this.grid){
//			for(Tile j: i){
//				if(j.isActivated() && j.getType()!=TileType.EMPTY){
//					exists = true;
//				}
//			}
//		}
//		return exists;
//	}

//	public void changePos(float x, float y) {
//		for(Tile[] i: grid){
//			for(Tile j: i){
//				j.changeX(-x);
//				j.changeY(-y);
//			}
//		}
//	}
	
	public Tile getTile(int x, int y){return grid[x][y];}
	public Tile[][] getGrid(){return grid;}
	public Mho[] getMhos(){return mhos;}
	
//	public void tick(int delta, GameContainer gc){
//		for(Tile t: tickable){
//			t.tick(delta, gc);
//		}
//	}
	
}
