package patterns.command;

public abstract class Event {

	private long eventTime;
	protected final long delayTime;

	public Event(long delayTime) {
		this.delayTime = delayTime;
		setEventTime();
	}

	public void setEventTime() {
		eventTime = System.nanoTime() + delayTime;
	}

	public boolean ready() {
		return (System.nanoTime() >= eventTime);
	}

	public abstract void action();
}
