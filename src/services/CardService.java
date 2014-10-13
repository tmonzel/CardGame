package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import materials.Card;
import materials.Deck;
import model.CardModel;

public class CardService {
	private Set<CardModel> _cards;
	
	public CardService() {
		_cards = new HashSet<>();
	}
	
	public void addCard(CardModel c) {
		_cards.add(c);
	}
	
	public Deck buildDeck(int cardNum) {
		Deck deck = new Deck();
		List<CardModel> cards = new ArrayList<>(_cards);
		
		Collections.shuffle(cards);
		
		for(int i = 0; i < cardNum; i++) {
			deck.addCard(new Card(cards.get(i)));
		}
		
		return deck;
	}
}
