package ui.actors;

import com.badlogic.gdx.Gdx;

public class Layer extends DisplayContainer {
	public Layer() {
		setWidth(Gdx.graphics.getWidth());
		setHeight(Gdx.graphics.getHeight());
	}
}
