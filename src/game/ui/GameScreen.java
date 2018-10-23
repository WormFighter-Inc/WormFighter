package game.ui;

import java.util.ArrayList;
import java.util.Iterator;

import game.ai.MoveableSprite;
import game.generation.Map;
import game.player.Player;
import game.utility.Coordinate;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;

public class GameScreen extends Pane{
	
	// TODO Implement these better
	//__________________________________________________________________________________________
	int playerWidth = 50;
	int playerHeight = 50;
	//__________________________________________________________________________________________
	
	private boolean[] movementStates = {false, false, false, false};
	
	// TODO Implement these for real
	private Coordinate screenSize;
	private HUD healthBar;
	private Player currentPlayer;
	private Map currentMap;
	
	/**
	 * 
	 * @param givenScreenSize 
	 */
	public GameScreen(Coordinate givenScreenSize) {
		currentMap = new Map("Default_Map");
				
		screenSize = givenScreenSize;
		
		healthBar = new HUD(screenSize.getY());
		
		addSprites();
		addPlayer();
		getChildren().add(healthBar.getHUD());
		
	}
	
	/**
	 * Add the player to the scene
	 */
	private void addPlayer() {
		
		// Places the player in the center of the screen
		currentPlayer = new Player((screenSize.getX() / 2.0) - (playerWidth / 2.0), (screenSize.getY() / 2.0) - (playerHeight / 2.0), playerWidth, playerHeight);
		// Adds the player to the pane
		getChildren().add(currentPlayer);
	}
	
	/**
	 * Adds the sprites to the screen, except for the player
	 */
	private void addSprites() {
		//ArrayList<MoveableSprite> tempHolder = currentMap.getSprites();
		Iterator<MoveableSprite> spriteIterator = currentMap.getSprites().iterator();
		
		while(spriteIterator.hasNext()) {
			getChildren().add(spriteIterator.next());
		}
		
		Iterator<Polyline> boundIterator = currentMap.getBounds().iterator();
		
		while(boundIterator.hasNext()) {
			getChildren().add(boundIterator.next());
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
	public void setAll(boolean[] states) {
		this.movementStates[0] = states[0];
		this.movementStates[1] = states[1];
		this.movementStates[2] = states[2];
		this.movementStates[3] = states[3];
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
		currentMap.moveSpritesLeft();
		if(currentPlayer.checkCollisionPolyline(currentMap.getBounds())) {
			currentMap.moveSpritesRight();
		}
	}
	
	/**
	 * Calls move right on all of the sprites on the map making the player move left relative
	 */
	public void moveLeft() {
		currentMap.moveSpritesRight();
	}
	
	/**
	 * Calls move down on all of the sprites on the map making the player up right relative
	 */
	public void moveUp() {
		currentMap.moveSpritesDown();
	}
	
	/**
	 * Calls move up on all of the sprites on the map making the player move down relative
	 */
	public void moveDown() {
		currentMap.moveSpritesUp();
	}
	
	/**
	 * 
	 * @return current player
	 */
	public Player getPlayer() {
		return currentPlayer;
	}
	
	public ArrayList<MoveableSprite> getSprites(){
		return currentMap.getSprites();
	}
}
