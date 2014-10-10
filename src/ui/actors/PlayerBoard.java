package ui.actors;

import java.util.HashMap;

import ui.text.Fonts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import data.Card;
import data.CardType;

public class PlayerBoard extends DisplayContainer {
	private Label _nameLabel;
	private HashMap<CardType, CardHandGroup> _cardHandGroups;
	
	public PlayerBoard() {
		_nameLabel = new Label("[Player name]", new LabelStyle(Fonts.HORSERAD_LABEL, Color.WHITE));
		addActor(_nameLabel);
		
		_cardHandGroups = new HashMap<>();
		CardHandGroup factoryGroup = new CardHandGroup();
		factoryGroup.setPosition(0, -100);
		
		CardHandGroup statusGroup = new CardHandGroup();
		statusGroup.setPosition(150, -100);
		
		CardHandGroup eventGroup = new CardHandGroup();
		eventGroup.setPosition(300, -100);
		
		CardHandGroup specialGroup = new CardHandGroup();
		specialGroup.setPosition(450, -100);
		
		_cardHandGroups.put(CardType.PRODUCTION, factoryGroup);
		_cardHandGroups.put(CardType.WEALTH, statusGroup);
		_cardHandGroups.put(CardType.ACTION, eventGroup);
		_cardHandGroups.put(CardType.SERVICE, specialGroup);
		
		addActor(factoryGroup);
		addActor(statusGroup);
		addActor(eventGroup);
		addActor(specialGroup);
	}
	
	public CardHandGroup getHandGroupByType(CardType type) {
		return _cardHandGroups.get(type);
	}
	
	public Label getNameLabel() {
		return _nameLabel;
	}
}
