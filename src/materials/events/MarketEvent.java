package materials.events;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MarketEvent extends ActionEvent {
	public static final int ALL_PASSED = 1;
	public static final int SOLD = 2;
	public static final int AUCTION_STARTED = 3;
	
	public MarketEvent(Object source, int id) {
		super(source, id, null);
	}
}
