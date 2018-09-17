package game.generation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView{
	//Sprite variables for type and alive status
	boolean alive = true;
	final String type;
	//array to hold value of keys entered left right up down
	private boolean[] state = {false, false, false, false};
	
	
	Sprite(){
		type = null;
	}
	
	//Sprite(int x, int y, int w, int h, String type, Color color){
		//creates rectangle from specifyed parameters
	//	super(w, h, color);
	//
	//	this.type = type;
	//	setTranslateX(x);
	//	setTranslateY(y);
	//}
	

	Sprite(int x, int y, int w, int h, String type, Image image){
		//creates rectangle from specifyed parameters
		super(image);
		
		setFitHeight(h);
		setFitWidth(w);
		
		this.type = type;
		setTranslateX(x);
		setTranslateY(y);
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
	
	void shoot() {
		
	}
}
