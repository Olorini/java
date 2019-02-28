package patterns.command;

public class GreenhouseRun {
	public static void main(String[] args) {
		GreenHouseControls gc = new GreenHouseControls();
		gc.addEvent(gc.new Bell(900));
		Event[] events = {
				gc.new LightOff(0),
				gc.new LightOn(200),
				gc.new WaterOff(400),
				gc.new WaterOn(600),
		};
		//gc.addEvent(gc.new Restart(1200, events));
		gc.addEvent(gc.new LightOff(0));
		if (args.length == 1) {
			gc.addEvent(new GreenHouseControls.Terminate(new Integer(args[0])));
		}
		gc.run();
	}
}
