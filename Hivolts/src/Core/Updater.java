package Core;

public class Updater implements Runnable{
	
	public Updater(){
		
	}
	
	public void run() {
		try{
			while(true){
				Hivolts.Update();
				Thread.sleep(16);
			}
		}catch(Exception e){System.out.println("Updater Error: ");e.printStackTrace();}
	}
}
