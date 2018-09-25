package game.ai.worm;

import game.ai.MoveableSprite;
import javafx.scene.image.Image;

public abstract class Worm extends MoveableSprite{
	//image files for each worm position are allocated
	private static Image wormyRight;
	private static Image wormyLeft;
	private static Image wormyUp;
	private static Image wormyDown;
	
	//ints for attribute points
	private int healthPoints;
	private int attackPoints;
	private int defencePoints;

	Worm(double x, double y, int spriteWidth, int spriteHeight, Image right, Image left, int health, int attack, int defence){
		super(x, y, spriteWidth, spriteHeight, right);
		wormyRight = right;
		wormyLeft = left;
		
		healthPoints = health;
		attackPoints = attack;
		defencePoints = defence;
	}
	
	/**
	 * Move left with wormy image
	 * @param distance pixels to move left
	 */
	@Override
	public void moveLeft(double distance){
		super.moveLeft(distance, wormyLeft);
	}
	
	/**
	 * Move right with wormy image
	 * 
	 */
	@Override
	public void moveRight(double distance){
		super.moveRight(distance, wormyRight);
	}
}
