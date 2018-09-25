package game.generation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import game.ai.MoveableSprite;
import game.ai.Sprite;
import game.ai.worm.LightWorm;
import game.player.Player;
import game.ui.HUD;
import game.ui.Mountain;
import game.utility.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Map{
	
	private ArrayList<MoveableSprite> sprites = new ArrayList<MoveableSprite>();
	
	// TODO Implement these for real
	private Coordinate mapSize;
	private Coordinate screenSize;
	
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
		
		mapFile = Map.class.getResourceAsStream("/game/generation/maps/" + mapFileName);
		importMap();
		
		/*
		System.out.println("Map relative: ");
		printMapRelativeSprites();
		System.out.println("\nScreen relative: ");
		printScreenRelativeSprites();
		*/
	}
	
	/**
	 * Gets the location of the player's starting position
	 * 
	 * @return A coordinate object representing the player's starting position
	 */
	public Coordinate getPlayerStartingLocation() {
		return playerStartingPosition;
	}
	
	/**
	 * Calls move left on all of the sprites on the map making the player move right relative
	 */
	public void moveSpritesRight() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveRight(5);
		}
	}
	
	/**
	 * Calls move right on all of the sprites on the map making the player move left relative
	 */
	public void moveSpritesLeft() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveLeft(5);
		}
	}
	
	/**
	 * Calls move down on all of the sprites on the map making the player up right relative
	 */
	public void moveSpritesUp() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveUp(5);
		}
	}
	
	/**
	 * Calls move up on all of the sprites on the map making the player move down relative
	 */
	public void moveSpritesDown() {
		Iterator<MoveableSprite> spriteIterator = sprites.iterator();
		
		while(spriteIterator.hasNext()) {
			spriteIterator.next().moveDown(5);
		}
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
		}
		else {
			sprites.remove(targetSprite);
			return true;
		}
	}
	
	/**
	 * Gets the ArrayList of all the MoveableSprites on the map. 
	 * 
	 * @return ArrayList of all the MoveableSprites on the map
	 */
	public ArrayList<MoveableSprite> getSprites(){
		
		// Creates a new ArrayList copies it
		//ArrayList<MoveableSprite> returnSprites = new ArrayList<>(sprites.size());
		
		//for(int index = 0; index < sprites.size(); index++) {
		//	returnSprites.add(sprites.get(index));
		//}
		
		return sprites;
	}
	
	/**
	 * Converts a map relative coordinate to a screen relative point. 
	 * 
	 * @param mapRelative A map relative coordinate to be converted 
	 * @return The screen relative coordinate
	 */
	private Coordinate mapToScreen(Coordinate mapRelative) {
		return mapToScreen(mapRelative, playerStartingPosition, screenSize);
	}
	
	/**
	 * Converts a map relative coordinate to a screen relative point. 
	 * 
	 * @param mapRelative A map relative coordinate to be converted
	 * @param playerStarting A coordinate representing the player starting location
	 * @param screenSize A coordinate representing the size of the screen
	 * @return The screen relative coordinate
	 */
	private Coordinate mapToScreen(Coordinate mapRelative, Coordinate playerStarting, Coordinate screenSize) {
	
		double screenRelativeX = mapRelative.getX() - playerStarting.getX() - (screenSize.getX() / 2.0);
		double screenRelativeY = mapRelative.getY() - playerStarting.getY() - (screenSize.getY() / 2.0);
		
		return new Coordinate(screenRelativeX, screenRelativeY);
	}
	
	/**
	 * Reads in a file of objects (mountain, water, player), saves these to the map. 
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
				
				// Read in the string - indicates what type of object to create
				String type = mapInput.next();
				
				// Checks for end case
				if(type.equals("end")) {
					return;
				}
				// Checks for map art
				else if(type.equals("art")) {
					Coordinate origin = mapToScreen(new Coordinate(0, 0));
					
					// Gets the background image
					sprites.add(new MoveableSprite(origin.getX(), origin.getY(), 3840, 2160, new Image("/game/generation/maps/" + mapInput.next())));
				}
				else {
					// Get the x and y value from the file
					Coordinate objectLocation = new Coordinate(mapInput.nextDouble() * 1.0, mapInput.nextDouble() * 1.0);
					
					// Determining if the object is valid and creating it
					if(isInBounds(objectLocation, lineIndex)) {
						
						// Determine which type of object is being read in
						switch(type) {								
							case "player":
								// Set the player's starting position
								playerStartingPosition = new Coordinate(objectLocation.getX(), objectLocation.getY());
								break;
						
							case "mountain":
								// Add a mountain sprite
								sprites.add(new Mountain(100, 100, mapToScreen(objectLocation)));
								break;
								
							case "water":
								// Add a water sprite
								// TODO Water class not created or implemented yet
								//sprites.add(new Water(objectLocation));
								break;
								
							default:
								// Unexpected type - Error
								System.out.println("Map Creation Error: Incorrect label at line " + lineIndex);
								break;
						}
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
	 * Tests for coordinates being in the bounds of the map
	 * 
	 * @param objectLocation Coordinate object holding the location of the object to be tested
	 * @param lineIndex Line the object is on in the map file
	 * @return true if the coordinate is in the bounds of the map, false if the coordinate is out of the bounds of the map
	 */
	private boolean isInBounds(Coordinate objectLocation, int lineIndex) {
		
		// Test for coordinates being out of bounds
		if(objectLocation.getX() < 0 || objectLocation.getY() < 0) {
			System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "\n\tX or Y coordinate is negative");
			return false;
		}
		else if(objectLocation.getX() > mapSize.getX() || objectLocation.getY() > mapSize.getY()) {
			System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "\n\tX or Y coordinate is greater than the max value");
			return false;
		}
		// Object is in bounds
		return true;
	}
	
	/**
	 * Debugging method. 
	 * Converts every sprite to screen relative, then 
	 * calls the toString method for every sprite in the array sprites. 
	 * 
	 */
	private void printScreenRelativeSprites() {
		
		Coordinate screenRelativePlayer = mapToScreen(playerStartingPosition);
		
		System.out.printf("(%5.0f, %5.0f)\tPlayer\n", screenRelativePlayer.getX(), screenRelativePlayer.getY());
		
		// Loop through sprites and prints the string from to String
		for(int i = 0; i < sprites.size(); i++) {
			
			System.out.println(mapToScreen(new Coordinate(sprites.get(i).getX(), sprites.get(i).getY())).toString());
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
