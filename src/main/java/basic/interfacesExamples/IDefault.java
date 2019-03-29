package basic.interfacesExamples;

//@FunctionalInterface -- анотация для функционального интерфейса без default
public interface IDefault {

	/*
	 * Определяет поведение по умолчанию для классов,
	 * где метод не реализован
	 */

	default String getMainString() {
		return "";
	}
}
