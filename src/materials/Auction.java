package materials;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Auction {
	public enum Status {
		IDLE, STARTED, ALL_PASSED, SOLD;
	}
	
	private HashMap<Player, Integer> _bids;
	private int _highestBid = 0;
	private Status _status;
	private Card _card;
	
	public Auction(Card card) {
		_card = card;
		_bids = new HashMap<Player, Integer>();
		setStatus(Status.IDLE);
	}
	
	public void start(List<Player> players) {
		for(Player p : players) bid(p, null);
		setStatus(Status.STARTED);
	}
	
	public void updateStatus() {
		if(allPassed()) {
			setStatus(Status.ALL_PASSED);
		} else if(_bids.size() == 1 && gethighestBidder() != null) {
			setStatus(Status.SOLD);
		}
	}
	
	private void setStatus(Status status) {
		_status = status;
	}
	
	public Status getStatus() {
		return _status;
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
	
	public void bid(Player p, Integer amount) {
		_bids.put(p, amount);
		
		if(amount != null && amount > _highestBid) {
			_highestBid = amount;
		}
		
		updateStatus();
	}
	
	public void pass(Player p) {
		_bids.remove(p);
		updateStatus();
	}
	
	public int getHighestBid() {
		return _highestBid;
	}
	
	public boolean hasPassed(Player p) {
		return !_bids.containsKey(p);
	}
	
	public int getMinimumBid() {
		return _card.getMinimumBid();
	}
	
	public int getBid(Player p) {
		return _bids.get(p);
	}
	
	public Card getCard() {
		return _card;
	}
}
