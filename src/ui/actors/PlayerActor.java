package ui.actors;

import ui.text.Fonts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class PlayerActor extends DisplayContainer {
	private Label _nameField;
	private Label _wealthField;
	private Label _productionField;
	private CardHand _cardHand;
	
	public PlayerActor() {
		_nameField = new Label("[Player name]", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		addActor(_nameField);
		
		_wealthField = new Label("0", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_wealthField.setPosition(0, 50);
		addActor(_wealthField);
		
		_productionField = new Label("+0 €", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_productionField.setPosition(50, 50);
		addActor(_productionField);
		
		_cardHand = new CardHand();
		
		addActor(_cardHand);
	}
	
	public CardHand getCardHand() {
		return _cardHand;
	}
	
	public Label getNameField() {
		return _nameField;
	}
	
	public Label getWealthField() {
		return _wealthField;
	}
	
	public Label getProductionField() {
		return _productionField;
	}
}
