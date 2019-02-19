package patterns.strategy;

public class Lowcase extends Processor {

	@Override
	public String process(Object input) {
		return ((String) input).toLowerCase();
	}
}
