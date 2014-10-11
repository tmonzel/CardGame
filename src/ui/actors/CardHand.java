package ui.actors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import data.Card;
import data.CardType;

public class CardHand extends DisplayContainer {
	private Map<CardType, CardGroup> _groups;
	
	public CardHand() {
		_groups = new HashMap<>();
		placeGroups();
	}

	public void placeGroups() {
		float x = 0;
		for(CardType type : CardType.values()) {
			CardGroup group = new CardGroup();
			group.setPosition(x, -100);
			_groups.put(type, group);
			addActor(group);
			
			x += 150;
		}
	}
	
	public void updateCards(Set<Card> cards) {
		clear();
		
		for(Card c : cards) {
			getGroupByType(c.getType()).addCard(new CardActor(c));
		}
	}
	
	public void clear() {
		for(CardGroup g : _groups.values()) g.clear();
	}
	
	public CardGroup getGroupByType(CardType type) {
		return _groups.get(type);
	}
}
