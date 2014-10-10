package ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

abstract class AbstractScreen extends ScreenAdapter {
	GL20 _gl;
	Stage _stage;
	
	public AbstractScreen() {
		_gl = Gdx.gl20;
		_stage = new Stage(new ScreenViewport());
		
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
		//asdasd
		_stage.draw();
	}
}
