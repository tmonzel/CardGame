package materials;

import java.util.HashSet;
import java.util.Set;

import models.CardModel;

public class Player {
	private String _name;
	private Set<Card> _cards;
	private int _money = 60;
	private int _basicIncome = 30;
	private int _wealthAmount;
	private int _productionAmount;
	private boolean _starter = false;
	
	public Player(String name) {
		_cards = new HashSet<>();
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public void addCard(Card c) {
		_cards.add(c);
		update();
	}
	
	public void removeCard(CardModel c) {
		_cards.remove(c);
		update();
	}
	
	public void update() {
		_wealthAmount = 0;
		_productionAmount = 0;
		
		for(Card c : _cards) {
			c.getBehavior().update(this);
		}
	}
	
	public void transferBasicIncome() {
		depositMoney(_basicIncome);
	}
	
	public void transferProductionIncome() {
		depositMoney(_productionAmount);
	}
	
	public Set<Card> getCards() {
		return _cards;
	}
	
	public void setWallet(int value) {
		_money = value;
	}
	
	public int getWallet() {
		return _money;
	}
	
	public void setStatus(int value) {
		_wealthAmount = value;
	}
	
	public int getWealthAmount() {
		return _wealthAmount;
	}
	
	public void setProduction(int value) {
		_productionAmount = value;
	}
	
	public int getProduction() {
		return _productionAmount;
	}
	
	public int increaseBasicIncome(int amount) {
		_basicIncome += amount;
		return _basicIncome;
	}
	
	public int increaseProduction(int amount) {
		_productionAmount += amount;
		return _productionAmount;
	}
	
	public int raiseStatus(int amount) {
		_wealthAmount += amount;
		return _wealthAmount;
	}
	
	public void withdrawMoney(int amount) {
		_money -= amount;
	}
	
	public void depositMoney(int amount) {
		_money += amount;
	}
	
	public boolean isStarter() {
		return _starter;
	}
}
