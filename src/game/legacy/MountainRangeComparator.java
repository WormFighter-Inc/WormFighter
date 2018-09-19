package game.legacy;

import java.util.Comparator;

public class MountainRangeComparator implements Comparator<MountainRange>{

	/**
	 * Compares range1 and range2. 
	 * 
	 * @param range1 First MountainRange object
	 * @param range2 Second MountainRange object
	 * @return  negative: if range1 is less than range2. 
	 * 			0: if range1 and range2 are equal. 
	 * 			positive: if range1 is greater than range2. 
	 */
	public int compare(MountainRange range1, MountainRange range2) {
		
		if(range1.getStartingY() < range2.getStartingY()) {
			return -1;
		}
		// If the y values are the same, then compare the x values
		else if(range1.getStartingY() == range2.getStartingY()){
			return compareX(range1, range2);
		}
		else {
			return 1;
		}
	}
	
	/**
	 * Compares the x value of range1 and range2. 
	 * 
	 * @param range1 First MountainRange object
	 * @param range2 Second MountainRange object
	 * @return  negative: if the x-value of range1 is less than the x-value of range2. 
	 * 			0: if the x-values of range1 and range2 are equal. 
	 * 			positive: if the x-value of range1 is greater than the x-value of range2. 
	 */
	private int compareX(MountainRange range1, MountainRange range2) {
		if(range1.getStartingX() < range2.getStartingX()) {
			return -1;
		}
		else if(range1.getStartingX() == range2.getStartingX()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
