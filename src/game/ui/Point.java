package game.ui;

import game.generation.GameStats;

public class Point extends Sprite{
	
	private int pointXValue;
	private int pointYValue;
	
	private boolean initialized = false;
	
	//TODO: Implement these functions into Sprite.java
	/**
	 * Creates a random point on a 20 x 20 unit grid. 
	 * 
	 */
	public Point (){
		this(new GameStats(20, 20, 0, 0, 0));
	}
	
	/**
	 * Creates a point at the given coordinate. 
	 * 
	 * @param xValue X value of the point
	 * @param yValue Y value of the point
	 */
	public Point(int xValue, int yValue, GameStats stats){
		
		if(isOnMap(stats)) {
			pointXValue = xValue;
			pointYValue = yValue;
			
			initialized = true;
		}
			
	}
	
	/**
	 * Creates a random point on the map. 
	 * 
	 * @param givenGameHeight
	 * @param givenGameWidth
	 */
	public Point (GameStats stats) {

		// Finds random height and width to be the location of the point
		pointXValue = randomXYValue(stats.getWidth());
		pointYValue = randomXYValue(stats.getHeight());
		
		initialized = true;
		
	}
	
	/**
	 * Return a string to represent the point. 
	 */
	@Override
	public String toString() {
		return "(" + getXValue() + ", " + getYValue() + ")";
	}
	
	/**
	 * Creates a random value in the range (0, boundingValue). 
	 * 
	 * @param boundingValue Upper bound of the possible random values
	 * @return A value in the range (0, boundingValue)
	 */
	private int randomXYValue(int boundingValue) {
		return (int)(Math.random() * (boundingValue * 1.0));
	}
	
	/**
	 * Gets the y value of the point. 
	 * 
	 * @return Y value of the point
	 */
	public int getYValue() {
		return pointYValue;
	}
	
	/**
	 * Gets the x value of the point. 
	 * 
	 * @return x value of the point
	 */
	public int getXValue() {
		return pointXValue;
	}
	
	public boolean isOnMap(GameStats stats) {
		
		if(getXValue() < stats.getWidth() && getXValue() >= 0) {
			
			if(getYValue() < stats.getHeight() && getXValue() >= 0) {
				return true;
			}
		}
		return false;
				
	}
	
	public boolean checkInitialization() {
		return initialized;
	}

}
