package events;

import materials.Player;
import data.Card;

public class CardBehavior {
	protected Card _card;
	
	public void setCard(Card card) {
		_card = card;
	}
	
	public void update(Player owner) {
		owner.increaseProduction(_card.getProductionAmount());
		owner.raiseStatus(_card.getWealthAmount());
	}

}
