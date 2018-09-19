package game.ui;

import game.legacy.GameStats;
import game.legacy.Mountain;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TwoDimensionalTestSuite extends Application{
	
	private Pane root = new Pane();
	LightWorm test = new LightWorm(0, 0, 50, 50);

	
	/**
	 * Creates the scene with content in it
	 * 
	 * @return pane with conent
	 */
	private Parent createContent() {
		
		
		//create size and add test sprite
		root.setPrefSize(600,  800);
		root.getChildren().add(test);

		
		AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		timer.start();
		
		return root;
	}
	
	private void update() {
		boolean[] currentState = test.getState();
		
		System.out.printf("%f", test.getTranslateX());
		System.out.printf(" %f%n" , test.getTranslateY());
		
		//updating movement based on state of keys pressed and test for hitting wall boundaries
		if(currentState[0]) {
			test.moveLeft(5);
		}
		
		if(currentState[1] && test.getTranslateX() < (root.getWidth() - test.getFitWidth())) {
			test.moveRight(5);
		}
		
		if(currentState[2] && test.getTranslateY() > -10) {
			test.moveUp(5);
		}
		
		if(currentState[3] && test.getTranslateY() < (root.getHeight() - test.getFitHeight() + 14)) {
			test.moveDown(5);
		}
		
	}
	
	public void start(Stage theStage) {
		Scene mainScene = new Scene(createContent());
		
		mainScene.setOnKeyPressed(e -> {
			//TODO: Create booleans for capture, Handle in main update
			switch(e.getCode()) {
				case A:
					test.setMoveLeft(true);
					break;
				case S:
					test.setMoveDown(true);
					break;
				case D:
					test.setMoveRight(true);
					break;
				case W:
					test.setMoveUp(true);
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
					test.setMoveLeft(false);
					break;
				case S:
					test.setMoveDown(false);
					break;
				case D:
					test.setMoveRight(false);
					break;
				case W:
					test.setMoveUp(false);
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
