package ui.actors;

import models.CardFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CardGroup extends DisplayContainer {
	
	public CardGroup() {
		
	}
	
	public void addCard(CardActor card) {
		if(hasChildren()) {
			for(Actor a : getChildren()) {
				a.setPosition(a.getX(), a.getY()+30);
			}
		}
		
		card.clearListeners();
		card.addListener(new ClickListener() {
			private CardActor _viewActor;
			private CardActor _handActor;
			
			private void updatePosition(CardActor ca, float x, float y) {
				Vector2 globals = ca.localToStageCoordinates(new Vector2(x, y));
				
				if((globals.y+_viewActor.getHeight()/2 + 250) > Gdx.graphics.getHeight()) {
					globals.y = Gdx.graphics.getHeight() - _viewActor.getHeight()/2 - 250;
				}
				
				Rectangle format = CardFormat.VIEW.getBounds();
				
				_viewActor.setPosition(globals.x - format.getWidth()/2, globals.y - format.getHeight()/2 + 250);
			}
			
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				CardActor ca = (CardActor) event.getListenerActor();
				updatePosition(ca, x, y);
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				CardActor ca = (CardActor) event.getListenerActor();
				Stage stage = ca.getStage();
				_handActor = ca;
				_handActor.hide();
				_viewActor = new CardActor(ca.getCard());
				//Tween.to(_viewActor, ActorAccessor.SCALE_XY, 0.3f).target(CardActor.VIEW_SCALE).ease(Expo.OUT).start(CardGame.tweens());
				
				_viewActor.setFormat(CardFormat.VIEW);
				stage.addActor(_viewActor);
				updatePosition(ca, x, y);

				return true;
			}
			
			@Override
			public void touchUp(InputEvent arg0, float arg1, float arg2, int arg3, int arg4) {
				_handActor.show();
				_viewActor.remove();
			}
	
		});
		
		card.setFormat(CardFormat.HAND);
		card.setPosition(0, 0);
		addActor(card);
	}
}
