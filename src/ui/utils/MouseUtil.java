package ui.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class MouseUtil {
	public static final int HAND = 1;
	
	private static final Pixmap handCursorMap = new Pixmap(Gdx.files.internal("assets/mouse_pointer4.png"));
	
	
	public static void changeCursor(int type) {
		switch(type) {
			case HAND:
		        int xHotSpot = 13;
		        int yHotSpot = 3;
		        
		        Gdx.input.setCursorImage(handCursorMap, xHotSpot, yHotSpot);
		}
	}
	
	public static void resetCursor() {
		Gdx.input.setCursorImage(null, 0, 0);
	}
}
