package materials;

import java.util.HashMap;
import java.util.Map.Entry;

public class Auction {
	private HashMap<Player, Integer> _bids;
	private int _highestBid = 0;
	
	public Auction() {
		_bids = new HashMap<Player, Integer>();
	}
	
	public boolean allPassed() {
		return _bids.isEmpty();
	}

	public Player gethighestBidder() {
		int highestBid = 0;
		Player highestBidder = null;
		
		for(Entry<Player, Integer> entry : _bids.entrySet()) {
			Integer bid = entry.getValue();
			if(bid != null && bid > highestBid) {
				highestBid = bid;
				highestBidder = entry.getKey();
			}
		}
		
		return highestBidder;
	}
	
	public void bid(Player s, Integer amount) {
		_bids.put(s, amount);
		
		if(amount != null && amount > _highestBid) {
			_highestBid = amount;
		}
	}
	
	public int getHighestBid() {
		return _highestBid;
	}
	
	public void pass(Player s) {
		_bids.remove(s);
	}
	
	public boolean hasPassed(Player s) {
		return !_bids.containsKey(s);
	}
	
	public boolean isClosed() {
		return allPassed() || (_bids.size() == 1 && gethighestBidder() != null);
	}
	
	public int getBid(Player s) {
		return _bids.get(s);
	}
}
