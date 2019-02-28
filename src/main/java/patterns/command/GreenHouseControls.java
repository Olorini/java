package patterns.command;

public class GreenHouseControls extends Controls {

	private boolean light = false;
	private boolean water = false;

	public GreenHouseControls() {
	}

	public class LightOn extends Event {
		public LightOn(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			light = true;
		}
		@Override
		public String toString() {
			return "Свет включен";
		}
	}

	public class LightOff extends Event {
		public LightOff(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			light = false;
		}
		@Override
		public String toString() {
			return "Свет выключен";
		}
	}

	public class WaterOn extends Event {
		public WaterOn(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			water = true;
		}
		@Override
		public String toString() {
			return "Полив включен";
		}
	}

	public class WaterOff extends Event {
		public WaterOff(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			water = false;
		}
		@Override
		public String toString() {
			return "Полив выключен";
		}
	}

	public class Bell extends Event {
		public Bell(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			addEvent(new Bell(delayTime));
		}

		@Override
		public String toString() {
			return "Бам!";
		}
	}

	public class Restart extends Event {
		private Event[] events;
		public Restart(long delayTime, Event[] events) {
			super(delayTime);
			this.events = events;
			for (Event e : events) {
				addEvent(e);
			}
		}
		@Override
		public void action() {
			for (Event e : events) {
				e.setEventTime();
				addEvent(e);
			}
			setEventTime();
			addEvent(this);
		}
		@Override
		public String toString() {
			return "Перезапуск системы";
		}
	}

	public static class Terminate extends Event {
		public Terminate(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}

		@Override
		public String toString() {
			return "Отключение";
		}
	}
}
