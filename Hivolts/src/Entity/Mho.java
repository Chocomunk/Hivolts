package Entity;

public class Mho extends Entity{
	public Mho(int x, int y){
		super(x, y);
	}
	
	void nextTurn(){
		CalcMove(this.getX(), this.getY());
	}
	
	private void CalcMove(int x, int y) {
		int playerposx = 0;
		int playerposy = 0;
		boolean right;
		boolean down;
		
		//check for players position in respect to the mho
		if(playerposx == x){
			if(playerposy > y){
				Mcoords [1] = moveY(1);
				return;
			}
			else{
				Mcoords [1] = moveY(-1);
				return;
			}
		}
		else if(playerposx-x > 0){
			right = true;
		}
		else{
			right = false;
		}
		if(playerposy == y){
			if(playerposy > x){
				Mcoords [0] = moveX(1);
				return;
			}
			if(playerposy < x){
				Mcoords [0] = moveX(-1);
				return;
			}
		}
		else if(playerposy - y > 0){
			down = true;
		}
		else{
			down = true;
		}
		
		//if(getTile(x + 1,y) = Tile instanceof fence)
		
	
		
		
		
		
	}
	
int[] Mcoords = new int[2];
}


