package basic.genericsExamples;

import basic.interfacesExamples.IDefault;

public class ParametrizedMethods {

	/**
	 * Один и тот же алгоритм для разных типов данных
	 * Типом может быть только ссылочный тип
	 * Внутри нельзя создавать экземпляр или массив T
	 * T o = new T(); или T[] arr = new T[5];
	 * Нельзя проверять и приводить к типу T
	 * if (o instanceof T) {...}
	 * T o = (T) a;
	 */

	public <T extends Comparable<T>> T findMin(T[] values) {
		if (values.length == 0) {
			return null;
		}
		T min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min.compareTo(values[i]) < 0) {
				min = values[i];
			}
		}
		return min;
	}

	/**
	 * * extends работает и для классов и для интерфейсов
	 * Можно перечислить несколько интерфейсов
	 * @param templ
	 * @param <T>
	 * @param <K>
	 * @return
	 */
	public <T extends IDefault & Comparable, K extends String> K getName(T templ) {
		return null;
	}
}
