package materials.events;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TableEvent extends ActionEvent {
	public static final int PLAYER_SELECTED = 1;

	public TableEvent(Object source, int id) {
		super(source, id, null);
	}
}
