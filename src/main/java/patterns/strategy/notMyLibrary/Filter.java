package patterns.strategy.notMyLibrary;

public class Filter {

	public String name() {
		return getClass().getSimpleName();
	}

	public Object process(Waveform input) {
		return input;
	}
}
