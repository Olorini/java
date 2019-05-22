package basic.random;

import java.util.Optional;

public class ObjectsExamples {

	public static void main(String[] args) {
		String s = "Tim";
		Optional<String> n = Optional.of(s);
		/*
		if (s != null) System.out.print(s);
		 */
		n.ifPresent(System.out::print);
		/*
		if (s != null) v = s else v = "Anonymous"
		 */
		String v = n.orElse("Anonymous");
	}
}