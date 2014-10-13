package ui.presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import materials.Card;
import materials.Player;
import materials.Table;
import ui.actors.PlayerActor;
import ui.actors.TableLayer;

import com.badlogic.gdx.Gdx;

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
				updateChairs();
			}
		});
		
		for(Player p : _table.getPlayers()) {
			p.addObserver(new Observer() {
				@Override
				public void update(Observable o, Object arg) {
					updatePlayer((Player) o);
				}
			});
		}
	}
	
	private void placePlayers() {
		int numPlayers = _table.countPlayers();
		
		for(int i = 0; i < numPlayers-1; i++) {
			PlayerActor actor = new PlayerActor();
			float part = Gdx.graphics.getWidth()/(numPlayers-1);
			actor.setPosition((part*i)+part/3, Gdx.graphics.getHeight()-100);
			
			
			_playerActors.add(actor);
			_layer.addActor(actor);

		}
	}
	
	public void updatePlayer(Player p) {
		if(_table.getSelectedPlayer().equals(p)) {
			setName(p.getName());
			setWallet(p.getWallet());
			setProduction(p.getProduction());
			setWealth(p.getWealthAmount());
			setCardHand(p.getCards());
			
			return;
		} 
		
		PlayerActor a = _playerActors.get(p.getChairIndex());
		a.updatePlayer(p);
	}
	
	public void updateChairs() {
		int index = 0;
		for(Player p : _table.getNextPlayers()) {
			p.setChairIndex(index);
			index++;
		}
		
		_table.getSelectedPlayer().setChairIndex(null);
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
