package ui.actors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import materials.Card;
import materials.Deck;
import models.CardFormat;
import ui.utils.MouseUtil;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DeckActor extends DisplayContainer {
	private Deck _deck;
	private Stack<CardActor> _placedDeck;
	
	public DeckActor() {
		_placedDeck = new Stack<>();
		setBounds(CardFormat.DECK.getBounds());
	}
	
	public void place(Deck deck) {
		_deck = deck;
		
		float offset = 0;
		
		for(Card c : _deck) {
			CardActor actor = new CardActor(c);
			actor.setFormat(CardFormat.DECK);
			
			actor.addListener(new ClickListener() {
				@Override
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					MouseUtil.changeCursor(MouseUtil.HAND);
				}
				
				@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
					MouseUtil.resetCursor();
				}
			});
	
			actor.flipToBack();
			actor.setPosition(offset, offset);
			addActor(actor);
			_placedDeck.add(actor);
			offset += .5f;
		}
	}
	
	public ArrayList<CardActor> takeCards(int number) {
		ArrayList<CardActor> cards = new ArrayList<CardActor>();
		
		for(int i = 0; i < number; i++) {
			cards.add(0, _placedDeck.pop());
		}
		
		for(CardActor card : cards) {		
			card.moveToStage();
		}
		
		Collections.reverse(cards);
		
		return cards;
	}
}
