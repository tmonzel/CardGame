package app;
import java.util.Collections;
import java.util.Stack;

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

import data.Card;

public class CardGame extends Game {
	GL20 gl;
	Stage stage;
	static TweenManager tweenManager;
	static AssetManager assets;
	static CardGame _runningInstance;
	
	private boolean assetsLoaded = false;
	
	@Override
	public void create() {
		_runningInstance = this;
		
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		tweenManager = new TweenManager();
		assets = new AssetManager();
		assets.load("assets/background.jpg", Texture.class);
		
		for(Card c : Card.values()) {
			assets.load(c.getCoverFile(), Texture.class);
			assets.load(c.getBackFile(), Texture.class);
		}
	}
	
	@Override
	public void render() {
		super.render();
		
		if(!assetsLoaded && assets.update()) {
			setScreen(new StartScreen());
			assetsLoaded = true;
		}
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
	}
	
	public static TweenManager tweens() {
		return tweenManager;
	}
	
	public static AssetManager assets() {
		return assets;
	}
	
	public static void switchScreen(Screen screen) {
		_runningInstance.setScreen(screen);
	}
	
	/**
	 * Builds the card deck
	 * @return Stack<Card> Shuffled deck
	 */
	public static Stack<Card> buildDeck() {
		Stack<Card> d = new Stack<Card>();
		
		// Aktionen
		/*d.push(Card.ANWALT);
		d.push(Card.AUFERSTEHUNG);
		d.push(Card.AUSGELUTSCHT);
		d.push(Card.BERSERKER);
		d.push(Card.BIN_NOCH_NICHT_FERTIG);
		d.push(Card.DEFLATION);
		d.push(Card.DU_BIST_RAUS);
		d.push(Card.HANS_IM_GLUECK);
		d.push(Card.INVENTUR);
		d.push(Card.KUHHANDEL);
		d.push(Card.MANIPULATION);
		d.push(Card.MEHR_BRUTTO_VOM_NETTO);
		d.push(Card.MURPHYS_GESETZ);
		d.push(Card.SABOTAGE);
		d.push(Card.SONDERANGEBOT);
		d.push(Card.SPENDENAKTION);
		d.push(Card.STEUEREINTREIBER);
		d.push(Card.STREIK);
		d.push(Card.WIRTSCHAFTSWUNDER);
		d.push(Card.ZWANGSVERKAUF);*/
		
		// Statussymbole
		//d.push(Card.ARMBANDUHR);
		//d.push(Card.DIADEM);
		d.push(Card.GOLDENES_KLOSETT);
		d.push(Card.GROSSGRUNDBESITZ);
		//d.push(Card.HEXENHAMMER);
		d.push(Card.LOCKE_VON_ELVIS);
		//d.push(Card.MUENZSAMMLUNG);
		//d.push(Card.OSTALGIESAMMLUNG);
		d.push(Card.PLATTENSAMMLUNG);
		//d.push(Card.SIEGELRING);
		//d.push(Card.SPORTWAGEN);
		d.push(Card.VERGOLDETE_FABRIKEN);
		//d.push(Card.WM_BALL);
		
		// Fabriken
		d.push(Card.AZUBI);
		d.push(Card.BAECKER);
		//d.push(Card.BAUUNTERNEHMER);
		d.push(Card.BESTATTER);
		//d.push(Card.DIE_BAHN);
		d.push(Card.GALERIE);
		d.push(Card.KAUFHAUS);
		//d.push(Card.KINO);
		//d.push(Card.MOEBELFABRIK);
		d.push(Card.MOLKEREI);
		//d.push(Card.MUSEUM);
		/*d.push(Card.PLATTENSTUDIO);
		d.push(Card.SAEGEWERK);
		d.push(Card.SCHLACHTER);
		d.push(Card.SUPERMARKT);
		d.push(Card.T_SHIRT_FABRIK);
		d.push(Card.TABAKINDUSTRIE);
		d.push(Card.THEATER);
		d.push(Card.WERFT);*/
		
		// Spezial
		/*d.push(Card.BAULAND_1);
		d.push(Card.BAULAND_2);
		d.push(Card.BAULAND_3);
		d.push(Card.MEGA_IN);
		d.push(Card.SECURITY);
		d.push(Card.SPONSOR);
		d.push(Card.SUPERMANAGER);
		d.push(Card.VOLL_IM_TREND);*/
		
		Collections.shuffle(d);
		
		return d;
	}
	
	/**
	 * Main execution method
	 * @param args
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "A Card Game";
		config.width = 1920;
		config.height = 1200;
		config.fullscreen = true;
		new LwjglApplication(new CardGame(), config);
	}

}
