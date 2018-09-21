package game.ui;

import javafx.scene.image.Image;

public class Mountain extends ObjectSprite{

	private static Image mountainSprite = new Image("/game/sprites/mountain/mountain.png");
	
	public Mountain(double x, double y) {
		super(x, y, mountainSprite);
	}
	
}
