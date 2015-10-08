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
		if(this.isValid()&&this.getMap().getPlayer().isValid()){
			int px = this.getMap().getPlayer().getX();
			int py = this.getMap().getPlayer().getY();
			
			int horiz = -1*normalize(this.getX()-px);
			int vert = -1*normalize(this.getY()-py);
			
			int posX = this.getX()+horiz;
			int posY = this.getY()+vert;
			
			boolean hasFenceX = this.getMap().getGrid()[posX][this.getY()] instanceof Fence;
			boolean hasFenceY = this.getMap().getGrid()[this.getX()][posY] instanceof Fence;
			boolean hasFenceDiagonal = this.getMap().getGrid()[posX][posY] instanceof Fence;
			
			boolean hasMhoX = this.getMap().getGrid()[posX][this.getY()] instanceof Mho && ((Mho)this.getMap().getGrid()[posX][this.getY()]).isValid();
			boolean hasMhoY = this.getMap().getGrid()[this.getX()][posY] instanceof Mho && ((Mho)this.getMap().getGrid()[this.getX()][posY]).isValid();
			boolean hasMhoDiagonal = this.getMap().getGrid()[posX][posY] instanceof Mho && ((Mho)this.getMap().getGrid()[posX][posY]).isValid();
			
			if(vert==0 && !hasMhoX){moveX(horiz);}
			else if(horiz==0 && !hasMhoY){moveY(vert);}
			else if(!hasFenceDiagonal && !hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasFenceY && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasFenceX && !hasMhoX){moveX(horiz);}
			else if(!hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasMhoX){moveX(horiz);}
			
			if(this.getX()==px && this.getY()==py){this.getMap().getPlayer().die();}
		}
	}
	
	public void die(){
		super.die();
	}
	
	public void setIndex(int index){this.index = index;}
	public int getIndex(){return this.index;}
}


