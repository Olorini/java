package patterns.strategy;

public class Upcase extends Processor {

	@Override
	public String process(Object input) {
		return ((String) input).toUpperCase();
	}
}
