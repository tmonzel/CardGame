package ui.screens;

import materials.Deck;
import materials.Market;
import materials.Player;
import materials.Table;
import ui.buttons.DefaultButton;
import ui.presenters.MarketPresenter;
import ui.presenters.TablePresenter;
import app.CardGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayScreen extends TableScreen {
	
	@Override
	public void initialize() {
		Deck deck = new Deck(CardGame.cards().buildDeck(30));
		
		Table table = new Table(deck);
		table.startWith(0);
		
		// Adding players
		table.addPlayer(new Player("Thomas"));
		table.addPlayer(new Player("Marco"));
		table.addPlayer(new Player("Pedram"));
		table.addPlayer(new Player("Melli"));
		
		TablePresenter tablePresenter = new TablePresenter(table);

		MarketPresenter marketPresenter = new MarketPresenter(new Market(table));
		marketPresenter.presentDeck(deck);
		
		_stage.addActor(tablePresenter.getLayer());
		_stage.addActor(marketPresenter.getLayer());
		setupHelperButtons();
		
		marketPresenter.startPeriod();
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
