package Entity;

import java.awt.Graphics;

import Tile.Fence;

public class Mho extends Entity{
	private int index;
	
	private boolean moved = false;
	private int moveTimes = 0;
	
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
			
			this.setMoved();
			
			if(vert==0 && !hasMhoX){moveX(horiz);}
			else if(horiz==0 && !hasMhoY){moveY(vert);}
			else if(!hasFenceDiagonal && !hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasFenceY && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasFenceX && !hasMhoX){moveX(horiz);}
			else if(!hasMhoDiagonal){moveDiagonal(horiz,vert);}
			else if(vert>horiz && !hasMhoY){moveY(vert);}
			else if(horiz>vert && !hasMhoX){moveX(horiz);}
			else{this.resetMoved();moveTimes++;}
			
//			if(!this.hasMoved()){System.out.println("I am mho "+this.getIndex()+" and I am being a huge butt!");}
			
			if(this.getX()==px && this.getY()==py){this.getMap().getPlayer().die();}
		}
	}
	
	public void die(){
		super.die();
	}
	
	public void draw(Graphics g){
		super.draw(g);
		g.drawString(this.getIndex()+"", this.getX()*74+35, this.getY()*74+64);
	}
	
	public void setIndex(int index){this.index = index;}
	public int getIndex(){return this.index;}
	
	public void setMoved(){this.moved=true;}
	public void resetMoved(){this.moved=false;}
	
	public int getMoveTimes(){return this.moveTimes;}
	public void resetTimes(){this.moveTimes=0;}
	
	public boolean hasMoved(){return this.moved;}
}


