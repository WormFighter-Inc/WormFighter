package game.ui;

import game.generation.GameStats;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView{
	//Sprite variables for type and alive status
	private boolean initialized = false;
	private final String type;
	
	//array to hold value of keys entered left right up down
	private boolean[] state = {false, false, false, false};
	
	//TODO: Implement Functions from point.java
	
	Sprite(){
		this(new GameStats(800, 600, 0, 0, 0));
	}
	
	Sprite(int x, int y, GameStats stats){
		this.type = null;
		this.initialized = true;
		if(isOnMap(stats)) {
			setTranslateX(x);
			setTranslateY(y);
		}
		
		//sets width and height to default values
		setFitHeight(10);
		setFitWidth(10);
	}

	Sprite(GameStats stats){
		//sets width and height to default values
		setFitHeight(10);
		setFitWidth(10);
		
		// Finds random height and width to be the location of the point
		setTranslateX(randomXYValue(stats.getWidth()));
		setTranslateY(randomXYValue(stats.getHeight()));
			
		this.type=null;
		this.initialized = true;
	}
	
	Sprite(int x, int y, int w, int h, String type, Image image){
		//creates rectangle from specifyed parameters
		super(image);
		
		//set dimensions of sprite
		setFitHeight(h);
		setFitWidth(w);
		
		this.initialized = true;
		this.type = type;
		setTranslateX(x);
		setTranslateY(y);
	}
	
	/**
	 * Return a string to represent the point. 
	 */
	@Override
	public String toString() {
		return "(" + getXValue() + ", " + getYValue() + ")";
	}
	
	/**
	 * Setter for move left boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	boolean setMoveLeft(boolean state) {
		this.state[0] = state;
		return state;
	}
	
	/**
	 * Setter for move right boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	boolean setMoveRight(boolean state) {
		this.state[1] = state;
		return state;
	}
	
	/**
	 * Setter for move up boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	boolean setMoveUp(boolean state) {
		this.state[2] = state;
		return state;
	}
	
	/**
	 * Setter for move down boolean
	 * @param state to set boolean to
	 * @return state of boolean
	 */
	boolean setMoveDown(boolean state) {
		this.state[3] = state;
		return state;
	}
	
	/**
	 * Sets all states of movement to false
	 */
	void setAllFalse() {
		this.state[0] = false;
		this.state[1] = false;
		this.state[2] = false;
		this.state[3] = false;
	}
	
	/**
	 * 
	 * @return Array of states of movement (left, right, up, down)
	 */
	boolean[] getState() {
		return this.state;
	}
	
	/**
	 * Moves Sprit Left 5 pixels
	 */
	void moveLeft() { 
		setTranslateX(getTranslateX()-5);
	}
	
	/**
	 * Moves Sprite Right 5 pixels
	 */
	void moveRight() {
		setTranslateX(getTranslateX()+5);
	}
	
	/**
	 * Move Sprite Up 5 pixels
	 */
	void moveUp() {
		setTranslateY(getTranslateY()-5);
	}
	
	/**
	 * Moves sprite down 5 pixels
	 */
	void moveDown() {
		setTranslateY(getTranslateY()+5);
	}
	
	/**
	 * Creates a random value in the range (0, boundingValue). 
	 * 
	 * @param boundingValue Upper bound of the possible random values
	 * @return A value in the range (0, boundingValue)
	 */
	private int randomXYValue(int boundingValue) {
		return (int)(Math.random() * (boundingValue * 1.0));
	}
	
	/**
	 * Gets the y value of the point. 
	 * 
	 * @return Y value of the point
	 */
	public int getYValue() {
		return (int)getTranslateY();
	}
	
	/**
	 * Gets the x value of the point. 
	 * 
	 * @return x value of the point
	 */
	public int getXValue() {
		return (int)getTranslateX();
	}
	
	/**
	 * Checks if sprite is located on the map
	 * 
	 * @param stats game board information
	 * @return Sprite on map
	 */
	public boolean isOnMap(GameStats stats) {
		
		if(getXValue() < stats.getWidth() && getXValue() >= 0) {
			
			if(getYValue() < stats.getHeight() && getXValue() >= 0) {
				return true;
			}
		}
		return false;
				
	}
	
	public boolean checkInitialization() {
		return initialized;
	}
	
	void shoot() {
		
	}
}
