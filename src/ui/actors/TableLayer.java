package ui.actors;

import ui.text.Fonts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class TableLayer extends Layer {
	private Label _nameLabel;
	private Label _nameField;
	
	private Label _walletLabel;
	private Label _walletField;
	
	private Label _productionLabel;
	private Label _productionField;
	
	private Label _statusLabel;
	private Label _statusField;
	
	private CardHand _cardHand;
	
	public TableLayer() {
		placeComponents();
	}
	
	public void placeComponents() {
		_nameLabel = new Label("Players turn", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_nameLabel.setPosition(60, 100);
		addActor(_nameLabel);
		
		_nameField = new Label(null, new LabelStyle(Fonts.HORSERAD_BIG, Color.WHITE));
		_nameField.setPosition(60, 48);
		_nameField.setColor(Color.valueOf("ffe400"));
		addActor(_nameField);
		
		_walletLabel = new Label("Wallet", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_walletLabel.setPosition(320, 100);
		addActor(_walletLabel);
		
		_walletField = new Label("-", new LabelStyle(Fonts.HORSERAD_NORMAL, Color.WHITE));
		_walletField.setPosition(320, 60);
		addActor(_walletField);
		
		_productionLabel = new Label("Production", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_productionLabel.setPosition(420, 100);
		addActor(_productionLabel);
		
		_productionField = new Label("-", new LabelStyle(Fonts.HORSERAD_NORMAL, Color.WHITE));
		_productionField.setPosition(420, 60);
		addActor(_productionField);
		
		_statusLabel = new Label("Status", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_statusLabel.setPosition(560, 100);
		addActor(_statusLabel);
		
		_statusField = new Label("-", new LabelStyle(Fonts.HORSERAD_NORMAL, Color.WHITE));
		_statusField.setPosition(560, 60);
		addActor(_statusField);
		
		_cardHand = new CardHand();
		_cardHand.setPosition(700, 0);
		addActor(_cardHand);
	}
	
	public CardHand getCardHand() {
		return _cardHand;
	}
	
	public Label getNameField() {
		return _nameField;
	}
	
	public Label getWalletField() {
		return _walletField;
	}
	
	public Label getProductionField() {
		return _productionField;
	}
	
	public Label getStatusField() {
		return _statusField;
	}
}
