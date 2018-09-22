package game.generation;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import game.ui.Mountain;
import game.ui.MoveableSprite;
import game.ui.Player;
import javafx.scene.layout.Pane;

public class Map extends Pane{
	private ArrayList<MoveableSprite> sprites = new ArrayList<MoveableSprite>();
	private Player currentPlayer;
	private boolean[] movementStates = {false, false, false, false};
	
	//TODO
	private double mapSizeX = 2000;
	private double mapSizeY = 2000;
	
	private File premadeMap;
	
	public Map(String mapFileName) {
		importMap(mapFileName);
		
		
	}

	/**
	 * Contructs a map with sprites
	 * @param sprites Array list filled with sprites to add to map
	 */
	public Map(ArrayList<MoveableSprite> sprites, Player player){
		for(int i = 0; i < sprites.size(); i ++) {
			this.getChildren().add(sprites.get(i));
			this.sprites = sprites;
		}
		currentPlayer = player;
		this.getChildren().add(player);
	}
	
	/**
	 * Add new sprite to map
	 * @param newSprite sprite to add
	 * @return true if successful
	 */
	public boolean addSprite(MoveableSprite newSprite){
		sprites.add(newSprite);
		return true;
	}
	
	/**
	 * Removes a target sprite from the map
	 * @param targetSprite target sprite to remove from map
	 * @return true if removed false if not on map
	 */
	public boolean removeSprite(MoveableSprite targetSprite) {
		if(!sprites.contains(targetSprite)) {
			return false;
		}else {
			sprites.remove(targetSprite);
			return true;
		}
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
	public void setAllFalse() {
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
	 * Calls move left on all of the sprites on the map making the player move right relative
	 */
	public void moveRight() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveLeft(5);
		}
	}
	
	/**
	 * Calls move right on all of the sprites on the map making the player move left relative
	 */
	public void moveLeft() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveRight(5);
		}
	}
	
	/**
	 * Calls move down on all of the sprites on the map making the player up right relative
	 */
	public void moveUp() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveDown(5);
		}
	}
	
	/**
	 * Calls move up on all of the sprites on the map making the player move down relative
	 */
	public void moveDown() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveUp(5);
		}
	}
	
	public void importMap(String mapName) {
		int lineIndex = 0;
		
		premadeMap = new File(mapName);
		
		try(Scanner mapInput = new Scanner(premadeMap)){
			
			while(mapInput.hasNextLine()) {
				
				double objectX = mapInput.nextDouble();
				double objectY = mapInput.nextDouble();
				
				if(objectX < 0 || objectY < 0) {
					System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "/n/tX or Y coordinate is negative");
				}
				else if(objectX > mapSizeX || objectY > mapSizeY) {
					
				}
				else {
					
					String type = mapInput.next();
					
					if(type == "mountain") {
						sprites.add(new Mountain(objectX, objectY));
					}
					else if(type == "water") {
						// Water class not created or implemented yet
						//sprites.add(new Water(objectX, objectY));
					}
					else {
						System.out.println("Map Creation Error: Incorrect label at line " + lineIndex);
					}
					
					lineIndex++;
				}
				
			}
		}
		catch(Exception FileNotFoundException) {
			System.out.println(FileNotFoundException.getMessage());
		}
	}
	
	/**
	 * Debugging method. 
	 * Calls the toString method for every sprite in the array sprites. 
	 * 
	 */
	private void printSprites() {
		
		for(int i = 0; i < sprites.size(); i++) {
			
			System.out.println(sprites.get(i).toString());
		}
	}
	
}



























