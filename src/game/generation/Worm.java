package game.generation;

import javafx.scene.image.Image;

public class Worm extends Sprite{
	static Image wormyRight = new Image("/game/sprites/worm/Wormy_Right.png");
	static Image wormyLeft = new Image("/game/sprites/worm/Wormy_Left.png");

	Worm(){
		super(200,200,50,50,"worm",wormyRight);
	}
	
	void moveLeft() {
		setImage(wormyLeft);
		setTranslateX(getTranslateX()-5);
	}
	
	void moveRight() {
		setImage(wormyRight);
		setTranslateX(getTranslateX()+5);
	}
}
