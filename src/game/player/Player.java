package game.player;

import game.ai.Sprite;
import javafx.scene.image.Image;

public class Player extends Sprite{
	static Image sprite = new Image("/game/sprites/player/Player.png");
	
	private int playerWidth;
	private int playerHeight;

	public Player() {
		super(sprite);
	}
	
	public Player(double x, double y, int spriteWidth, int spriteHeight) {
		super(x, y, spriteWidth, spriteHeight, sprite);
		
		playerWidth = spriteWidth;
		playerHeight = spriteWidth;
	}

	public void updatePosition(double screenWidth, double screenHeight) {
		setX((screenWidth / 2.0) - (playerWidth / 2.0));
		setY((screenHeight / 2.0) - (playerHeight / 2.0));
	}

}
