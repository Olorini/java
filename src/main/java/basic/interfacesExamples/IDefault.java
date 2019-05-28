package basic.interfacesExamples;

public interface IDefault {

	/*
	 * Определяет поведение по умолчанию для классов,
	 * где метод не реализован
	 */

	default String getMainString() {
		return "";
	}

	void calculateSquare();
}
