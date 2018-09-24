package game.ai;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite extends ImageView{
	//Sprite variables for type and alive status
	private boolean initialized = false;
	private double xValue;
	private double yValue;
	
	/**
	 * Instantiates Sprite with Image at Pos 0, 0, with width 20, height 20
	 * @param image File to be displayed on screen
	 */
	public Sprite(Image image){
		this(0, 0, image);
		
	}
	
	/**
	 * Instantiates Sprite with Image at Pos, X, Y, and width 20, height 20
	 * @param x starting x pos
	 * @param y starting y pos
	 * @param image File to be displayed on screen
	 */
	public Sprite(double x, double y, Image image){
		this(x, y, 20, 20, image);
	}
	
	/**
	 * Instantiates Sprite with Image at Pos, X, Y, and width spriteWidth, height spriteHeight
	 * @param x starting x pos
	 * @param y starting y pos
	 * @param spriteWidth width of sprite
	 * @param spriteHeight height of sprite
	 * @param image File to be displayed on screen
	 */
	public Sprite(double x, double y, int spriteWidth, int spriteHeight, Image image){
		//creates rectangle from specified parameters
		super(image);
		
		//set dimensions of sprite
		setFitHeight(spriteHeight);
		setFitWidth(spriteWidth);
		
		this.initialized = true;
		
		setTranslateX(x);
		xValue = x;
		setTranslateY(y);
		yValue = y;
	}
	
	/**
	 * Return a string to represent current x and y position
	 */
	@Override
	public String toString() {
		return "(" + getXValue() + ", " + getYValue() + ")";
	}
	
	/**
	 * Gets the y position on screen 
	 * 
	 * @return Y value of the sprite
	 */
	public double getYValue() {
		return getTranslateY();
	}
	
	/**
	 * Gets the x position on screen 
	 * 
	 * @return x value of the sprite
	 */
	public double getXValue() {
		return getTranslateX();
	}
	
	/**
	 * Checks if sprite is located on the map
	 * 
	 * @return Sprite on map
	 */
	public boolean isOnMap() {
		//TODO: Implement Method	
		return false;
	}
	
	public boolean checkInitialization() {
		return initialized;
	}
}