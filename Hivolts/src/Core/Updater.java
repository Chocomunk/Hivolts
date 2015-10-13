package Core;

public class Updater implements Runnable{
	
	/**
	 * Implemented from Runnable interface, excecuted as part of Thread.
	 */
	public void run() {
		try{
			//Ticks every 16 ms, or about 60fps. Calls Update method in core class.
			while(true){
				Hivolts.Update();
				Thread.sleep(16);
			}
		}catch(Exception e){System.out.println("Updater Error: ");e.printStackTrace();}
	}
}
