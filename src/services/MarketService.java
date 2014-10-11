package services;

import java.util.HashMap;
import java.util.Map;

import materials.Player;
import data.Card;

public class MarketService {
	private Map<Card, Player> _playerCards;
	
	public MarketService() {
		_playerCards = new HashMap<>();
	}
	
	public void setCardOwner(Card c, Player p) {
		_playerCards.put(c, p);
	}
}
