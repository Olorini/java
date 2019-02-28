package patterns.strategy;

public interface IProcessor {

	String name();

	Object process(Object input);
}
