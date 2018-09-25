package game.ui;

import java.util.ArrayList;
import java.util.Arrays;

import game.ai.MoveableSprite;
import game.ai.worm.LightWorm;
import game.generation.Map;
import game.player.Player;
import game.utility.Coordinate;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TwoDimensionalTestSuite extends Application{
	
	double screenWidth = 1000;
	double screenHeight = 600;
	
	int playerWidth = 50;
	int playerHeight = 50;
	
	GameScreen testScreen = new GameScreen(new Coordinate(screenWidth, screenHeight));
	
	/**
	 * Creates the scene with content in it
	 * 
	 * @return pane with content
	 */
	private Parent createContent() {	
		//create size and add test sprite
		testScreen.setPrefSize(screenWidth, screenHeight);
		
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		
		return testScreen;
	}
	
	private void update() {
		
		boolean[] currentState = testScreen.getState();

		//updating movement based on state of keys pressed and test for hitting wall boundaries
		if(currentState[0]) {
			testScreen.moveLeft();
		}
		
		if(currentState[1]) {
			testScreen.moveRight();
		}
		
		if(currentState[2]) {
			testScreen.moveUp();
		}
		
		if(currentState[3]) {
			testScreen.moveDown();
		}
		
	}
	
	public void start(Stage theStage) {
		Scene mainScene = new Scene(createContent());
		
		mainScene.setOnKeyPressed(e -> {
			//TODO: Create booleans for capture, Handle in main update
			switch(e.getCode()) {
				case A:
					testScreen.setMoveLeft(true);
					break;
				case S:
					testScreen.setMoveDown(true);
					break;
				case D:
					testScreen.setMoveRight(true);
					break;
				case W:
					testScreen.setMoveUp(true);
					break;
				default:
					break;
				
				//case SPACE
					//Test.shoot();
					//break;
			}
		});
		
		mainScene.setOnKeyReleased(e ->{
			switch(e.getCode()) {
				case A:
					testScreen.setMoveLeft(false);
					break;
				case S:
					testScreen.setMoveDown(false);
					break;
				case D:
					testScreen.setMoveRight(false);
					break;
				case W:
					testScreen.setMoveUp(false);
					break;
				default:
					break;
			}
		});
		
		theStage.setScene(mainScene);
		theStage.setTitle("Game Testing");
		theStage.show();
	}
	
	public static void main(String[] args) {
		
		/*
		new Map("Default_Map");
		System.exit(0);
		*/
		
		launch(args);
	}

}
