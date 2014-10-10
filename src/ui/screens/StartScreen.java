package ui.screens;

import ui.buttons.DefaultButton;
import ui.utils.MouseUtil;
import app.CardGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartScreen extends AbstractScreen {
	
	@Override
	public void initialize() {
		DefaultButton db = new DefaultButton("Spiel starten");
		db.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MouseUtil.resetCursor();
				CardGame.switchScreen(new PlayScreen());
			}
		});
		
		Image background = new Image(CardGame.assets().get("assets/background.jpg", Texture.class));
		_stage.addActor(background);
		
		db.setCenterPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		_stage.addActor(db);
	}
}
