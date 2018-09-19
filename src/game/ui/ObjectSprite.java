package game.ui;

import javafx.scene.image.Image;

public class ObjectSprite extends MoveableSprite{

	protected ObjectSprite(Image image) {
		super(image);
	}
	
	protected ObjectSprite(double x, double y, Image image) {
		super(x, y, image);
	}
	
	protected ObjectSprite(double x, double y, int spriteHeight, int spriteWidth, Image image) {
		super(x, y, spriteHeight, spriteWidth, image);
	}

}
