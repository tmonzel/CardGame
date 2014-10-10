package ui.events;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AuctionEvent extends ActionEvent {
	public static final int PLACE_BID = 1;
	public static final int PASS = 2;
	
	public AuctionEvent(Object source, int id) {
		super(source, id, null);
	}
}
