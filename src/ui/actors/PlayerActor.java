package ui.actors;

import materials.Player;
import ui.text.Fonts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayerActor extends DisplayContainer {
	private Label _nameField;
	private Label _wealthField;
	private Label _productionField;
	private CardHand _cardHand;
	private Player _player;
	
	public PlayerActor() {
		setWidth(200);
		setHeight(200);
		
		_nameField = new Label("[Player name]", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		addActor(_nameField);
		
		_wealthField = new Label("0", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_wealthField.setPosition(0, 50);
		addActor(_wealthField);
		
		_productionField = new Label("+0 €", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_productionField.setPosition(50, 50);
		addActor(_productionField);
		
		_cardHand = new CardHand();
		_cardHand.hide();
		addActor(_cardHand);
		
		addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y,
					int pointer, Actor fromActor) {
				_cardHand.setPosition(_cardHand.getWidth()/-2, -220);
				_cardHand.show();
			}
			
			@Override
			public void exit(InputEvent event, float x, float y,
					int pointer, Actor toActor) {
				_cardHand.hide();
			}
		});
	}
	
	public void updatePlayer(Player p) {
		_player = p;
		
		_nameField.setText(p.getName());
		_wealthField.setText(p.getWealthAmount() + "");
		_productionField.setText("+" + p.getProduction() + " €");
		_cardHand.updateCards(p.getCards());
	}
	
	public Player getPlayer() {
		return _player;
	}
}
