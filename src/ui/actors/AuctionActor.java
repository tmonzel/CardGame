package ui.actors;

import ui.buttons.DefaultButton;
import ui.text.Fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class AuctionActor extends DisplayContainer {
	private Label _currentBidLabel;
	private Label _currentBidField;
	private DefaultButton _placeButton;
	private DefaultButton _passButton;
	private TextField _bidAmountField;
	
	public AuctionActor() {
		_placeButton = new DefaultButton("Place");
		_passButton = new DefaultButton("Pass");
		_currentBidLabel = new Label("Current bid", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		_bidAmountField = new TextField("0", new Skin(Gdx.files.internal("assets/uiskin.json")));
		_currentBidField = new Label("0 €", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE)); 
		
		_placeButton.setPosition(20, -40);
		addActor(_placeButton);
		
		_passButton.setPosition(200-_passButton.getWidth()-20, -40);
		addActor(_passButton);
		
		_currentBidLabel.setPosition(40, 350);
		addActor(_currentBidLabel);
		
		_currentBidField.setPosition(60, 300);
		addActor(_currentBidField);
		
		_bidAmountField.setPosition(20, -100);
		addActor(_bidAmountField);
	}
	
	public DefaultButton getPlaceButton() {
		return _placeButton;
	}
	
	public DefaultButton getPassButton() {
		return _passButton;
	}
	
	public TextField getBidAmountField() {
		return _bidAmountField;
	}
	
	public Label getCurrentBidField() {
		return _currentBidField;
	}
}
