package game.ui;

import javafx.scene.image.Image;

public abstract class MoveableSprite extends Sprite{
	//boolean array of movementstats left, right, up, down
	private boolean[] movementStates = {false, false, false, false};
	
	/**
	 * Instantiates Moveable Sprite with Image at Pos 0, 0, with width 20, height 20
	 * @param image File to be displayed on screne
	 */
	MoveableSprite(Image image) {
		super(image);
	}
	
	/**
	 * Instatiates Moveable Sprite with Image at Pos, X, Y, and width 20, height 20
	 * @param x starting x pos
	 * @param y starting y pos
	 * @param image File to be displayed on screne
	 */
	MoveableSprite(double x, double y, Image image){
		super(x, y, image);
	}
	
	/**
	 * Instatiates Moveable Sprite with Image at Pos, X, Y, and width spriteWidth, height spriteHeight
	 * @param x starting x pos
	 * @param y starting y pos
	 * @param spriteWidth width of sprite
	 * @param spriteHeight height of sprite
	 * @param image File to be displayed on screne
	 */
	MoveableSprite(double x, double y, int spriteWidth, int spriteHeight, Image image){
		super(x, y, spriteWidth, spriteHeight, image);
	}

	/**
	 * Setter for move left boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	public boolean setMoveLeft(boolean state) {
		this.movementStates[0] = state;
		return state;
	}
	
	/**
	 * Setter for move right boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	public boolean setMoveRight(boolean state) {
		this.movementStates[1] = state;
		return state;
	}
	
	/**
	 * Setter for move up boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	public boolean setMoveUp(boolean state) {
		this.movementStates[2] = state;
		return state;
	}
	
	/**
	 * Setter for move down boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	public boolean setMoveDown(boolean state) {
		this.movementStates[3] = state;
		return state;
	}
	
	/**
	 * Sets all movementStates of movement to false
	 */
	void setAllFalse() {
		this.movementStates[0] = false;
		this.movementStates[1] = false;
		this.movementStates[2] = false;
		this.movementStates[3] = false;
	}
	
	/**
	 * 
	 * @return Array of movementStates of movement (left, right, up, down)
	 */
	public boolean[] getState() {
		return this.movementStates;
	}
	
	/**
	 * Move sprite left specifyed distance
	 * @param distance pixels to move left
	 */
	public void moveLeft(double distance) {
		setTranslateX(getTranslateX()- distance);
	}
	
	/**
	 * Move sprite right specifyed distance
	 * @param distance pixels to move right
	 */
	public void moveRight(double distance) {
		setTranslateX(getTranslateX()+ distance);
	}
	
	/**
	 * Move sprite up specifyed distance
	 * @param distance pixels to move up
	 */
	public void moveUp(double distance) {
		setTranslateY(getTranslateY()- distance);
	}

	/**
	 * Move sprite down specifyed distance
	 * @param distance pixels to move down
	 */
	public void moveDown(double distance) {
		setTranslateY(getTranslateY()+ distance);
	}
	
	/**
	 * Move sprite left specifyed distance and change image
	 * @param distance pixels to move left
	 * @param spriteArt new image
	 */
	public void moveLeft(double distance, Image spriteArt) {
		setImage(spriteArt);
		setTranslateX(getTranslateX()- distance);
	}
	
	/**
	 * Move sprite right specifyed distance
	 * @param distance pixels to move right
	 * @param spriteArt new image
	 */
	public void moveRight(double distance, Image spriteArt) {
		setImage(spriteArt);
		setTranslateX(getTranslateX()+ distance);
	}
	
	/**
	 * Move sprite up specifyed distance
	 * @param distance pixels to move up
	 * @param spriteArt new image
	 */
	public void moveUp(double distance, Image spriteArt) {
		setImage(spriteArt);
		setTranslateY(getTranslateY()- distance);
	}

	/**
	 * Move sprite down specifyed distance
	 * @param distance pixels to move down
	 * @param spriteArt new image
	 */
	public void moveDown(double distance, Image spriteArt) {
		setImage(spriteArt);
		setTranslateY(getTranslateY()+ distance);
	}
	
}
