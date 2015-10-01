package Entity;

import Tile.Fence;
import Tile.Tile;

import java.awt.Color;
import java.awt.Graphics;

public class Mho extends Entity{
	public Mho(int x, int y){
		super(x, y);
	}
	
	public void nextTurn(){
		CalcMove(this.getX(), this.getY());
	}
	
	/**
	 * Edan this is causing so many errors omg, wtf is going on with this
	 * @param x
	 * @param y
	 */
	private void CalcMove(int x, int y){
		
		int playerposx = this.getMap().getPlayer().getX();
		int playerposy = this.getMap().getPlayer().getY();;
		int right = 0;
		int down = 0;
		boolean shouldhero = false;
		
		
		//check for players position in respect to the mho
		if(playerposx == x){
			if(playerposy > y){
				this.moveY(1);
				return;
			}
			else{
				this.moveY(-1);
				return;
				
			}
		}
		else if(playerposx-x > 0){
			// player is to the right of mho
			right = 1;
		}
		else{
			//player is to the left of mho
			right = -1;
		}
		if(playerposy == y){
			if(playerposx > x){
				this.moveX(1);
				return;
			}
			else{
				this.moveX(-1);
				return;
			}
		}
		else if(playerposy - y > 0){
			//play is below the mho
			down = 1;
		}
		else{
			//player is above the mho
			down = -1;
		}
		
		if(!(this.getMap().getTile(x + right,y + down) instanceof Tile)){
			this.moveX(right);
			this.moveY(down);
			return;

		}
		else{
			if(Math.abs(playerposx-x)>=Math.abs(playerposy-y)||!(this.getMap().getTile(x+right,y) instanceof Tile)){
				this.moveX(right);
				return;
			}
			else{
				if(!(this.getMap().getTile(x, y+down) instanceof Tile)){
					this.moveY(down);
					return;
				}
				else{
					if(this.getMap().getTile(x, y+down) instanceof Fence){shouldhero=true;}
					if(this.getMap().getTile(x+right, y) instanceof Fence){shouldhero=true;}
					if(this.getMap().getTile(x+right, y+down) instanceof Fence){shouldhero=true;}
					if(shouldhero = true){Dead();}
				}
			}
//   if(Math.abs(playerposx-x)>=Math.abs(playerposy-y)||getTile(x+right)){
//				Mcoords
//			}
		}
		
	
		
		
		
		
	}
	
int[] Mcoords = new int[2];

}


