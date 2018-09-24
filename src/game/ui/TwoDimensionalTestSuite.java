package game.ui;

import java.util.ArrayList;
import java.util.Arrays;

import game.ai.MoveableSprite;
import game.ai.worm.LightWorm;
import game.generation.Map;
import game.player.Player;
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

	LightWorm test = new LightWorm(0, 0, 50, 50);
	LightWorm test2 = new LightWorm(100, 100, 50, 50);
	MoveableSprite testArt = new MoveableSprite(0, 0, 3840, 2160, new Image("game/generation/maps/Test Art.png"));
	
	ArrayList<MoveableSprite> myArray = new ArrayList<MoveableSprite>(Arrays.asList(testArt, test, test2));
	Map testMap = new Map(myArray, new Player((screenWidth / 2.0) - (playerWidth / 2.0), (screenHeight / 2.0) - (playerHeight / 2.0), playerWidth, playerHeight), screenHeight);
	
	
	/**
	 * Creates the scene with content in it
	 * 
	 * @return pane with conent
	 */
	private Parent createContent() {	
		//create size and add test sprite
		testMap.setPrefSize(screenWidth, screenHeight);
		
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		
		return testMap;
	}
	
	private void update() {
		
		boolean[] currentState = testMap.getState();

		//updating movement based on state of keys pressed and test for hitting wall boundaries
		if(currentState[0]) {
			testMap.moveLeft();
		}
		
		if(currentState[1]) {
			testMap.moveRight();
		}
		
		if(currentState[2]) {
			testMap.moveUp();
		}
		
		if(currentState[3]) {
			testMap.moveDown();
		}
		
	}
	
	public void start(Stage theStage) {
		Scene mainScene = new Scene(createContent());
		
		mainScene.setOnKeyPressed(e -> {
			//TODO: Create booleans for capture, Handle in main update
			switch(e.getCode()) {
				case A:
					testMap.setMoveLeft(true);
					break;
				case S:
					testMap.setMoveDown(true);
					break;
				case D:
					testMap.setMoveRight(true);
					break;
				case W:
					testMap.setMoveUp(true);
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
					testMap.setMoveLeft(false);
					break;
				case S:
					testMap.setMoveDown(false);
					break;
				case D:
					testMap.setMoveRight(false);
					break;
				case W:
					testMap.setMoveUp(false);
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
