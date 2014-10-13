package ui.actors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import materials.Card;
import model.CardType;

public class CardHand extends DisplayContainer {
	private Map<CardType, CardGroup> _groups;
	
	public CardHand() {
		_groups = new HashMap<>();
	}
	
	public void updateCards(Set<Card> cards) {
		_groups.clear();
		clear();
		
		float x = 0;
		for(Card c : cards) {
			CardType type = c.getType();
			
			if(!_groups.containsKey(type)) {
				CardGroup newGroup = new CardGroup();
				newGroup.setPosition(x, 0);
				_groups.put(type, newGroup);
				addActor(newGroup);
				x += 150;
			}
			
			_groups.get(type).addCard(new CardActor(c));
		}
		
		setWidth(x);
	}
}
