package game.generation;

import java.util.ArrayList;
import game.ui.Sprite;
import javafx.scene.Parent;

public class Map extends Parent{
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	Map(ArrayList<Sprite> sprites){
		for(int i = 0; i < sprites.size(); i ++) {
			this.getChildren().add(sprites.get(i));
			this.sprites = sprites;
		}
	}
	
	public boolean addSprite(Sprite newSprite){
		sprites.add(newSprite);
		return true;
	}
}
