package game.utility;

import javafx.geometry.Point2D;

public class Coordinate extends Point2D{

	/**
	 * Creates a new coordinate at (x, y). 
	 * 
	 * @param x X value of the coordinate
	 * @param y Y value of the coordinate
	 */
	public Coordinate(double x, double y) {
		super(x, y);
	}
	
	/**
	 * Creates a nicely formatted string with the coordinates. 
	 * 
	 * @return A formatted string containing the x and y values of the coordinate
	 */
	@Override
	public String toString() {
		return String.format("(%5.0f, ", this.getX()) + String.format("%5.0f)", this.getY());
	}
	
	public int getIntX() {
		return (int)getX();
	}
	
	public int getIntY() {
		return (int)getY();
	}


}
