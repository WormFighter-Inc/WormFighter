package game.ui;

import game.ai.ObjectSprite;
import game.utility.Coordinate;
import javafx.scene.image.Image;

public class Mountain extends ObjectSprite{

	private static Image mountainSprite = new Image("/game/sprites/mountain/mountain.png");
	
	public Mountain(int spriteWidth, int spriteHeight, Coordinate point) {
		super(point.getX(), point.getY(), spriteWidth, spriteHeight, mountainSprite);
	}
	
	public Mountain(double x, double y) {
		super(x, y, mountainSprite);
	}
	
	/**
	 * Creates a formatted string to represent this object. 
	 * 
	 * @return A formatted string containing the coordinates of the mountain and a label
	 */
	@Override
	public String toString() {
		return String.format("(%5.0f, ", this.getX()) + String.format("%5.0f)\tMountain", this.getY());
	}
}
