package game.generation;

public class DrawGame {
	
	/**
	 * 
	 * 
	 * @param map
	 */
	public static void drawGame(Map map) {
		
		// Draw the outline on the top of the map
		drawTopBottom(map.getGameWidth());
		
		// Draw each layer (top to bottom)
		for(int layer = 0; layer < map.getGameHeight(); layer++) {
			drawLayer(map.getGameWidth(), map.getLayer(layer));
		}
		
		// Draw the outline of the bottom of the map
		drawTopBottom(map.getGameWidth());
	}
	
	/**
	 * 
	 * @param width
	 * @param gameLayerLayout
	 */
	private static void drawLayer(int width, Layer gameLayerLayout) {
		// Draw the outline on the left side of the layer
		drawTileRectangle(2, 1);
		
		// Print the state of each position within the layer
		for(int widthPos = 0; widthPos < width; widthPos++) {
			System.out.printf(" %s ", gameLayerLayout.getState(widthPos));
		}
		
		// Draw the outline of the right side of the layer
		drawTileRectangle(2, 1);
		nextLine(); // Move cursor to the next line
	}
	
	/**
	 * Prints specified amount of outline tiles for the display. 
	 * 
	 * @param numTiles The number of tiles to be printed
	 */
	private static void drawOutlineTile(int numTiles) {
		// Loops printing the tile the given number of times
		for(int currentTile = 0; currentTile < numTiles; currentTile++) {
			System.out.print("[--]");
		}
	}
	
	/**
	 * Draws a solid rectangle of outline tiles. 
	 * Dimensions are given by the height and width parameters. 
	 * Cursor ends at the bottom right of the rectangle: to the right of the last printed tile. 
	 * 
	 * @param height The height of the rectangle
	 * @param width The width of the rectangle
	 */
	private static void drawTileRectangle(int width, int height) {
		
		// Prints layer by layer
		for(int currentLayer = 0; currentLayer < height; currentLayer++) {
			drawOutlineTile(width);
			
			// Only prints newline if there is another layer of tiles to print
			// Leaves the cursor at the bottom right of the rectangle after it is printed
			if (currentLayer != height - 1) {
				nextLine();
			}
		}
	}
	
	/**
	 * 
	 * @param gameWidth
	 */
	private static void drawTopBottom(int gameWidth) {
		// Must add 4 to givenGameWidth to account for the thickness of the tiles on both sides of the map
		drawTileRectangle(gameWidth + 4, 2);
		nextLine();
	}
	
	/**
	 * Prints newline character. 
	 * Moves to the next layer in the display. 
	 */
	private static void nextLine() {
		System.out.print("\n");
	}

}
