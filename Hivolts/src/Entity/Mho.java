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
		int right;
		int down;
		
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
			// player is to the right of mho
			right = 1;
		}
		else{
			//player is to the left of mho
			right = -1;
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
			
			down = 1;
		}
		else{
			
			down = -1;
		}
		
		//if(getTile(x + right,y + down) == instanceof fence){
			
		//}
		
	
		
		
		
		
	}
	
int[] Mcoords = new int[2];
}


