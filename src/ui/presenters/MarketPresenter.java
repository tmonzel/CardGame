package ui.presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

import materials.Market;
import materials.events.MarketEvent;
import ui.actors.AuctionActor;
import ui.actors.MarketLayer;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import data.Card;

public class MarketPresenter {
	private MarketLayer _layer;
	private Market _market;
	private AuctionActor _auctionActor;
	
	public MarketPresenter(Market market) {
		_market = market;
		_layer = new MarketLayer();
		_auctionActor = _layer.getAuctionActor();
		
		_market.addEventListener(MarketEvent.AUCTION_STARTED, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startAuction(_market.getAuctionIndex());
			}
		});
		
		_market.addEventListener(MarketEvent.ALL_PASSED, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				allPlayerPassed();
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
	
	public void presentDeck(Stack<Card> cards) {
		_layer.placeDeck(cards);
	}
	
	public void startAuction(int index) {
		_layer.showAuction(index);
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
