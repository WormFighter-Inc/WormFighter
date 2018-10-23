package game.generation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import game.ai.MoveableSprite;
import game.ui.Mountain;
import game.utility.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.shape.Polyline;

public class Map{
	
	private ArrayList<MoveableSprite> sprites = new ArrayList<MoveableSprite>();
	private ArrayList<Polyline> bounds = new ArrayList<Polyline>();
	
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
		mapSize = new Coordinate(3840, 2160);
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
		
		Iterator<Polyline> boundIterator = bounds.iterator();
		
		while(boundIterator.hasNext()) {
			Polyline temp = boundIterator.next();
			temp.setTranslateX(temp.getTranslateX() + 5);
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
		
		Iterator<Polyline> boundIterator = bounds.iterator();
		
		while(boundIterator.hasNext()) {
			Polyline temp = boundIterator.next();
			temp.setTranslateX(temp.getTranslateX() - 5);
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
		
		Iterator<Polyline> boundIterator = bounds.iterator();
		
		while(boundIterator.hasNext()) {
			Polyline temp = boundIterator.next();
			temp.setTranslateY(temp.getTranslateY() - 5);
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
		
		Iterator<Polyline> boundIterator = bounds.iterator();
		
		while(boundIterator.hasNext()) {
			Polyline temp = boundIterator.next();
			temp.setTranslateY(temp.getTranslateY() + 5);
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
	 * Gets the ArrayList of the polylines on the map
	 * 
	 * @return ArrayList of bounds
	 */
	public ArrayList<Polyline> getBounds(){
		return bounds;
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
		
		// TODO Implement these
		//_______________________________________________________________________________________________
		// Used to store the size of the sprite X x Y
		Coordinate spriteSize = new Coordinate(100, 100);
		//_______________________________________________________________________________________________
		
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
					Image mapImage = new Image("/game/generation/maps/" + mapInput.next());
					Coordinate mapSize = new Coordinate(mapInput.nextDouble(), mapInput.nextDouble());
					
					// Gets the background image
					sprites.add(new MoveableSprite(origin.getX(), origin.getY(), mapSize.getIntX(),  mapSize.getIntY(), mapImage));
				}
				//Checks for polyline
				else if(type.equals("poly")) {
					//first entry is number of segments followed by segment coordinants
					int numberOfPoints = mapInput.nextInt();
					double points[] = new double[numberOfPoints*2];
					for(int i = 0; i < numberOfPoints*2; i += 2) {
						Coordinate temp = new Coordinate(mapInput.nextDouble(), mapInput.nextDouble());
						//convert to screen perspective coordinates
						temp = mapToScreen(temp);
						points[i] = temp.getX();
						points[i+1] = temp.getY();
					}
					//Gets the polyline
					Polyline temp = new Polyline(points);
					temp.setStrokeWidth(10);
					bounds.add(temp);
				}
				
				else {
					// Get the x and y value from the file
					Coordinate objectLocation = new Coordinate(mapInput.nextDouble() * 1.0, mapInput.nextDouble() * 1.0);
					
					// Determining if the object is valid and creating it
					if(isInBounds(objectLocation, lineIndex, spriteSize)) {
						
						// Determine which type of object is being read in
						switch(type) {								
							case "player":
								// Set the player's starting position
								playerStartingPosition = new Coordinate(objectLocation.getX(), objectLocation.getY());
								break;
						
							case "mountain":
								// Add a mountain sprite
								sprites.add(new Mountain(spriteSize.getIntX(), spriteSize.getIntY(), mapToScreen(objectLocation)));
								break;
								
							case "water":
								// Add a water sprite
								// TODO Water class not created or implemented yet
								//sprites.add(new Water(spriteWidth, spriteHeight, mapToScreen(objectLocation));
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
	private boolean isInBounds(Coordinate objectLocation, int lineIndex, Coordinate spriteSize) {
		
		// Test for coordinates being out of bounds
		if(objectLocation.getX() < 0 || objectLocation.getY() < 0) {
			System.out.println("Map Creation Error: Coordinate error at line " + lineIndex + "\n\tX or Y coordinate is negative");
			return false;
		}
		else if(objectLocation.getX() > mapSize.getX() - spriteSize.getX() || objectLocation.getY() > mapSize.getY() - spriteSize.getY()) {
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
	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
	private void printMapRelativeSprites() {
		
		System.out.printf("(%5.0f, %5.0f)\tPlayer\n", playerStartingPosition.getX(), playerStartingPosition.getY());
		
		// Loop through sprites and prints the string from toString
		for(int i = 0; i < sprites.size(); i++) {
			
			System.out.println(sprites.get(i).toString());
		}
	}
	
}
