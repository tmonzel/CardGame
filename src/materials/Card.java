package materials;

import models.CardBehavior;
import models.CardModel;
import models.CardType;

public class Card {
	private CardModel _model;
	private Player _owner;
	
	public Card(CardModel model) {
		_model = model;
	}
	
	public void setOwner(Player owner) {
		_owner = owner;
	}
	
	public String getCoverFile() {
		return _model.getCoverFile();
	}
	
	public String getBackFile() {
		return _model.getBackFile();
	}
	
	public CardType getType() {
		return _model.getType();
	}
	
	public int getMinimumBid() {
		return _model.getMinimumBid();
	}
	
	public Player getOwner() {
		return _owner;
	}
	
	public CardBehavior getBehavior() {
		return _model.getBehavior();
	}
}
