package materials;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import models.CardModel;

public class Market extends Observable {
	public enum EventType {
		ALL_PASSED, SOLD, AUCTION_START;
	}
	
	public static class Observer implements java.util.Observer {

		@Override
		public void update(Observable o, Object arg) {	
			switch((EventType) arg) {
				case AUCTION_START: auctionStart((Market) o); break;
				case ALL_PASSED: allPassed((Market) o); break;
				case SOLD: sold((Market) o); break;
				default: break;
			}
		}
		
		public void auctionStart(Market o) {
			
		}
		
		public void allPassed(Market o) {
			
		}
		
		public void sold(Market o) {
			
		}
	}
	
	private List<List<Auction>> _periods;
	private int _periodIndex = 0;
	private int _auctionIndex = 0;
	private Auction _startedAuction;
	private Table _table;
	
	public Market(Table table) {
		_table = table;
		_periods = new ArrayList<>();
	}
	
	public void bid(Integer amount) {
		_startedAuction.bid(_table.getSelectedPlayer(), amount);
		update();
	}
	
	public void pass() {
		_startedAuction.pass(_table.getSelectedPlayer());
		update();
	}
	
	public void startNextAuction() {
		startAuction(_auctionIndex+1);
	}
	
	private void notify(EventType type) {
		setChanged();
		notifyObservers(type);
		clearChanged();
	}
	
	public void startAuction(int index) {
		_startedAuction = getPeriod().get(index);
		_startedAuction.start(_table.getPlayers());
		_auctionIndex = index;
		_table.selectStartPlayer();
		
		notify(EventType.AUCTION_START);
	}
	
	public void startPeriod(List<Card> cards) {
		ArrayList<Auction> auctions = new ArrayList<>();

		for(Card c : cards) {
			auctions.add(new Auction(c));
		}
		
		_periods.add(auctions);
		_periodIndex = _periods.size()-1;
	}
	
	public void update() {
		switch(_startedAuction.getStatus()) {
			case ALL_PASSED:
				// All passed
				notify(EventType.ALL_PASSED);
			break;
			case SOLD:
				// Sold
				Player winner = _startedAuction.gethighestBidder();
				_table.setCardOwner(_startedAuction.getCard(), winner);
				winner.withdrawMoney(_startedAuction.getHighestBid());
				_table.startWith(winner);
				notify(EventType.SOLD);
			break;
			default:
				// otherwise select the next player
				_table.nextPlayer();
			break;
		}
	}
	
	public int getAuctionMaxCount() {
		return _table.countPlayers() + 2;
	}
	
	public List<Auction> getPeriod() {
		return _periods.get(_periodIndex);
	}
	
	public Auction getAuction() {
		return getPeriod().get(_auctionIndex);
	}
	
	public int getAuctionIndex() {
		return _auctionIndex;
	}
}
