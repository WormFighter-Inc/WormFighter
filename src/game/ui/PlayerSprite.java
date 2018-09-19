package game.ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class PlayerSprite extends Sprite{
	static Image sprite = new Image("/game/sprites/player/Player.png");

	PlayerSprite(Scene scene) {
		super((scene.getX()/2), (scene.getY()/2),sprite);
	}
	
	PlayerSprite(int spriteWidth, int spriteHeight, Scene scene) {
		super((scene.getX()/2), (scene.getY()/2), spriteWidth, spriteHeight, sprite);
	}

}
