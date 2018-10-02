package game.ai;

import java.util.ArrayList;
import java.util.Iterator;

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
		
		setX(x);
		xValue = x;
		setY(y);
		yValue = y;
	}
	
	/**
	 * Return a string to represent current x and y position
	 */
	@Override
	public String toString() {
		return "(" + this.getTranslateX() + ", " + this.getTranslateY() + ")";
	}
	
	/**
	 * check collision with other sprites
	 * @param sprites list of all sprites on the map that could be collided with
	 * @return true if collision detected
	 */
	public boolean[] checkCollision(ArrayList<MoveableSprite> sprites) {	
		boolean[] array = {true, true, true, true};
		Iterator<MoveableSprite> currentSprites = sprites.iterator();
		//Skip over map art
		currentSprites.next();	
		
		//iterator through list of sprites and check for intersection
		while(currentSprites.hasNext()) {
			Sprite temp = currentSprites.next();
			
			if(this.intersects(temp.getBoundsInLocal()) && !this.equals(temp)) {
				String side = this.checkCollisionSide(temp);
				switch(side) {
					case "left":
						array[0] = false;
						break;
					case "right":
						array[1] = false;
						break;
					case "top":
						array[2] = false;
						break;
					case "bottom":
						array[3] = false;
						break;
					default:
						array[0] = true;
						array[1] = true;
						array[2] = true;
						array[3] = true;
					
				}
				
				System.out.printf("Collision Detected: %s%n", temp.toString());
			}
		}
		//return false if no intersection detected
		return array;
	}
	
	/**
	 * Checks the side of the collision
	 * @param collision object collided with
	 * @return String of what side collided with
	 */
	public String checkCollisionSide(Sprite collision) {
		//hit on right
		if(Math.abs((collision.getX() + collision.getFitWidth()) - this.getX()) < 5) {
			//System.out.println("Right");
			return "right";
		}
		//hit on left
		if(Math.abs(collision.getX() - (this.getX() + this.getFitWidth())) < 5) {
			//System.out.println("Left");
			return "left";
		}
		//hit on top
		if(Math.abs(collision.getY() - (this.getY() + this.getFitHeight())) < 5) {
			//System.out.println("Top");
			return "top";
		}
		//hit on bottom
		if(Math.abs((collision.getY() + collision.getFitHeight()) - this.getY()) < 5) {
			//System.out.println("Bottom");
			return "bottom";
		}
		
		return "null";
	}
	
	public boolean checkInitialization() {
		return initialized;
	}
}
