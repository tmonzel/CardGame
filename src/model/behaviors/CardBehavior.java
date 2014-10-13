package model.behaviors;

import materials.Card;
import materials.Player;
import ui.actors.CardActor;
import ui.utils.MouseUtil;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CardBehavior {
	protected Card _card;
	protected EventListener _listener;
	
	public EventListener buildListener() {
		return _listener = new ClickListener() {
			
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				
				if(!_card.hasOwner() || !_card.getOwner().selected()) return;
				
				CardBehavior.this.touchDragged((CardActor) event.getListenerActor(), x, y);
				super.touchDragged(event, x, y, pointer);
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(!_card.hasOwner() || !_card.getOwner().selected()) return false;
				
				CardBehavior.this.touchDown((CardActor) event.getListenerActor(), x, y);
				return super.touchDown(event, x, y, pointer, button);
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int arg3, int arg4) {
				if(!_card.hasOwner() || !_card.getOwner().selected()) return;
				
				CardBehavior.this.touchUp((CardActor) event.getListenerActor(), x, y);
				super.touchUp(event, x, y, arg3, arg4);
			}
			
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				MouseUtil.changeCursor(MouseUtil.HAND);
				super.enter(event, x, y, pointer, fromActor);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				MouseUtil.resetCursor();
				super.exit(event, x, y, pointer, toActor);
			}
		};
	}
	
	public void touchDragged(CardActor actor, float x, float y) {

	}
	
	public void touchDown(CardActor actor, float x, float y) {

	}
	
	public void touchUp(CardActor actor, float x, float y) {
		
	}
	
	public void setCard(Card card) {
		_card = card;
	}
	
	public void update() {
		Player owner = _card.getOwner();
		
		owner.increaseProduction(_card.getProductionAmount());
		owner.raiseStatus(_card.getWealth());
	}
	
	public void giveCardTo(Player p) {
		_card.destroy();
		p.addCard(_card);
	}
	
	public void takeCard(Card c) {
		c.destroy();
		_card.getOwner().addCard(c);
	}
}
