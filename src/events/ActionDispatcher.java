package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ActionDispatcher {
	private HashMap<Integer, ArrayList<ActionListener>> _listeners;
	
	public ActionDispatcher() {
		_listeners = new HashMap<Integer, ArrayList<ActionListener>>();
		
	}
	
	public void addActionListener(int id, ActionListener listener) {
		if(!_listeners.containsKey(id)) {
			_listeners.put(id, new ArrayList<ActionListener>());
		}
		
		_listeners.get(id).add(listener);
	}
	
	public void dispatchAction(ActionEvent event) {
		ArrayList<ActionListener> listeners = _listeners.get(event.getID());
		for(ActionListener l : listeners) {
			l.actionPerformed(event);
		}
	}
}
