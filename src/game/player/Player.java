package game.player;

import game.ai.Sprite;
import javafx.scene.image.Image;

public class Player extends Sprite{
	static Image sprite = new Image("/game/sprites/player/Player.png");

	public Player() {
		super(sprite);
	}
	
	public Player(double x, double y, int spriteWidth, int spriteHeight) {
		super(x, y, spriteWidth, spriteHeight, sprite);
	}

}
