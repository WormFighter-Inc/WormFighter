package game.generation;

import java.util.ArrayList;

public class Layer {
	
	/*
	 * "^^" - Mountain range mountain
	 * "/\" - Mountain
	 * "@r" - Reserved spot
	 * 
	 */
	
	// Holds the width of the game
	private int gameWidth;
	
	// This is the heart of Layer, each element represents a horizontal position in the layer
	private ArrayList<String> layerContents = new ArrayList<>(0);
	
	/**
	 * Creates a layer where all the horizontal positions are filled with blank space. 
	 * 
	 * @param givenGameWidth Width of the game, gives the width the layer needs to be
	 */
	Layer(int givenGameWidth){
		
		// Sets the game width to the given game width
		gameWidth = givenGameWidth;
		
		// Creates a blank layer
		for(int currentPos = 0; currentPos < gameWidth; currentPos++) {
			// Adds blank space to each element of layerContents
			layerContents.add("  ");
		}
	}
	
	/**
	 * Checks to see if the spot is open. 
	 * 
	 * @param xValue x value of the feature
	 * @return True if the spot contains "  " (blank), false if it contains anything else
	 */
	public Boolean canAddFeature(int xValue,  Boolean canOverwriteReserved) {
		// If the spot is blank then a mountain can be placed
		if(layerContents.get(xValue) == "  " || (canOverwriteReserved && layerContents.get(xValue) == "@r")) {
			return true; // The spot is empty, so a mountain can be added here
		}
		// The spot is not blank
		else {
			return false; // The mountain cannot be placed
		}
	}
	
	/**
	 * Will overwrite any feature in that spot. Preventing overwriting must be handled before this is called. 
	 * 
	 * @param xValue x value of the feature
	 * @param feature Two character wide representation of the feature. Must be in the format that System.out.printf requires. 
	 */
	private void addFeature(int xValue, String feature) {
		layerContents.set(xValue, feature);
	}
	
	/**
	 * Adds a mountain to the given x value. 
	 * Will not overwrite any feature in that spot. 
	 * 
	 * @param xValue x value of the mountain
	 */
	public void addMountain(int xValue) {
		addFeature(xValue, "/\\");
	}
	
	/**
	 * Adds a mountain for a mountain range to the given x value. 
	 * Will not overwrite any feature in that spot. 
	 * 
	 * @param xValue x value of the mountain range mountain
	 */
	public void addMountainR(int xValue) {
		addFeature(xValue, "^^");
	}
	
	
	/**
	 * Getter for the state of the given position in the layer. 
	 * Two possible states are either "  " (blank) or "/\\" (mountain). 
	 * 
	 * @param position The horizontal position to find the state of
	 * @return "  " - blank, "/\\" - mountain
	 */
	public String getState(int position) {
		return layerContents.get(position);
	}

}
