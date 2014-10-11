package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import data.Card;

public class CardService {
	private Set<Card> _cards;
	
	public CardService() {
		_cards = new HashSet<>();
	}
	
	public void addCard(Card c) {
		_cards.add(c);
	}
	
	public Stack<Card> buildDeck(int cardNum) {
		Stack<Card> deck = new Stack<Card>();
		List<Card> cards = new ArrayList<>(_cards);
		
		Collections.shuffle(cards);
		
		for(int i = 0; i < cardNum; i++) {
			deck.push(cards.get(i));
		}
		
		return deck;
	}
}
