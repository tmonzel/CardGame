package ui.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ui.tween.ActorAccessor;
import app.CardGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Expo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import data.Card;

public class MarketLayer extends Layer {
	private DeckActor _deckActor;
	private AuctionActor _auctionActor;
	private List<CardActor> _placedCards;
	
	public MarketLayer() {
		_placedCards = new ArrayList<>();
		
		_deckActor = new DeckActor();
		_deckActor.setCenterPosition((getWidth()/2)+getWidth()/3, getHeight()/2);
		addActor(_deckActor);
		
		_auctionActor = new AuctionActor();
		_auctionActor.hide();
		addActor(_auctionActor);
	}
	
	public void placeDeck(Stack<Card> cards) {
		for(Card card : cards) {
			_deckActor.place(card);
		}
	}
	
	public List<Card> placeCards(int takeCount, TweenCallback callback) {
		List<Card> cards = new ArrayList<>();
		
		float delay = 0;
		float x = _deckActor.getX() - _deckActor.getWidth();

		Timeline tweens = Timeline.createParallel();
		for(CardActor actor : _deckActor.takeCards(takeCount)) {
			cards.add(actor.getCard());
			tweens.push(actor.slideTo(x, _deckActor.getY(), 0.5f).delay(delay).setUserData(actor).setCallback(new TweenCallback() {

				@Override
				public void onEvent(int arg0, BaseTween<?> arg1) {
					CardActor ca = ((CardActor) arg1.getUserData());
					placeCard(ca);
				}
				
			}));
			
			delay += 0.5f;
			x -= actor.getWidth() + 10;
		}
		
		tweens.setCallback(callback).start(CardGame.tweens());
		
		return cards;
	}
	
	public void placeCard(CardActor a) {
		_placedCards.add(a);
		addActor(a);
		
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
	
	public void pushCardUp(int index) {
		_placedCards.get(index).slideUp(100, 0.5f).start(CardGame.tweens());
	}
	
	public AuctionActor showAuction(int index) {
		CardActor card = getPlacedCard(index);
		
		_auctionActor.setPosition(card.getX(), card.getY());
		_auctionActor.show();
		return _auctionActor;
	}
	
	public AuctionActor getAuctionActor() {
		return _auctionActor;
	}
	
	public CardActor getPlacedCard(int index) {
		return _placedCards.get(index);
	}
	
	public void removePlacedCard(int index) {
		_placedCards.get(index).remove();
		_placedCards.remove(index);
	}
}
