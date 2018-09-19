package game.ui;

import javafx.scene.image.Image;

public class LightWorm extends Worm{
	private static Image wormyRight = new Image("/game/sprites/worm/Wormy_Right.png");
	private static Image wormyLeft = new Image("/game/sprites/worm/Wormy_Left.png");
	
	LightWorm(double x, double y, int spriteWidth, int spriteHeight){
		super(x, y, spriteWidth, spriteHeight, wormyRight, wormyLeft, 5, 5, 5);
	}
}
