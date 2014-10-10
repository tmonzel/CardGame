package ui.presenters;

import java.util.ArrayList;

import materials.Player;
import ui.actors.CardActor;
import ui.actors.CardHandGroup;
import ui.actors.PlayerBoard;
import ui.actors.PlayerLayer;
import app.CardGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Gdx;

import data.Card;
import data.CardType;

public class PlayerPresenter {
	private PlayerLayer _layer;
	private Player _player;
	private ArrayList<Player> _players;
	private ArrayList<PlayerBoard> _boards;
	private int _sessionIndex;
	
	public PlayerPresenter(ArrayList<Player> sessions) {
		_layer = new PlayerLayer();
		_boards = new ArrayList<PlayerBoard>();
		_players = sessions;
		
		setupBoards();
	}
	
	private void setupBoards() {
		
		PlayerBoard lastBoard;
		for(int i = 0; i < _players.size()-1; i++) {
			PlayerBoard board = new PlayerBoard();
			board.setPosition(Gdx.graphics.getWidth()/(1+i), Gdx.graphics.getHeight()-200);
			_boards.add(board);
			_layer.addActor(board);
			lastBoard = board;
		}
	}
	
	private ArrayList<Player> getFollowingPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();

		for(int i = _sessionIndex+1; ; i++) {
			if(i > _players.size()-1) {
				i = 0;
			}
			
			if(i == _sessionIndex) break;
			
			players.add(_players.get(i));
		}
		
		return players;
	}
	
	private void updateBoard(Player player, PlayerBoard board) {
		board.getHandGroupByType(CardType.PRODUCTION).clear();
		board.getHandGroupByType(CardType.WEALTH).clear();
		board.getHandGroupByType(CardType.ACTION).clear();
		board.getHandGroupByType(CardType.SERVICE).clear();
		board.getNameLabel().setText(player.getName());
		
		
		for(Card card : player.getCards()) {
			board.getHandGroupByType(card.getType()).addCard(new CardActor(card));
		}
	}
	
	private void updateBoards() {
		
		int index = 0;
		for(Player ps : getFollowingPlayers()) {
			updateBoard(ps, _boards.get(index));
			index++;
		}
	}
	
	public Player selectPlayer(int index) {
		_player = _players.get(index);
		_player.update();
		_sessionIndex = index;
		updateBoards();
		
		_layer.getHandGroupByType(CardType.PRODUCTION).clear();
		_layer.getHandGroupByType(CardType.WEALTH).clear();
		_layer.getHandGroupByType(CardType.ACTION).clear();
		_layer.getHandGroupByType(CardType.SERVICE).clear();
		
		setName(_player.getName());
		setWallet(_player.getWallet());
		setProduction(_player.getProduction());
		setStatus(_player.getWealthAmount());
		
		for(Card c : _player.getCards()) {
			_layer.getHandGroupByType(c.getType()).addCard(new CardActor(c));
		}
		
		
		return _player;
	}
	
	public Player selectPlayer(Player p) {
		return selectPlayer(_players.indexOf(p));
	}
	
	public Player nextPlayer() {
		int nextIndex = _sessionIndex+1;
		
		if(_players.size()-1 < nextIndex) {
			nextIndex = 0;
		}
		
		return selectPlayer(nextIndex);
	}
	
	public void setName(String name) {
		_layer.getNameField().setText(name);
	}
	
	public void setWallet(int value) {
		_layer.getWalletField().setText(value + " €");
	}
	
	public void setProduction(int value) {
		_layer.getProductionField().setText("+" + value + " €");
	}
	
	public void setStatus(int value) {
		_layer.getStatusField().setText(value + "");
	}
	
	public void putCardIntoHand(CardActor c) {
		CardHandGroup handGroup = _layer.getHandGroupByType(c.getType());
		c.moveToStage();

		c.slideTo(handGroup.getX(), handGroup.getY(), 0.3f).setUserData(c).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				CardActor ca = ((CardActor) arg1.getUserData());
				_layer.getHandGroupByType(ca.getType()).addCard(ca);
			}
			
		}).start(CardGame.tweens());
		c.scaleTo(CardActor.HAND_SCALE, 0.3f);
		
		setStatus(_player.getWealthAmount()+c.getStatus());
		setProduction(_player.getProduction()+c.getProduction());
	}
	
	public PlayerLayer getLayer() {
		return _layer;
	}
	
	public Player getSession() {
		return _player;
	}
	
	public ArrayList<Player> getSessions() {
		return _players;
	}
}
