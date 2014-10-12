package models;

import ui.actors.CardActor;
import materials.Player;

public class CardBehavior {
	protected CardModel _card;
	protected CardActor _actor;
	
	public void setCard(CardModel c) {
		_card = c;
	}
	
	public void setActor(CardActor a) {
		_actor = a;
	}
	
	public void update(Player owner) {
		owner.increaseProduction(_card.getProductionAmount());
		owner.raiseStatus(_card.getWealthAmount());
	}

}
