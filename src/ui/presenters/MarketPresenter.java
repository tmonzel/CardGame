package ui.presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import materials.Auction;
import materials.Player;
import materials.Table;
import ui.actors.CardActor;
import ui.actors.DeckActor;
import ui.actors.DisplayContainer;
import ui.actors.Layer;
import ui.actors.MarketLayer;
import ui.events.AuctionEvent;
import ui.tween.ActorAccessor;
import app.CardGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Expo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import data.Card;

public class MarketPresenter {
	private MarketLayer _layer;
	private PlayerPresenter _playerPresenter;
	private AuctionPresenter _auctionPresenter;
	private ArrayList<CardActor> _placedCards;
	private ArrayList<CardActor> _passedCards;
	private DeckActor _deckActor;
	private Auction _auction;
	private int _auctionIndex;
	private Table _table;
	
	public MarketPresenter(Table table) {
		_table = table;
		_layer = new MarketLayer();
		_placedCards = new ArrayList<CardActor>();
		_auctionPresenter = new AuctionPresenter(_playerPresenter);
		_auctionPresenter.addEventListener(AuctionEvent.PLACE_BID, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				endTurn();
			}
		});
		
		_auctionPresenter.addEventListener(AuctionEvent.PASS, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				endTurn();
			}
		});
		
		_layer.addActor(_auctionPresenter.getLayer());
	}
	
	public void passAuction() {
		_placedCards.get(_auctionIndex).slideUp(100, 0.5f).start(CardGame.tweens());
		_passedCards.add(getAuctionPlacedCard());
	}
	
	public CardActor getAuctionPlacedCard() {
		return _placedCards.get(_auctionIndex);
	}
	
	public void endTurn() {
		int nextAuctionIndex = _auctionIndex+1;

		if(_auction.allPassed()) {
			passAuction();
			_table.selectStartPlayer();
			startAuction(nextAuctionIndex);
		} else if(_auction.isClosed()) {
			Card wonCard = getAuctionPlacedCard().getCard();
			
			Player highestBidder = _auction.gethighestBidder();
			highestBidder.addCard(wonCard);
			highestBidder.withdrawMoney(_auction.getHighestBid());
			_placedCards.get(_auctionIndex).remove();
			
			_table.selectPlayer(highestBidder);
			startAuction(nextAuctionIndex);

		} else {
			_table.nextPlayer();
		}
		
		
	}
	
	public void presentDeck(Stack<Card> cards) {
		_layer.placeDeck(cards);
	}
	
	public void placeCard(CardActor a) {
		_placedCards.add(a);
		_layer.addActor(a);
		
		a.addListener(new ClickListener() {
			private Vector2 _orig;
			private boolean _scaled = false;
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(_scaled) return false;
				
				CardActor actor = (CardActor) event.getListenerActor();
				_orig = new Vector2(actor.getX(), actor.getY());
				Tween.to(actor, ActorAccessor.SCALE_XY, 0.3f).target(CardActor.VIEW_SCALE).ease(Expo.OUT).start(CardGame.tweens());
				Tween.to(actor, ActorAccessor.POSITION_XY, 0.3f).target(actor.getX()-(actor.getWidth()/2), actor.getY()-(actor.getHeight()/2)).ease(Expo.OUT).start(CardGame.tweens());
				actor.toFront();
				_scaled = true;
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float arg1, float arg2, int arg3, int arg4) {
				CardActor actor = (CardActor) event.getListenerActor();
				Tween.to(actor, ActorAccessor.SCALE_XY, 0.3f).target(CardActor.DECK_SCALE).ease(Expo.OUT).start(CardGame.tweens());
				Tween.to(actor, ActorAccessor.POSITION_XY, 0.3f).target(_orig.x, _orig.y).ease(Expo.OUT).setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						_scaled = false;
					}
				}).start(CardGame.tweens());
			}
		});
		
		a.flipToCover();
	}
	
	public void startAuction(int index) {
		if(index > 4) {
			endPeriod();
			return;
		}
		
		_auction = _auctionPresenter.start(_placedCards.get(index));
		for(Player s : _table.getPlayers()) {
			_auction.bid(s, null);
		}
		_auctionIndex = index;
	}
	
	public void endPeriod() {
		float delay = 0;
		float xPos = _deckActor.getX()-230;
		
		_auctionPresenter.hide();
		
		Timeline tweens = Timeline.createParallel();
		for(CardActor actor : _passedCards) {
			tweens.push(actor.slideTo(xPos, actor.getY(), 0.5f).delay(delay));
			delay += 0.5f;
			xPos -= actor.getWidth() + 10;
		}
		
		tweens.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				upgradePlayers();
				startPeriod();
			}
			
		});
		
		tweens.start(CardGame.tweens());
	}
	
	public void upgradePlayers() {
		for(Player s : _table.getPlayers()) {
			
			// Grundeinkommen
			s.transferBasicIncome();
			
			// Produktion
			s.transferProductionIncome();
		}
	}
	
	public void startPeriod() {
		int takeCount = getAuctionCount();
		float xPos = _deckActor.getX()-230;
		float delay = 0;
		
		if(_passedCards != null) {
			_placedCards = new ArrayList<CardActor>();
			
			for(CardActor ca : _passedCards) {
				_placedCards.add(ca);
				xPos -= ca.getWidth() + 10;
			}
			
			takeCount -= _passedCards.size();
		}
		
		ArrayList<CardActor> cards = _deckActor.takeCards(takeCount);
		_passedCards = new ArrayList<CardActor>();

		Timeline tweens = Timeline.createParallel();
		
		for(CardActor card : cards) {
			tweens.push(card.slideTo(xPos, _deckActor.getY(), 0.5f).delay(delay).setUserData(card).setCallback(new TweenCallback() {

				@Override
				public void onEvent(int arg0, BaseTween<?> arg1) {
					CardActor ca = ((CardActor) arg1.getUserData());
					placeCard(ca);
				}
				
			}));
			
			delay += 0.5f;
			xPos -= card.getWidth() + 10;
		}
		
		tweens.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				startAuction(0);
				_table.selectStartPlayer();
			}
			
		});
		
		tweens.start(CardGame.tweens());
	}
	
	public int getAuctionCount() {
		return _table.countPlayers() + 3;
	}
	
	public DisplayContainer getLayer() {
		return _layer;
	}
}
