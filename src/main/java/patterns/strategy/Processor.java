package patterns.strategy;

abstract public class Processor implements IProcessor {

	@Override
	public String name() {
		return getClass().getSimpleName();
	}

	public abstract String process(Object input);
}
