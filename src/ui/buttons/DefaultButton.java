package ui.buttons;

import ui.utils.MouseUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DefaultButton extends TextButton {

	public DefaultButton(String labelText) {
		super(labelText, new Skin(Gdx.files.internal("assets/uiskin.json")));
		
		addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				MouseUtil.changeCursor(MouseUtil.HAND);
				super.enter(event, x, y, pointer, fromActor);
			}
			
			@Override
			public void exit(InputEvent event, float x, float y, int pointer,
					Actor toActor) {
				MouseUtil.resetCursor();
				super.exit(event, x, y, pointer, toActor);
			}
		});
	}

}
