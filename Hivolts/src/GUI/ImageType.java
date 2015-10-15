package GUI;

/**
 * Determines the type of image of an object
 * @author Alvin On
 * @author Edan Sneh
 * @author Frederic Maa
 */
public enum ImageType {
	//All visible object types + string locations
	MHO("res/mho.png"),
	PLAYER("res/player.png"),
	FENCE("res/fence.png"),
	BG("res/background.png"),
	WIN("res/background.png"),
	LOSE("res/background.png"),
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
