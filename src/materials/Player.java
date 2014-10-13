package materials;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Player extends Observable {
	private String _name;
	private Set<Card> _cards;
	private int _money = 60;
	private int _basicIncome = 30;
	private int _wealthAmount;
	private int _productionAmount;
	private int _factoryMax = 3;
	private Integer _chairIndex;
	private boolean _starter = false;
	private boolean _selected = false;
	
	public Player(String name) {
		_cards = new HashSet<>();
		_name = name;
	}
	
	public void select() {
		_selected = true;
	}
	
	public boolean selected() {
		return _selected;
	}
	
	public void deselect() {
		_selected = false;
	}
	
	public void setChairIndex(Integer index) {
		_chairIndex = index;
		update();
	}
	
	public Integer getChairIndex() {
		return _chairIndex;
	}
	
	public String getName() {
		return _name;
	}
	
	public void addCard(Card c) {
		_cards.add(c);
		c.setOwner(this);
		update();
	}
	
	public void removeCard(Card c) {
		_cards.remove(c);
		update();
	}
	
	public void update() {
		_wealthAmount = 0;
		_productionAmount = 0;
		
		for(Card c : _cards) {
			c.update();
		}
		
		setChanged();
		notifyObservers();
		clearChanged();
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
	
	public int raiseFactoryMax(int amount) {
		_factoryMax += amount;
		return _factoryMax;
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
