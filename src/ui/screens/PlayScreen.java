package ui.screens;

import java.util.ArrayList;

import materials.Player;
import materials.Table;
import ui.actors.CardActor;
import ui.actors.DeckActor;
import ui.buttons.DefaultButton;
import ui.presenters.MarketPresenter;
import ui.presenters.PlayerPresenter;
import app.CardGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import data.Card;

public class PlayScreen extends TableScreen {
	
	@Override
	public void initialize() {
		Table table = new Table();
		table.startWith(0);
		
		// Adding players
		table.addPlayer(new Player("Thomas"));
		table.addPlayer(new Player("Marco"));
		table.addPlayer(new Player("Pedram"));
		table.addPlayer(new Player("Melli"));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Thomas"));
		players.add(new Player("Marco"));
		players.add(new Player("Pedram"));
		players.add(new Player("Melli"));
		
		PlayerPresenter playerPresenter = new PlayerPresenter(table);
		
		DeckActor deck = new DeckActor();
		deck.setPosition(Gdx.graphics.getWidth()-450, Gdx.graphics.getHeight()/2 - 120);
		
		for(Card card : CardGame.buildDeck()) {
			deck.place(new CardActor(card));
		}
		
		MarketPresenter marketPresenter = new MarketPresenter(table, deck);
		
		setupHelperButtons();
		
		_stage.addActor(deck);
		_stage.addActor(marketPresenter.getLayer());
		_stage.addActor(playerPresenter.getLayer());

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
