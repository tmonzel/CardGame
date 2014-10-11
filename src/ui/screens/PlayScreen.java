package ui.screens;

import materials.Player;
import materials.Table;
import ui.buttons.DefaultButton;
import ui.presenters.MarketPresenter;
import ui.presenters.PlayerPresenter;
import app.CardGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayScreen extends TableScreen {
	
	@Override
	public void initialize() {
		Table t = new Table();
		t.startWith(0);
		
		// Adding players
		t.addPlayer(new Player("Thomas"));
		t.addPlayer(new Player("Marco"));
		t.addPlayer(new Player("Pedram"));
		t.addPlayer(new Player("Melli"));
		
		PlayerPresenter playerPresenter = new PlayerPresenter(t);

		MarketPresenter marketPresenter = new MarketPresenter(t);
		marketPresenter.presentDeck(CardGame.cards().buildDeck(30));

		_stage.addActor(marketPresenter.getLayer());
		_stage.addActor(playerPresenter.getLayer());
		setupHelperButtons();
		
		//marketPresenter.startPeriod();
	}
	
	private void setupHelperButtons() {
		DefaultButton exitButton = new DefaultButton("Beenden");
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		exitButton.setPosition(60, Gdx.graphics.getHeight()-50);
		_stage.addActor(exitButton);
	}
}
