package ui.actors;

import java.util.Stack;

import data.Card;

public class MarketLayer extends Layer {
	private DeckActor _deckActor;
	
	public MarketLayer() {
		_deckActor = new DeckActor();
		_deckActor.setCenterPosition((getWidth()/2)+getWidth()/3, getHeight()/2);
		addActor(_deckActor);
	}
	
	public void placeDeck(Stack<Card> cards) {
		for(Card card : cards) {
			_deckActor.place(card);
		}
	}
}
