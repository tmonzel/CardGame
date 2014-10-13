package ui.presenters;

import java.util.List;

import materials.Card;
import materials.Deck;
import materials.Market;
import ui.actors.AuctionActor;
import ui.actors.MarketLayer;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MarketPresenter {
	private MarketLayer _layer;
	private Market _market;
	private AuctionActor _auctionActor;
	
	public MarketPresenter(Market market) {
		_market = market;
		_layer = new MarketLayer();
		_auctionActor = _layer.getAuctionActor();
		
		applyListeners();
	}
	
	private void applyListeners() {
		_market.addObserver(new Market.Observer() {
			@Override
			public void allPassed(Market m) {
				allPlayerPassed();
			}
			
			@Override
			public void sold(Market m) {
				cardSold();
			}
			
			@Override
			public void auctionStart(Market m) {
				startAuction(m.getAuctionIndex());
			}
		});
		
		_auctionActor.getPlaceButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				placeBid();
			}
		});
		
		_auctionActor.getPassButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pass();
			}
		});
	}
	
	public void placeBid() {
		String bidAmount = _auctionActor.getBidAmountField().getText();
		
		_market.bid(Integer.parseInt(bidAmount));
		_auctionActor.getCurrentBidField().setText(bidAmount + " €");
	}
	
	public void pass() {
		_market.pass();
	}
	
	public void allPlayerPassed() {
		_layer.pushCardUp(_market.getAuctionIndex());
		_market.startNextAuction();
	}
	
	public void cardSold() {
		_layer.removePlacedCard(_market.getAuctionIndex());
		_market.startNextAuction();
	}
	
	public void presentDeck(Deck deck) {
		_layer.placeDeck(deck);
	}
	
	public void startAuction(int index) {
		AuctionActor actor = _layer.showAuction(index);
		
		actor.getBidAmountField().setText(_market.getAuction().getMinimumBid() + "");
		actor.getCurrentBidField().setText("0 €");
	}
	
	public void startPeriod() {
		int auctionCount = _market.getAuctionMaxCount();
		
		List<Card> cards = _layer.placeCards(auctionCount, new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				_market.startAuction(0);
			}
			
		});
		
		_market.startPeriod(cards);
	}
	
	public MarketLayer getLayer() {
		return _layer;
	}
}
