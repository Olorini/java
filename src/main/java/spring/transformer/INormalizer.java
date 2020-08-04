package spring.transformer;

public interface INormalizer {

	default String transform (String input) {
		return input.trim();
	}
}
