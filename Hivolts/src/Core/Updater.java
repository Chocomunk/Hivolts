package Core;

/**
 * Updater class, updates other classes every 16ms, or at 60fps
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 * @see Runnable
 */
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
