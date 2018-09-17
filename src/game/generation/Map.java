package game.generation;

import java.util.ArrayList;

import game.ui.Mountain;
import game.ui.MountainRange;

public class Map {
	
	// Data fields
	private int gameWidth;
	private int gameHeight;
	private int totalMountains;
	private int totalMountainRanges;
	private int rangeLength;
	
	private GameStats gameStats;
	
	private Boolean canOverwrite = true;
	private Boolean cannotOverwrite = false;
	
	// Holds the number of mountains placed on the map at the given time
	private int mountainCount;
	
	// Holds all the layers
	private ArrayList<Layer> mapLayout = new ArrayList<>(0);
	
	
	
	/*
	 * The mountains placed in the mountain range count toward totalMountains
	 * Add a test to make sure mountains are not being placed too close to the mountain ranges
	 * Figure out some equation to make the mountains a certain percentage of the map
	 * 
	 * First place mountain ranges, keep track of the number of mountains places
	 * Second place the remaining mountains, found by: totalMountains - mountainsFromRanges
	 * 
	 */
	
	/**
	 * Creates a map for the game. 
	 * Uses a coordinate system where 0, 0 is at the top right. 
	 * 
	 * @param givenGameWidth The width of the rows on the map
	 * @param givenGameHeight The height of the columns on the map
	 * @param totalMountains The total number of mountains to be placed on the map. Cannot exceed the total number of positions on the map.
	 */
	public Map (GameStats stats) /* TODO throws Exception*/{
		
		gameStats = stats;
		
		// Set the data fields based on the parameters
		gameWidth = stats.getWidth();
		gameHeight = stats.getHeight();
		totalMountains = stats.getNumOfMountains();
		totalMountainRanges = stats.getNumOfMountainRanges();
		rangeLength = stats.getRangeLength();
		
		// Sets mountain count to 0 because there are no mountains placed at this time
		mountainCount = 0;
		
		// The game will create the exactly the number of mountains given by totalMountains
		// If there are more totalMountains than spots to place them, then there will be an infinite loop of creating mountains
		if(totalMountains > (gameWidth * gameHeight)) {
			System.out.print("ERROR: Total number of mountains exceeds the number of positions on the map. The maximum amount of mountains is: " + (gameHeight * gameWidth));
			System.exit(0);
			//throw new RuntimeException("The total number of mountains cannot exceed the total number of positions on the map.");
			// TODO NEED TO THROW EXCEPTION - otherwise game will be stuck in infinite loop b/c it will only add a mountain to an open spot
			// TODO Handle the exception in Game
		}
		
		// Creates a blank game map
		// Populates mapLayout with blank layers
		// For each layer, add a new layer to mapLayout
		// The new layer is blank by default
		for(int currentLayer = 0; currentLayer < gameHeight; currentLayer++) {
			mapLayout.add(new Layer(gameWidth));
		}
		
		//populateWithMountainRanges();
		populateWithMountains();

	} 
	
	/**
	 * Adds mountain ranges to the map. 
	 * Will attempt to place them until there are totalMountainRanges number of ranges or there are ten times more attempts than totalMountainRanges. 
	 */
	@SuppressWarnings("unused")
	private void populateWithMountainRanges() {
		
		int mountainRanges = 0;
		
		while(mountainRanges < totalMountainRanges) {
		
			MountainRange newRange = new MountainRange(gameStats);
		
			// If the spot where the starting point of the mountain range is open, then create a new mountain range and increase the count of mountain ranges
			// Reserved characters will be overwritten
			if(mapLayout.get(newRange.getStartingY()).canAddFeature(newRange.getStartingX(), canOverwrite)) {
				createMountainRange(newRange);
				mountainRanges++;
			}
		}
	}
	
