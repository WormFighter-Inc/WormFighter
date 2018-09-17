package game.ui;

import game.generation.GameStats;

public class Reserved extends Sprite{
	
	/**
	 * Creates a random reserved point on a 20 x 20 unit grid. 
	 */
	public Reserved() {
		super();
	}
	
	/**
	 * Creates a reserved point on the map at the given coordinates. 
	 * 
	 * @param xValue X-value of the point
	 * @param yValue Y-value of the point
	 */
	public Reserved (int xValue, int yValue, GameStats stats) {
		super(xValue, yValue, stats);
	}
	
	/**
	 * Creates a reserved point at a random place on the map. 
	 * 
	 * @param stats GameStats object for the game
	 */
	public Reserved(GameStats stats) {
		super(stats);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "@r";
	}
	
}
