package app;
import model.CardModel;
import services.CardService;
import ui.screens.ConnectionScreen;
import ui.screens.StartScreen;
import ui.tween.ActorAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CardGame extends Game {
	GL20 gl;
	Stage stage;
	
	private static CardGame _runningInstance;
	
	private TweenManager _tweenManager;
	private AssetManager _assetManager;
	private CardService _cards;
	
	private boolean assetsLoaded = false;
	
	@Override
	public void create() {
		_runningInstance = this;
		
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		_tweenManager = new TweenManager();
		_assetManager = new AssetManager();
		_cards = new CardService();
		_assetManager.load("assets/background.jpg", Texture.class);
		
		//for(CardModel c : CardModel.values()) {
			//_cards.addCard(c);
			//_assetManager.load(c.getCoverFile(), Texture.class);
			//_assetManager.load(c.getBackFile(), Texture.class);
		//}
	}
	
	@Override
	public void render() {
		
		
		super.render();
		
		if(!assetsLoaded && _assetManager.update()) {
			setScreen(new ConnectionScreen());
			//setScreen(new StartScreen());
			assetsLoaded = true;
		}
		
		_tweenManager.update(Gdx.graphics.getDeltaTime());
	}
	
	public static TweenManager tweens() {
		return _runningInstance._tweenManager;
	}
	
	public static AssetManager assets() {
		return _runningInstance._assetManager;
	}
	
	public static CardService cards() {
		return _runningInstance._cards;
	}
	
	public static void switchScreen(Screen screen) {
		_runningInstance.setScreen(screen);
	}
	
	/**
	 * Main execution method
	 * @param args
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "A Card Game";
		config.width = 1024;
		config.height = 768;
		
		//config.width = 1920;
		//config.height = 1200;
		//config.fullscreen = true;
		new LwjglApplication(new CardGame(), config);
	}

}
