package Entity;

import Tile.Fence;

public class Mho extends Entity{
	private int index;
	
	public Mho(int x, int y){
		super(x, y);
	}
	
	public void nextTurn(){
		CalcMove();
	}
	
	private void CalcMove(){
		if(this.getMap().getPlayer()!=null){
			int px = this.getMap().getPlayer().getX();
			int py = this.getMap().getPlayer().getY();
			
			int horiz = -1*normalize(this.getX()-px);
			int vert = -1*normalize(this.getY()-py);
			
			int posX = this.getX()+horiz;
			int posY = this.getY()+vert;
			
			boolean hasFenceX = this.getMap().getGrid()[posX][this.getY()] instanceof Fence;
			boolean hasFenceY = this.getMap().getGrid()[this.getX()][posY] instanceof Fence;
			boolean hasFenceDiagonal = this.getMap().getGrid()[posX][posY] instanceof Fence;
			
			boolean hasMhoX = this.getMap().getGrid()[posX][this.getY()] instanceof Mho;
			boolean hasMhoY = this.getMap().getGrid()[this.getX()][posY] instanceof Mho;
			boolean hasMhoDiagonal = this.getMap().getGrid()[posX][posY] instanceof Mho;
			
			if(vert==0){moveX(horiz);}
			else if(horiz==0){moveY(vert);}
			else if(!hasFenceDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasFenceY){moveY(vert);}
			else if(horiz>vert && !hasFenceX){moveX(horiz);}
			else if(!hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasMhoX){moveX(horiz);}
			
			if(this.getX()==px && this.getY()==py){this.getMap().getPlayer().die();}
		}
	}

	private int normalize(int x){
		if(x==0){return x;}
		else{return x/Math.abs(x);}
	}
	
	public void die(){
		super.die();
		this.getMap().delMho(this.index);
	}
	
	public void setIndex(int index){this.index = index;}
	public int getIndex(){return this.index;}
}


