package ui.actors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import ui.utils.MouseUtil;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DeckActor extends DisplayContainer {
	private Stack<CardActor> _placedDeck;
	private float _deckIndex = 0;
	
	public DeckActor() {
		_placedDeck = new Stack<CardActor>();
	}
	
	public void place(CardActor card) {
		card.setFormat(CardActor.DECK_SCALE);
		
		card.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				MouseUtil.changeCursor(MouseUtil.HAND);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				MouseUtil.resetCursor();
			}
		});

		card.flipToBack();
		card.setPosition(_deckIndex, _deckIndex);
		addActor(card);
		_placedDeck.push(card);
		_deckIndex += .5f;
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
