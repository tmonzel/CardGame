package ui.actors;

import java.util.ArrayList;
import java.util.List;

import ui.shapes.Line;
import ui.shapes.Shape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;

public class Layer extends DisplayContainer implements InputProcessor {
	private ShapeRenderer _shapeRenderer;
	private List<Shape> _shapes;
	
	public Layer() {
		setWidth(Gdx.graphics.getWidth());
		setHeight(Gdx.graphics.getHeight());
		_shapes = new ArrayList<>();
		_shapeRenderer = new ShapeRenderer();
	}
	
	public Line addLine(Line line) {
		_shapes.add(line);		
		return line;
	}
	
	public void removeLine(Line line) {
		_shapes.remove(line);
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
	

	public void enter() {
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		for (int i = 0; i < size; i++) {
			Actor actor = list.get(i);
			
			if (actor instanceof Layer) {
				Layer layer = (Layer) actor;
				layer.enter();
			}
		}
	}
	

	public void exit() {
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		for (int i = 0; i < size; i++) {
			Actor actor = list.get(i);
			
			if (actor instanceof Layer) {
				Layer layer = (Layer) actor;
				layer.exit();
			}
		}
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		boolean handled = false;
		
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		int index = 0;
		while(index < size && !handled) {
			Actor actor = list.get(index);
			
			if(actor instanceof Layer) {
				Layer layer = (Layer) actor;		
				handled = layer.touchDown(x, y, pointer, button);
			}
			
			index++;
		}
		
		return handled;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		boolean handled = false;
		
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		int index = 0;
		while (index < size && !handled) {
			Actor actor = list.get(index);
			
			if (actor instanceof Layer) {
				Layer layer = (Layer) actor;	
				handled = layer.touchUp(x, y, pointer, button);
			}
			
			index++;
		}
		
		return handled;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		boolean handled = false;
		
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		int index = 0;
		while (index < size && !handled) {
			Actor actor = list.get(index);
			
			if (actor instanceof Layer) {
				Layer layer = (Layer) actor;
				handled = layer.touchDragged(x, y, pointer);
			}
			
			index++;
		}
		
		return handled;
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		boolean handled = false;
		
		SnapshotArray<Actor> list = getChildren();
		
		int size = list.size;
		int index = 0;
		while (index < size && !handled) {
			Actor actor = list.get(index);
			
			if (actor instanceof Layer) {
				Layer layer = (Layer) actor;
				
				handled = layer.mouseMoved(x, y);
			}
			
			index++;
		}
		
		return handled;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	/**
	 * We override the hit test for the layer to ensure it does not take part in
	 * the search for a target. This is to stop the layer from blocking passing
	 * on any hit tests to items on layers below it.
	 */
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		Actor hit = super.hit(x, y, touchable);
		
		if (hit == this) {
			hit = null;
		}
		
		return hit;
	}
}
