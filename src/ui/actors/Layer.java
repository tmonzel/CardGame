package ui.actors;

import java.util.ArrayList;
import java.util.List;

import ui.shapes.Line;
import ui.shapes.Shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Layer extends DisplayContainer {
	private ShapeRenderer _shapeRenderer;
	private List<Shape> _shapes;
	
	public Layer() {
		setWidth(Gdx.graphics.getWidth());
		setHeight(Gdx.graphics.getHeight());
		_shapes = new ArrayList<>();
		_shapeRenderer = new ShapeRenderer();
	}
	
	public Line drawLine(Line line) {
		_shapes.add(line);		
		return line;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.end();
		
		for(Shape s : _shapes) {
			if(s instanceof Line) {
				Line line = (Line) s;
				_shapeRenderer.begin(ShapeType.Line);
				Gdx.gl20.glLineWidth(line.getThickness());
				_shapeRenderer.setColor(line.getColor());
				_shapeRenderer.line(line.getStartPoint(), line.getEndPoint());
				_shapeRenderer.end();
			}
		}

		batch.begin();
	}
}
