package model.behaviors;

import materials.Card;
import materials.Player;
import ui.actors.CardActor;
import ui.actors.Layer;
import ui.actors.PlayerActor;
import ui.shapes.Line;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CardTargetBehavior extends CardBehavior {
	private Layer _targetLayer;
	private Line _line;
	private boolean _disabled = false;
	
	public void touchDragged(CardActor actor, float x, float y) {
		if(_disabled || _card == null) return;
		
		Vector2 coords = actor.localToStageCoordinates(new Vector2(x, y));
		_line.setEndPoint(new Vector2(coords.x, coords.y));
	}
	
	public void touchDown(CardActor actor, float x, float y) {
		if(_disabled || _card == null) return;
		
		Vector2 coords = actor.localToStageCoordinates(new Vector2(x, y));
		
		_line = new Line(coords.x, coords.y, coords.x, coords.y, 5);
		_line.setColor(new Color(255, 255, 255, 255));

		_targetLayer = new Layer();
		_targetLayer.addLine(_line);
		actor.getStage().addActor(_targetLayer);
	}
	
	public void touchUp(CardActor actor, float x, float y) {
		if(_disabled || _card == null) return;
		
		_targetLayer.removeLine(_line);
		_targetLayer.remove();
		
		Stage stage = actor.getStage();
		
		Vector2 coords = actor.localToStageCoordinates(new Vector2(x, y));
		Actor hitActor = stage.hit(coords.x, coords.y, false);
		
		if(hitActor.getParent() instanceof CardActor) {
			Card targetCard = ((CardActor) hitActor.getParent()).getCard();
			
			if(targetCard.hasOwner()) hitCard(targetCard);
		} else if(hitActor instanceof PlayerActor) {
			hitPlayer(((PlayerActor) hitActor).getPlayer());
		}
	}
	
	public void disable() {
		_disabled = true;
	}
	
	public void hitCard(Card target) {
		
	}
	
	public void hitPlayer(Player player) {
		
	}
}
