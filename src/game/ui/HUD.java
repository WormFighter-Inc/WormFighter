package game.ui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class HUD {

	private HBox hudPane;
	private ImageView healthBar;
	
	private double screenHeight;
	private int healthBarPadding = 10;
	private int healthBarPixelHeight = 25;
	
	public HUD(double windowHeight){
		hudPane = new HBox();
		
		screenHeight = windowHeight;
		
		addHealthBar();
	}
	
	/**
	 * Gets the pane holding the HUD
	 * 
	 * @return The pane containing the HUD
	 */
	public HBox getHUD() {
		return hudPane;
	}
	
	/**
	 * Adds the health bar to the hudPane
	 * 
	 * @return True if the health bar was added, False if the health bar was not added
	 */
	private boolean addHealthBar() {
		
		// Create new ImageView based on "Health_Bar.png"
		try {
			healthBar = new ImageView(new Image("/game/ui/art/Health_Bar.png"));
		}
		// Catch the exception
		catch(Exception FileNotFoundException) {
			System.out.println(FileNotFoundException.getMessage());
			return false;
		}
		
		// Set the padding so the health bar is in the bottom right corner of the screen, with padding set by healthBarPadding
		hudPane.setPadding(new Insets(((int)screenHeight) - (healthBarPixelHeight + healthBarPadding), healthBarPadding, healthBarPadding, healthBarPadding));
		// Add healthBar ImageView to the HBox
		hudPane.getChildren().add(healthBar);
		
		return true;
	}
	
	/**
	 * Adjusts the health bar to reflect the health given by newHealth
	 * 
	 * @param newHealth The new value of the player's health
	 * @return True if the health was updated correctly, False if the health was not updated correctly
	 */
	public boolean updateHealthBar(int newHealth) {
		return false;
	}
}
