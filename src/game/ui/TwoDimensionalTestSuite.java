package game.ui;

import java.util.ArrayList;
import java.util.Arrays;

import game.generation.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TwoDimensionalTestSuite extends Application{

	MoveableSprite test = new LightWorm(0, 0, 50, 50);
	LightWorm test2 = new LightWorm(100, 100, 50, 50);
	
	ArrayList<MoveableSprite> myArray = new ArrayList<MoveableSprite>(Arrays.asList(test, test2));
	Map testMap = new Map(myArray, new Player(275, 375, 50, 50));
	
	
	/**
	 * Creates the scene with content in it
	 * 
	 * @return pane with conent
	 */
	private Parent createContent() {	
		//create size and add test sprite
		testMap.setPrefSize(600,  800);
		
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
		
		launch(args);
	}

}
