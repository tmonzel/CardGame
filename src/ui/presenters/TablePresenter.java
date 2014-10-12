package ui.presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import materials.Card;
import materials.Player;
import materials.Table;
import models.CardModel;
import ui.actors.PlayerActor;
import ui.actors.TableLayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TablePresenter {
	private TableLayer _layer;
	private Table _table;
	private ArrayList<PlayerActor> _playerActors;
	
	public TablePresenter(Table table) {
		_table = table;
		_layer = new TableLayer();
		_playerActors = new ArrayList<PlayerActor>();
		
		applyObservers();
		placePlayers();
	}
	
	private void applyObservers() {
		_table.addObserver(new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				present();
			}
		});
	}
	
	private void placePlayers() {
		int numPlayers = _table.countPlayers();
		
		for(int i = 0; i < numPlayers-1; i++) {
			PlayerActor actor = new PlayerActor();
			float part = Gdx.graphics.getWidth()/(numPlayers-1);
			actor.setPosition((part*i)+part/3, Gdx.graphics.getHeight()-100);
			actor.addListener(new ClickListener() {
				@Override
				public void enter(InputEvent event, float x, float y,
						int pointer, Actor fromActor) {
					PlayerActor pa = (PlayerActor) event.getListenerActor();
					pa.getCardHand().setPosition(pa.getCardHand().getWidth()/-2, -220);
					pa.getCardHand().show();
				}
				
				@Override
				public void exit(InputEvent event, float x, float y,
						int pointer, Actor toActor) {
					PlayerActor pa = (PlayerActor) event.getListenerActor();
					pa.getCardHand().hide();
				}
			});
			
			
			_playerActors.add(actor);
			_layer.addActor(actor);

		}
	}
	
	public void present() {
		int index = 0;
		for(Player p : _table.getNextPlayers()) {
			presentPlayer(p, _playerActors.get(index));
			index++;
		}
		
		Player p = _table.getSelectedPlayer();
		
		setName(p.getName());
		setWallet(p.getWallet());
		setProduction(p.getProduction());
		setWealth(p.getWealthAmount());
		setCardHand(p.getCards());
	}
	
	private void presentPlayer(Player p, PlayerActor a) {
		a.getNameField().setText(p.getName());
		a.getWealthField().setText(p.getWealthAmount() + "");
		a.getProductionField().setText("+" + p.getProduction() + " €");
		a.getCardHand().updateCards(p.getCards());
	}
	
	private void setCardHand(Set<Card> cards) {
		_layer.getCardHand().updateCards(cards);
	}
	
	private void setName(String name) {
		_layer.getNameField().setText(name);
	}
	
	private void setWallet(int amount) {
		_layer.getWalletField().setText(amount + " €");
	}
	
	private void setProduction(int amount) {
		_layer.getProductionField().setText("+" + amount + " €");
	}
	
	private void setWealth(int amount) {
		_layer.getStatusField().setText(amount + "");
	}
	
	public TableLayer getLayer() {
		return _layer;
	}
}
