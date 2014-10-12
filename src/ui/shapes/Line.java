package ui.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Line extends Shape {
	private Vector2 _startPoint;
	private Vector2 _endPoint;
	private int _thickness;
	private Color _color;
	
	public Line(Vector2 startPoint, Vector2 endPoint, int thickness) {
		setStartPoint(startPoint);
		setEndPoint(endPoint);
		setThickness(thickness);
		_color = new Color(0xffffff);
	}
	
	public Line(float startX, float startY, float endX, float endY, int thickness) {
		this(new Vector2(startX, startY), new Vector2(endX, endY), thickness);
	}
	
	public Vector2 getStartPoint() {
		return _startPoint;
	}

	public void setStartPoint(Vector2 startPoint) {
		_startPoint = startPoint;
	}

	public Vector2 getEndPoint() {
		return _endPoint;
	}

	public void setEndPoint(Vector2 endPoint) {
		_endPoint = endPoint;
	}

	public int getThickness() {
		return _thickness;
	}

	public void setThickness(int thickness) {
		_thickness = thickness;
	}
	
	public void setColor(Color c) {
		_color = c;
	}
	
	public Color getColor() {
		return _color;
	}
}
