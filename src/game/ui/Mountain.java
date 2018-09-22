package game.ui;

import javafx.scene.image.Image;

public class Mountain extends ObjectSprite{

	private static Image mountainSprite = new Image("/game/sprites/mountain/mountain.png");
	
	public Mountain(double x, double y) {
		super(x, y, mountainSprite);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return String.format("(%5.0f, ", this.getXValue()) + String.format("%5.0f)\tMountain", this.getYValue());
	}
}
