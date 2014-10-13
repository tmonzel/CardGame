package ui.actors;

import materials.Card;
import model.CardFormat;
import ui.tween.ActorAccessor;
import app.CardGame;
import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CardActor extends DisplayContainer {
	public static final float DECK_SCALE = .5f;
	public static final float VIEW_SCALE = 1f;
	public static final float HAND_SCALE = .35f;
	
	private Image _coverImage;
	private Image _backImage;
	private Card _card;

	public CardActor(Card card) {
		_card = card;
		_backImage = buildImageWith(_card.getBackFile());
		//_backImage.setTouchable(Touchable.disabled);
		addActor(_backImage);
		
		_coverImage = buildImageWith(_card.getCoverFile());
		//_coverImage.setTouchable(Touchable.disabled);
		addActor(_coverImage);
		
		setFormat(CardFormat.DECK);
		setTouchable(Touchable.enabled);
		
		addListener(_card.getListener());
	}
	
	public void setFormat(CardFormat format) {
		Rectangle rect = format.getBounds();
		setScale(format.getScale());
		setSize(rect.width, rect.height);
	}
	
	private Image buildImageWith(String file) {
		Texture t = CardGame.assets().get(file, Texture.class);
		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new Image(t);
	}
	
	public Card getCard() {
		return _card;
	}
	
	public void flipToCover() {
		_coverImage.toFront();
		_backImage.toBack();
	}
	
	public void flipToBack() {
		_backImage.toFront();
		_coverImage.toBack();
	}
	
	public void flip() {
		swapActor(_coverImage, _backImage);
	}
	
	public void scaleTo(float scale, float duration) {
		Tween.to(this, ActorAccessor.SCALE_XY, duration).target(scale).start(CardGame.tweens());
	}
	
	public Tween slideUp(float amount, float duration) {
		return slideTo(this.getX(), this.getY()+amount, duration);
	}
	
	public Tween slideTo(float x, float y, float duration) {
		return Tween.to(this, ActorAccessor.POSITION_XY, duration).target(x, y);
	}
}
