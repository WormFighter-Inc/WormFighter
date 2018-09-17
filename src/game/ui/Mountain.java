package game.ui;

import game.generation.GameStats;

public class Mountain extends Sprite{
	
	/**
	 * Creates a random mountain point on a 20 x 20 unit grid. 
	 */
	public Mountain() {
		super();
	}
	
	/**
	 * Creates a mountain point on the map at the given coordinates. 
	 * 
	 * @param xValue X-value of the point
	 * @param yValue Y-value of the point
	 */
	public Mountain(int xValue, int yValue, GameStats stats) {
		super(xValue, yValue, stats);
	}
	
	/**
	 * Creates a mountain point at a random place on the map. 
	 * 
	 * @param stats GameStats object for the game
	 */
	public Mountain(GameStats stats) {
		super(stats);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "/\\: (" + getXValue() + ", " + getYValue() + ")" ;
	}
	
}
