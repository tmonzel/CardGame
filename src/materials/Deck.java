package materials;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class Deck implements Iterable<Card> {
	private Stack<Card> _cards;
	
	public Deck(Stack<Card> cards) {
		_cards = cards;
	}
	
	public void shuffle() {
		Collections.shuffle(_cards);
	}
	
	public Card take() {
		return _cards.pop();
	}

	@Override
	public Iterator<Card> iterator() {
		return _cards.iterator();
	}
}
