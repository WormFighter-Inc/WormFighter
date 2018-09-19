package game.legacy;

import java.util.ArrayList;

public class Game {
	
	public static void main(String[] args) {
		
		for(int count = 0; count < 1; count++) {
			Map someMap = new Map(new GameStats(20, 20, 10, 0, 0));
		
			DrawGame.drawGame(someMap);
		}
	
	// TODO Flip all instances where it appears the coordinate system is being represented backward EX: (y, x) should be (x, y)
	// Create mountain ranges - create a line which will be the line of the range, use probability to place the mountains

	}
}