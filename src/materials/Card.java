package materials;

import model.CardModel;
import model.CardType;
import model.behaviors.CardBehavior;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public class Card {
	private CardModel _model;
	private Player _owner;
	private Table _table;
	private EventListener _listener;
	private int _wealth;
	private int _minimumBid;
	private int _productionAmount;
	private CardBehavior _behavior;
	
	public Card(CardModel model) {
		_model = model;
		
		_behavior = model.getBehavior();
		_behavior.setCard(this);
		_listener = _behavior.buildListener();
		
		_minimumBid = _model.getMinimumBid();
		_wealth = _model.getWealthAmount();
		_productionAmount = _model.getProductionAmount();
	}
	
	public void setOwner(Player owner) {
		_owner = owner;
	}
	
	public void setTable(Table table) {
		_table = table;
	}
	
	public void update() {
		_behavior.update();
	}
	
	public int raiseProduction(int amount) {
		_productionAmount += amount;
		return _productionAmount;
	}
	
	public int raiseWealth(int amount) {
		_wealth += amount;
		return _wealth;
	}
	
	public void setWealth(int amount) {
		_wealth = amount;
	}
	
	public void destroy() {
		_owner.removeCard(this);
	}
	
	public boolean hasOwner() {
		return _owner != null;
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
		return _minimumBid;
	}
	
	public int getWealth() {
		return _wealth;
	}
	
	public int getProductionAmount() {
		return _productionAmount;
	}
	
	public Table getTable() {
		return _table;
	}
	
	public Player getOwner() {
		return _owner;
	}
	
	public EventListener getListener() {
		return _listener;
	}
}
