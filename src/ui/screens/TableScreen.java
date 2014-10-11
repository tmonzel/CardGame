package ui.screens;

import ui.TableStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

abstract class TableScreen extends ScreenAdapter {
	GL20 _gl;
	TableStage _stage;
	
	public TableScreen() {
		_gl = Gdx.gl20;
		_stage = new TableStage();
		
		Gdx.input.setInputProcessor(_stage);
		
		initialize();
	}
	
	public void initialize() {
		
	}

	@Override
	public void render(float delta) {
		_gl.glClearColor(1, 1, 1, 1);
		_gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		_stage.act(delta);
		_stage.draw();
	}
}
