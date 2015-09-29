package Entity;

public class Mho extends Entity{
	public Mho(int x, int y){
		super(x, y);
	}
	void nextTurn(){
		CalcMove(this.getX(), this.getY());
	}
	
	private void CalcMove(int x, int y) {
		if(playerposx == x){
			if(playerposy > y){
				Mcoords [1] = moveY(1);
				break;
			}
			else{
				Mcoords [1] = moveY(-1);				
			}
		}
		if(playerposy == y){
			if(playerpos)
		}
			
		
		if(playerposy == this.getY()){
			moveX(1);
		}
		
	}
	
int[] Mcoords = new int[2];
}


