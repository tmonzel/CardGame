package materials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import models.CardModel;

public class Table extends Observable {
	private List<Player> _players;
	private Deck _deck;
	private Map<Card, Player> _playerCards;
	private int _selectedPlayerIndex;;
	private int _startPlayerIndex;
	
	public Table(Deck deck) {
		_players = new ArrayList<>();
		_playerCards = new HashMap<>();
		_deck = deck;
	}
	
	public void addPlayer(Player p) {
		_players.add(p);
	}
	
	public Player selectPlayer(int index) {
		_selectedPlayerIndex = index;
		setChanged();
		notifyObservers();
		clearChanged();
		return getSelectedPlayer();
	}
	
	public Player selectPlayer(Player p) {
		return selectPlayer(_players.indexOf(p));
	}
	
	public Player selectStartPlayer() {
		return selectPlayer(_startPlayerIndex);
	}
	
	public void nextPlayer() {		
		selectPlayer((_selectedPlayerIndex+1) % countPlayers());
	}
	
	public void setCardOwner(Card c, Player p) {
		_playerCards.put(c, p);
		p.addCard(c);
	}
	
	public int countPlayers() {
		return _players.size();
	}
	
	public void startWith(int playerIndex) {
		_startPlayerIndex = playerIndex;
	}
	
	public void startWith(Player p) {
		_startPlayerIndex = _players.indexOf(p);
	}
	
	public Player getStartPlayer() {
		return _players.get(_startPlayerIndex);
	}
	
	public Player getSelectedPlayer() {
		return _players.get(_selectedPlayerIndex);
	}
	
	public ArrayList<Player> getNextPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		int numPlayers = countPlayers();

		for(int i = 1; i < countPlayers(); i++) {		
			players.add(_players.get((_selectedPlayerIndex+i) % numPlayers));
		}
		
		return players;
	}
	
	public List<Player> getPlayers() {
		return _players;
	}
	
	public void upgradeAll() {
		for(Player s : _players) {
			
			// Grundeinkommen
			s.transferBasicIncome();
			
			// Produktion
			s.transferProductionIncome();
		}
	}
}
