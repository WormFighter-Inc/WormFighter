package game.generation;

import java.util.ArrayList;

public class MountainRange {
	
	// Data field for the Point object for the starting point
	private Point startingPoint;
	private int changeX;
	private int changeY;
	private int slopeSign;
	private int rangeLength;
	
	private GameStats gameStats;
	
	// Data fields holding mountains and reserved points
	private ArrayList<Point> mountains;
	private ArrayList<Point> reservedPoints;
	
	/**
	 * Mountain ranges can end off screen (don't worry about the ending point, the range will just cut off with the bounds of the map as it is drawn)
	 * 
	 * @param stats GameStats object for the game
	 */
	public MountainRange(GameStats stats) {
		
		gameStats = stats;
		rangeLength = gameStats.getRangeLength();
		
		startingPoint = new Point(gameStats);
		
		newSlope();
		
		mountains = new ArrayList<>(gameStats.getRangeLength());
		reservedPoints = new ArrayList<>(gameStats.getRangeLength() * 3);
		
		createPoints();
		
	}
	
	/**
	 * Adds a point to an array where everything is sorted by the y values. 
	 * 
	 * @param pointArray An ArrayList for Points
	 * @param aPoint A Point to add to the ArrayList
	 */
	public boolean addToArray(ArrayList<Point> pointArray, Point aPoint) {
		
		for(int index = 0; index < pointArray.size(); index++) {
			
			if(aPoint.getYValue() <= (pointArray.get(index)).getYValue()) {
				// This version of add will throw an exception if it is not successful, no check needed
				pointArray.add(index, aPoint);
				return true;
			}
		}
		
		// Tests if aPoint was added to the end of the ArrayList
		if(pointArray.add(aPoint)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	private void createPoints() {
		
		int mountains = 0;
		
		int nextMountainX = startingPoint.getXValue();
		int nextMountainY = startingPoint.getYValue();
		int currentY = startingPoint.getYValue() - 1;
		
		reservedPoint(nextMountainX, currentY);
		
		// Loop until the number of mountains created is equal to rangeLength or the current layer is not on the map
		for(currentY++; mountains < rangeLength && currentY < gameStats.getHeight(); currentY++) {
			
			// Current layer contains a mountain
			if(currentY == nextMountainY) {
				
				mountainPoint(nextMountainX, currentY);
				
				// First mountain placed
				if(mountains == 0) {
					// Reserves spot to the left of the first mountain
					reservedPoint(nextMountainX - (1 * slopeSign), currentY);
					
					// Reserves the spots from the x value of the next mountain to the x value of the current mountain
					for(int spotsToReserve = (changeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
						reservedPoint(nextMountainX + (spotsToReserve * slopeSign), currentY);
					}
				}
				// Last mountain placed
				else if(mountains + 1 == rangeLength) {
					
					// Reserves the spots from the x values of the previous mountain to the x value of the previous mountain
					for(int spotsToReserve = (changeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
						reservedPoint(nextMountainX - (spotsToReserve * slopeSign), currentY);
					}
					
					reservedPoint(nextMountainX + (1 * slopeSign), currentY);
				}
				// Mountains in the middle
				else {
					
					for(int spotsToReserve = (changeX * slopeSign); spotsToReserve > 0; spotsToReserve--) {
						reservedPoint(nextMountainX - spotsToReserve, currentY);
						reservedPoint(nextMountainX + spotsToReserve, currentY);
					}
				}
				
				nextMountainX += changeX;
				nextMountainY += changeY;
				mountains++;
			}
			// Current layer does not contain a mountain
			else {
				reservedPoint(nextMountainX - changeX, currentY);
				reservedPoint(nextMountainX, currentY);
			}
		}
		
		reservedPoint(nextMountainX - changeX, currentY);
	}
	
	public Point[] getMountains() {
		
		Point[] mountainsReturn = new Mountain[mountains.size()];
		
		for(int index = 0; index < mountainsReturn.length; index++) {
			mountainsReturn[index] = mountains.get(index);
		}
		
		return mountainsReturn;
	}
	
	/**
	 * Gets the rise of the slope. 
	 * 
	 * @return The rise of the slope
	 */
	public int getRise() {
		return changeY;
	}
	
	/**
	 * Gets the run of the slope. 
	 * 
	 * @return The run of the slope
	 */
	public int getRun() {
		return changeX;
	}
	
	/**
	 * Gets the slope of the mountain range. 
	 * 
	 * @return Slope between the start and end point
	 */
	public double getSlope() {
		return (changeY * 1.0) / (changeX * 1.0);
	}
	
	/**
	 * Getter method for the x value of the start point
	 * 
	 * @return The x value of the start point
	 */
	public int getStartingX() {
		return startingPoint.getXValue();
	}
	
	/**
	 * Getter method for the y value of the start point
	 * 
	 * @return The y value of the start point
	 */
	public int getStartingY() {
		return startingPoint.getYValue();
	}
	
	/**
	 * Creates a slope of either 1/(1-3) or (1-3)/1
	 */
	public void newSlope() {
		
		// Choosing the change in x to be 1
		if(Math.random() < 0.5) {
			changeX = 1;
			changeY = (int)(Math.random() * 3) + 1; // Random changeY 1-3
		}
		// Choosing the change in y to be 1
		else {
			changeY = 1;
			changeX = (int)(Math.random() * 3) + 1;
		}
		
		// Choosing if the slope is positive or negative
		if(Math.random() < 0.5) {
			slopeSign = -1;
		}
	}
	
	/**
	 * Adds a mountain point with the given coordinates to the list of mountain points
	 * 
	 * @param xValue X-value of the mountain point
	 * @param yValue Y-value of the mountain point
	 * @return  true if the point was added. 
	 * 			false if the point was not added.
	 */
	private boolean mountainPoint(int xValue, int yValue) {
		
		Mountain aMountainPoint = new Mountain(xValue, yValue, gameStats);
		
		if(aMountainPoint.checkInitialization() && aMountainPoint.isOnMap(gameStats)) {
			return addToArray(reservedPoints, aMountainPoint);
		}
		
		return false;
	}
	
	/**
	 * Print the coordinates of the starting and ending point along with the distance between the two
	 * 
	 * @param startingPoint The point for the start of the mountain range
	 */
	@SuppressWarnings("unused")
	private void printStats() {
		
		// Print the coordinates of the starting point
		System.out.printf("Starting Point: (%d, %d)\n", startingPoint.getXValue(), startingPoint.getYValue());

		// Print the slope of the mountain range
		System.out.printf("Slope: %f\n\n", getSlope());
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Starting Point: (" + startingPoint.getXValue() + ", " + startingPoint.getYValue() + ")\nSlope: " + getSlope() + "\n";
	}
	
	/**
	 * Adds a reserved point with the given coordinates to the list of reserved points
	 * 
	 * @param xValue X-value of the reserved point
	 * @param yValue Y-value of the reserved point
	 * @return  true if the point was added. 
	 * 			false if the point was not added.
	 */
	private boolean reservedPoint(int xValue, int yValue) {
	
		Reserved aReservedPoint = new Reserved(xValue, yValue, gameStats);
		
		if(aReservedPoint.checkInitialization() && aReservedPoint.isOnMap(gameStats)) {
			return addToArray(reservedPoints, aReservedPoint);
		}
		
		return false;
	}
}
