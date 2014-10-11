package ui.presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import materials.Player;
import materials.Table;
import materials.events.TableEvent;
import ui.actors.PlayerActor;
import ui.actors.PlayerLayer;

import com.badlogic.gdx.Gdx;

import data.Card;

public class PlayerPresenter {
	private PlayerLayer _layer;
	private Table _table;
	private ArrayList<PlayerActor> _actors;
	
	public PlayerPresenter(Table table) {
		_table = table;
		_layer = new PlayerLayer();
		_actors = new ArrayList<PlayerActor>();
		
		applyListeners();
		placeActors();
	}
	
	private void applyListeners() {
		_table.addEventListener(TableEvent.PLAYER_SELECTED, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateActors();
				presentPlayer(_table.getSelectedPlayer());
			}
		});
	}
	
	private void placeActors() {
		int numPlayers = _table.countPlayers();
		
		for(int i = 0; i < numPlayers-1; i++) {
			PlayerActor actor = new PlayerActor();
			float part = Gdx.graphics.getWidth()/(numPlayers-1);
			actor.setPosition((part*i)+part/3, Gdx.graphics.getHeight()-100);
			_actors.add(actor);
			_layer.addActor(actor);

		}
	}
	
	private void updateActor(Player p, PlayerActor a) {
		a.getNameField().setText(p.getName());
		a.getWealthField().setText(p.getWealthAmount() + "");
		a.getProductionField().setText("+" + p.getProduction() + " €");
		a.getCardHand().updateCards(p.getCards());
	}
	
	private void updateActors() {
		int index = 0;
		for(Player p : _table.getNextPlayers()) {
			updateActor(p, _actors.get(index));
			index++;
		}
	}
	
	public void presentPlayer(Player p) {
		setName(p.getName());
		setWallet(p.getWallet());
		setProduction(p.getProduction());
		setWealth(p.getWealthAmount());
		setCardHand(p.getCards());
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
	
	public PlayerLayer getLayer() {
		return _layer;
	}
}
