package model;

import com.badlogic.gdx.math.Rectangle;

public enum CardFormat {
	DECK(0.5f), HAND(0.35f), VIEW(1);
	
	private static Rectangle _orig = new Rectangle(0, 0, 412, 635);
	private float _scale;
	
	private CardFormat(float scale) {
		_scale = scale;
	}
	
	public float getScale() {
		return _scale;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, _orig.getWidth()*_scale, _orig.getHeight()*_scale);
	}
}
