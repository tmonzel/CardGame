package ui.presenters;

import ui.actors.AuctionActor;
import ui.actors.CardActor;
import ui.actors.DisplayContainer;
import ui.events.AuctionEvent;
import materials.Auction;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import events.ActionDispatcher;

public class AuctionPresenter extends ActionDispatcher {
	private AuctionActor _actor;
	private DisplayContainer _layer;
	private Auction _auction;
	private PlayerPresenter _playerPresenter;
	
	public AuctionPresenter(PlayerPresenter playerPresenter) {
		_playerPresenter = playerPresenter;
		
		_actor = new AuctionActor();
		_actor.getPlaceButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				placePlayerBid(Integer.parseInt(_actor.getBidAmountField().getText()));
			}
		});
		
		_actor.getPassButton().addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				passPlayer();
			}
		});
		
		_layer = new DisplayContainer();
		_actor.setVisible(false);
		_layer.addActor(_actor);
	}
	
	public void placePlayerBid(int amount) {
		_actor.getCurrentBidField().setText(amount + " €");
		_auction.bid(_playerPresenter.getSession(), amount);
		dispatchAction(new AuctionEvent(this, AuctionEvent.PLACE_BID));
	}
	
	public void passPlayer() {
		_auction.pass(_playerPresenter.getSession());
		dispatchAction(new AuctionEvent(this, AuctionEvent.PASS));
	}
	
	public Auction start(CardActor card) {
		_auction = new Auction();
		_actor.setPosition(card.getX(), card.getY());
		_actor.setVisible(true);
		_actor.getBidAmountField().setText(card.getCard().getMinimumBid() + "");
		_actor.getCurrentBidField().setText("0 €");
		return _auction;
	}
	
	public void hide() {
		_actor.hide();
	}
	
	public void show() {
		_actor.show();
	}
	
	public DisplayContainer getLayer() {
		return _layer;
	}
	
	public AuctionActor getActor() {
		return _actor;
	}
}
