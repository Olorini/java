package basic.enumExamples;

public class SpiceApply {

	Spice howHot = Spice.HOT;

	public static void main(String[] args) {
		for (Spice s : Spice.values()) {
			System.out.println(s.name() + " " + s.ordinal());
		}
		/*
		NOT 0
		MILD 1
		MEDIUM 2
		HOT 3
		 */
	}
}
