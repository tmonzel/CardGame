package ui.actors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import materials.Card;
import materials.Deck;
import model.CardFormat;

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
			actor.flipToBack();
			actor.setPosition(offset, offset);
			addActor(actor);
			_placedDeck.push(actor);
			offset += .5f;
		}
	}
	
	public List<CardActor> takeCards(int number) {
		List<CardActor> cards = new ArrayList<>();
		
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
