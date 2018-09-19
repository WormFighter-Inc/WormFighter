package game.legacy;

import game.ui.Sprite;
import javafx.scene.image.Image;

public class Mountain extends Sprite{
	
	private static Image mountain = new Image("/game/sprites/mountain/mountain.png");
	
	/**
	 * Creates a random mountain point on a 20 x 20 unit grid. 
	 */
	public Mountain() {
		super(mountain);
	}
	
	/**
	 * Creates a mountain point on the map at the given coordinates. 
	 * 
	 * @param xValue X-value of the point
	 * @param yValue Y-value of the point
	 */
	public Mountain(int xValue, int yValue) {
		super(xValue, yValue, 50, 50, mountain);
	}
	

	/**
	 * Creates a mountain point at a random place on the map. 
	 * 
	 * @param stats GameStats object for the game
	 */
	public Mountain(GameStats stats) {
		super(stats, mountain);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "/\\: (" + getXValue() + ", " + getYValue() + ")" ;
	}
	
}
