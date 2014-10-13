package ui.screens;

import ui.TableStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public abstract class TableScreen extends ScreenAdapter {
	GL20 _gl;
	TableStage _stage;
	InputMultiplexer _multiplexer;
	Array<InputProcessor> _processors;
	
	public TableScreen() {
		_gl = Gdx.gl20;
		_stage = new TableStage(this);
		_multiplexer = new InputMultiplexer();
		_processors = new Array<>();
		addProcessor(_stage);
		
		Gdx.input.setInputProcessor(_multiplexer);
		
		initialize();
	}
	
	public void addProcessor(InputProcessor p) {
		_processors.add(p);
		_multiplexer.setProcessors(_processors);
	}
	
	public void initialize() {
		
	}

	@Override
	public void render(float delta) {
		_gl.glClearColor(1, 1, 1, 1);
		_gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		_stage.act(delta);
		_stage.draw();
	}
}
