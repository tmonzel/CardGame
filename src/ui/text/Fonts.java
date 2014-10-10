package ui.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;

public class Fonts {
	public static BitmapFont createFont(String file, String chars, float worldWidth, float worldHeight, float fontSize) {
		return  TrueTypeFontFactory.createBitmapFont(Gdx.files.internal(file), chars, worldWidth, worldHeight, fontSize, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$€%#@|\\/?-+=()*&.;,{}\"´`'<>";
	public static final BitmapFont HORSERAD_LABEL = createFont("assets/fonts/Horseradish.ttf", CHARS, 12.5f, 7.5f, .15f);
	public static final BitmapFont HORSERAD_NORMAL = createFont("assets/fonts/Horseradish.ttf", CHARS, 12.5f, 7.5f, .20f);
	public static final BitmapFont HORSERAD_BIG = createFont("assets/fonts/Horseradish.ttf", CHARS, 12.5f, 7.5f, .35f);
}
