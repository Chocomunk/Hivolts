package GUI;

/**
 * Determines the type of image of an object
 * @author Alvin On
 * @author Frederic Maa
 * @author Bert Davies
 */
public enum ImageType {
	//All visible object types + string locations
	MHO("res/tiles/mho.png"),
	PLAYER("res/tiles/player.png"),
	FENCE("res/tiles/fence.png"),
	
	BG("res/BGs/background.png"),
	WIN("res/BGs/win.png"),
	CONTINUE("res/BGs/continue.png"),
	LOSE("res/BGs/lose.png"),
	
	PABUTT("res/buttons/playAgain/play_again.png"),
	PABUTT_HV("res/buttons/playAgain/play_again_hv.png"),
	PABUTT_HD("res/buttons/playAgain/play_again_hd.png"),
	
	TABUTT("res/buttons/tryAgain/try_again.png"),
	TABUTT_HV("res/buttons/tryAgain/try_again_hv.png"),
	TABUTT_HD("res/buttons/tryAgain/try_again_hd.png"),
	
	CBUTT("res/buttons/continue/continue.png"),
	CBUTT_HV("res/buttons/continue/continue_hv.png"),
	CBUTT_HD("res/buttons/continue/continue_hd.png"),
	
	NULL("");
	
	//Location of image file for this type
	private String location;
	
	/**
	 * Creates an imageType based on the location of the image
	 * @param location Directory of image in workspace
	 */
	ImageType(String location){
		this.location = location;
	}
	
	/**@return the location of this ImageType*/
	public String getLcoation(){return this.location;}
}
