package patterns.command;

import java.util.ArrayList;
import java.util.List;

public class Controls {

	private List<Event> eventList = new ArrayList<>();

	public void addEvent(Event e){
		eventList.add(e);
	}

	public void run() {
		while (eventList.size() > 0) {
			for (Event e : new ArrayList<>(eventList)) {
				if (e.ready()) {
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}
		}
	}
}