	private void createMountainRange(MountainRange range) {
		
			int placedMountains = 0;
			
			// Get all info from newRange into variables listed below
			int mountainRangeLength = 3;
			
			int startPointX = 5;
			int startPointY = 1;
			
			int slopeX = 1;
			int slopeY = 0;
			
			// Slope sign represents the sign of the slope, either positive (1) or negative (-1)
			int slopeSign = 1;
			
			// Checking to see if the slope is negative
			if((slopeX * 1.0) / (slopeY * 1.0) < 0) {
				// Slope sign is set to a negative
				slopeSign = -1;
			}
			
			int nextMountainX = startPointX;
			int nextMountainY = startPointY;
			int currentY = startPointY - 1;
			
			reserved(nextMountainX, currentY);
			
			for(currentY++; placedMountains < mountainRangeLength && currentY < gameHeight; currentY++) {
				if(currentY == nextMountainY) {
					System.out.print("\n");
					mountain(nextMountainX, currentY);
					
					// First mountain placed
					if(placedMountains == 0) {
						// Reserves spot to the left of the first mountain
						reserved(nextMountainX - (1 * slopeSign), currentY);
						
						// Reserves the spots from the x value of the next mountain to the x value of the current mountain
						for(int spotsToReserve = (slopeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
							reserved(nextMountainX + (spotsToReserve * slopeSign), currentY);
						}
					}
					// Last mountain placed
					else if(placedMountains + 1 == mountainRangeLength) {
						
						// Reserves the spots from the x values of the previous mountain to the x value of the previous mountain
						for(int spotsToReserve = (slopeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
							reserved(nextMountainX - (spotsToReserve * slopeSign), currentY);
						}
						
						reserved(nextMountainX + (1 * slopeSign), currentY);
					}
					// Mountains in the middle
					else {
						
						for(int spotsToReserve = (slopeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
							reserved(nextMountainX - spotsToReserve, currentY);
							reserved(nextMountainX + spotsToReserve, currentY);
						}
					}
					
					nextMountainX += slopeX;
					nextMountainY += slopeY;
					placedMountains++;
				}
				else {
					// Maybe loop here
					// This part needs some work
					reserved(nextMountainX - slopeX, currentY);
					reserved(nextMountainX, currentY);
	
				}
			}
			
			reserved(nextMountainX - slopeX, currentY);
	}
	

	private void reserved(int xValue, int yValue) {
		
		//Layer currentLayer = mapLayout.get(yValue);
		
	}
	
	
	private void mountain(int xValue, int yValue) {
		
		Layer currentLayer = mapLayout.get(yValue);
		
		if(currentLayer.canAddFeature(xValue, canOverwrite)) {
			currentLayer.addMountainR(xValue);
		}
		else {
			// TODO Figure out what to do here - make method return boolean?
			System.out.print("ERROR: Error with placing mountain range");
		}
		
	}
	
	
	/**
	 * Adds mountains to the blank map until the number of mountains is totalMountains. 
	 */
	private void populateWithMountains() {
		// Adds mountains to the blank map, while the mountain count is less that the total mountains
		while(mountainCount < totalMountains) {
					
			// Creates a new mountain, and gets the Layer based on the new mountain's height
			Mountain newMountain = new Mountain(gameStats);
			Layer gameLayer = mapLayout.get(newMountain.getYValue());
					
			// If the new mountain was able to be created, then mountainCount is increased
			if(gameLayer.canAddFeature(newMountain.getXValue(), cannotOverwrite)) {
				gameLayer.addMountain(newMountain.getXValue());
				mountainCount++;
			}
		}
		
	}

	/**
	 * Getter method for the height of the map. 
	 * 
	 * @return Height of the map
	 */
	public int getGameHeight() {
		return gameHeight;
	}
	
	/**
	 * Getter method for the width of the map. 
	 * 
	 * @return Width of the map
	 */
	public int getGameWidth() {
		return gameWidth;
	}
	
	/**
	 * Getter method for the layer based on the given layerHeight
	 * 
	 * @param layerHeight The layer to get the Layer object of
	 * @return The Layer object at the given layerHeight
	 */
	public Layer getLayer(int layerHeight) {
		return mapLayout.get(layerHeight);
	}

}
