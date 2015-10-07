package Core;

public class Updater implements Runnable{
	
	public Updater(){
		
	}
	
	public void run() {
		// TODO Auto-generated method
		try{
			while(true){
				Hivolts.Update();
				Thread.sleep(16);
//				System.out.println("Updating");
			}
		}catch(Exception e){System.out.println("I aint Updating cause");e.printStackTrace();}
	}
}
