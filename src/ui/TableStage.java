package ui;

import ui.actors.Layer;
import ui.screens.TableScreen;
import app.CardGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TableStage extends Stage {
	private TableScreen _screen;
	private Image _boardImage;
	
	public TableStage(TableScreen screen) {
		super(new ScreenViewport());
		_screen = screen;
		
		_boardImage = new Image(CardGame.assets().get("assets/background.jpg", Texture.class));
		addActor(_boardImage);
	}
	
	public void addLayer(Layer l) {
		_screen.addProcessor(l);
		addActor(l);
	}
}