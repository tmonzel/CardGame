package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher {
	private HashMap<Integer, ArrayList<ActionListener>> _listeners;
	
	public EventDispatcher() {
		_listeners = new HashMap<Integer, ArrayList<ActionListener>>();
	}
	
	public void addEventListener(int id, ActionListener listener) {
		if(!_listeners.containsKey(id)) {
			_listeners.put(id, new ArrayList<ActionListener>());
		}
		
		_listeners.get(id).add(listener);
	}
	
	public void dispatchEvent(ActionEvent event) {
		ArrayList<ActionListener> listeners = _listeners.get(event.getID());
		for(ActionListener l : listeners) {
			l.actionPerformed(event);
		}
	}
}
