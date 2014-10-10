package ui.screens;

import java.util.ArrayList;

import materials.Player;
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

public class PlayScreen extends AbstractScreen {
	
	@Override
	public void initialize() {
		ArrayList<Player> sessions = new ArrayList<Player>();
		sessions.add(new Player("Thomas"));
		sessions.add(new Player("Marco"));
		sessions.add(new Player("Pedram"));
		sessions.add(new Player("Melli"));
		
		PlayerPresenter playerPresenter = new PlayerPresenter(sessions);
		
		DeckActor deck = new DeckActor();
		deck.setPosition(Gdx.graphics.getWidth()-450, Gdx.graphics.getHeight()/2 - 120);
		
		for(Card card : CardGame.buildDeck()) {
			CardActor actor = new CardActor(card);
			actor.setFormat(CardActor.DECK_SCALE);
			deck.place(actor);
		}
		
		MarketPresenter marketPresenter = new MarketPresenter(playerPresenter, deck);


		Image background = new Image(CardGame.assets().get("assets/background.jpg", Texture.class));
		_stage.addActor(background);
		
		setupHelperButtons();
		
		_stage.addActor(deck);
		_stage.addActor(marketPresenter.getLayer());
		_stage.addActor(playerPresenter.getLayer());

		marketPresenter.startAuctions();
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
