package ui.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class DisplayContainer extends Group {
	public void moveToStage() {
		Vector2 coords = getParent().localToStageCoordinates(new Vector2(getX(), getY()));
		getStage().addActor(this);
		setPosition(coords.x, coords.y);
	}
	
	public void hide() {
		setVisible(false);
	}
	
	public void show() {
		setVisible(true);
	}
}
