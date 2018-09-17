package game.generation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TwoDimensionalTestSuite extends Application{
	
	private Pane root = new Pane();

	
	Worm test = new Worm();
	
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
		
		//updating movement based on state of keys pressed and test for hitting wall boundaries
		if(currentState[0] && test.getTranslateX() > 0) {
			test.moveLeft();
		}
		
		if(currentState[1] && test.getTranslateX() < (root.getWidth() - test.getFitWidth())) {
			test.moveRight();
		}
		
		if(currentState[2] && test.getTranslateY() > -10) {
			test.moveUp();
		}
		
		if(currentState[3] && test.getTranslateY() < (root.getHeight() - test.getFitHeight() + 14)) {
			test.moveDown();
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
