package Core;

public class Updater implements Runnable{
	
	public Updater(){
		
	}
	
	public void run() {
		// TODO Auto-generated method stublong lastTime = System.nanoTime();
		try{
			while(true){
				Hivolts.Update();
				Thread.sleep(5);
//				System.out.println("Updating");
			}
		}catch(Exception e){System.out.println("I aint Updating cause "+e);}
	}
}