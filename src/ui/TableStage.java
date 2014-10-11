package ui;

import app.CardGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TableStage extends Stage {
	private Image _boardImage;
	
	public TableStage() {
		super(new ScreenViewport());
		
		_boardImage = new Image(CardGame.assets().get("assets/background.jpg", Texture.class));
		addActor(_boardImage);
	}
}