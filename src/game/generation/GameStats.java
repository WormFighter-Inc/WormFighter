package game.generation;

public class GameStats {
	
	// Private data fields
	private int gameWidth;
	private int gameHeight;
	private int numOfMountains;
	private int numOfMountainRanges;
	private int rangeLength;
	
	/**
	 * Default game. 
	 * Everything in a "normal" setting. 
	 */
	public GameStats(){
		this(800, 600, 10, 5, 5);
	}
	
	/**
	 * Custom game. 
	 * 
	 * @param givenGameWidth
	 * @param givenGameHeight
	 * @param givenTotalMountains
	 * @param givenTotalMountainRanges
	 * @param mountainRangeLength
	 */
	public GameStats(int givenGameWidth, int givenGameHeight, int givenTotalMountains, int givenTotalMountainRanges, int mountainRangeLength){
		
		gameWidth = givenGameWidth;
		gameHeight = givenGameHeight;
		numOfMountains = givenTotalMountains;
		numOfMountainRanges = givenTotalMountainRanges;
		rangeLength = mountainRangeLength;
	}
	
	public int getWidth() {
		return gameWidth;
	}
	
	public int getHeight() {
		return gameHeight;
	}
	
	public int getNumOfMountains() {
		return numOfMountains;
	}
	
	public int getNumOfMountainRanges() {
		return numOfMountainRanges;
	}
	
	public int getRangeLength() {
		return rangeLength;
	}
}
