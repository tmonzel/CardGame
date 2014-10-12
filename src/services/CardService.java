package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import materials.Card;
import models.CardModel;

public class CardService {
	private Set<CardModel> _cards;
	
	public CardService() {
		_cards = new HashSet<>();
	}
	
	public void addCard(CardModel c) {
		_cards.add(c);
	}
	
	public Stack<Card> buildDeck(int cardNum) {
		Stack<Card> deck = new Stack<>();
		List<CardModel> cards = new ArrayList<>(_cards);
		
		Collections.shuffle(cards);
		
		for(int i = 0; i < cardNum; i++) {
			deck.push(new Card(cards.get(i)));
		}
		
		return deck;
	}
}
