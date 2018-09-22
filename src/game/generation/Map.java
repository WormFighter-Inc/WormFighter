package game.generation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import game.ui.Mountain;
import game.ui.MoveableSprite;
import game.ui.Player;
import game.utility.Coordinate;
import javafx.scene.layout.Pane;

public class Map extends Pane{
	
	private ArrayList<MoveableSprite> sprites = new ArrayList<MoveableSprite>();
	private Player currentPlayer;
	private boolean[] movementStates = {false, false, false, false};
	
	// TODO Implement these for real
	private Coordinate screenSize;
	private Coordinate mapSize;
	
	// Used to read in a map from a file
	InputStream mapFile;
	
	// Data field for the player's starting position, initialized by reading in the point from mapFile
	private Coordinate playerStartingPosition;
	
	/**
	 * Debugging constructor. 
	 * Allows easy access to the importMap method. 
	 * 
	 * @param mapFileName Name of the file which holds the map data
	 */
	public Map(String mapFileName) {
		mapSize = new Coordinate(10000, 10000);
		screenSize = new Coordinate(500, 500);
		
		mapFile = Map.class.getResourceAsStream("/game/maps/" + mapFileName);
		importMap();
		
		System.out.println("Map relative: ");
		printMapRelativeSprites();
		System.out.println("\nScreen relative: ");
		printScreenRelativeSprites();
	}

	/**
	 * Constructs a map with sprites
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
	
	/**
	 * Converts a map relative coordinate to a screen relative point. 
	 * 
	 * @param mapRelative A map relative coordinate to be converted 
	 * @return The screen relative coordinate
	 */
	public Coordinate mapToScreenConversion(Coordinate mapRelative) {
		return mapToScreenConversion(mapRelative, playerStartingPosition, screenSize);
	}
	
	/**
	 * Converts a map relative coordinate to a screen relative point. 
	 * 
	 * @param mapRelative A map relative coordinate to be converted
	 * @param playerStarting A coordinate representing the player starting location
	 * @param screenSize A coordinate representing the size of the screen
	 * @return The screen relative coordinate
	 */
	public Coordinate mapToScreenConversion(Coordinate mapRelative, Coordinate playerStarting, Coordinate screenSize) {
	
		double screenRelativeX = mapRelative.getX() - playerStarting.getX() - (screenSize.getX() / 2.0);
		double screenRelativeY = mapRelative.getY() - playerStarting.getY() - (screenSize.getY() / 2.0);
		
		return new Coordinate(screenRelativeX, screenRelativeY);
	}
	
	/**
	 * Reads in a file of objects (mountain or water), saves these to the map. 
	 * 
	 * @param mapName Name of the map file to read in
	 */
	public void importMap() {
		// Keeps track of the line - used to report errors
		// Starts at one because the lines are not zero indexed
		int lineIndex = 1;
		
		// Open the file mapName
		try(Scanner mapInput = new Scanner(mapFile)){
			
			// Repeat until there is no next line
			while(mapInput.hasNextLine()) {
				
				// Get the x and y value from the file
				double objectX = mapInput.nextDouble() * 1.0;
				double objectY = mapInput.nextDouble() * 1.0;
				
				// Read in the string - indicates what type of object to create
				String type = mapInput.next();
				
				// Checks for end case
				if(type.equals("end")) {
					return;
				}
				
				// Test for coordinates being out of bounds
				if(objectX < 0 || objectY < 0) {
					System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "\n\tX or Y coordinate is negative");
				}
				else if(objectX > mapSize.getX() || objectY > mapSize.getY()) {
					System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "\n\tX or Y coordinate is greater than the max value");
				}
				// Coordinates are within the bounds
				else {
					
					// Mountain case
					if(type.equals("mountain")) {
						sprites.add(new Mountain(objectX, objectY));
					}
					// Water case
					else if(type.equals("water")) {
						// TODO Water class not created or implemented yet
						//sprites.add(new Water(objectX, objectY));
					}
					// Player case, sets the player's starting point
					else if(type.equals("player")) {
						playerStartingPosition = new Coordinate(objectX, objectY);
					}
					// No recognizable type
					else {
						System.out.println("Map Creation Error: Incorrect label at line " + lineIndex);
					}
				}
				// Move cursor to the next line
				mapInput.nextLine();
				// Increase lineIndex - on to the next line
				lineIndex++;
			}
		}
		// Catch if the file cannot be found
		catch(Exception FileNotFoundException) {
			// Print error message
			System.out.println(FileNotFoundException.getMessage());
		}
	}
	
	/**
	 * Debugging method. 
	 * Converts every sprite to screen relative, then 
	 * calls the toString method for every sprite in the array sprites. 
	 * 
	 */
	private void printScreenRelativeSprites() {
		
		Coordinate screenRelativePlayer = mapToScreenConversion(playerStartingPosition);
		
		System.out.printf("(%5.0f, %5.0f)\tPlayer\n", screenRelativePlayer.getX(), screenRelativePlayer.getY());
		
		// Loop through sprites and prints the string from to String
		for(int i = 0; i < sprites.size(); i++) {
			
			System.out.println(mapToScreenConversion(new Coordinate(sprites.get(i).getXValue(), sprites.get(i).getYValue())).toString());
		}
	}
	
	/**
	 * Debugging method. 
	 * Calls the toString method for every sprite in the array sprites. 
	 * 
	 */
	private void printMapRelativeSprites() {
		
		System.out.printf("(%5.0f, %5.0f)\tPlayer\n", playerStartingPosition.getX(), playerStartingPosition.getY());
		
		// Loop through sprites and prints the string from toString
		for(int i = 0; i < sprites.size(); i++) {
			
			System.out.println(sprites.get(i).toString());
		}
	}
	
}
