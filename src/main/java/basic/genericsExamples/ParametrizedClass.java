package basic.genericsExamples;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Без дженерика класс парметризуется классом Object
 * Внутри нельзя создавать экземпляр или массив T
 * T o = new T(); или T[] arr = new T[5];
 * Нельзя проверять и приводить к типу T
 * if (o instanceof T) {...}
 * T o = (T) a;
 * @param <T>
 */

public class ParametrizedClass<T> {

	private final T value;

	private ParametrizedClass(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	/**
	 * Статические методы параметризуются отдельно
	 */
	public static <T> ParametrizedClass<T> create(T v) {
		return new ParametrizedClass<>(v);
	}

	/**
	 * Наследование в дженериках
	 */
	// Примет consumer написанный для Object
	// Для отдачи
	public void ifPresent(Consumer<? super T> consumer) {
		if (value != null) consumer.accept(value);
	}

	// Для получения
	public T orElseGet(Supplier<? extends T> supplier) {
		return (value != null) ? value : supplier.get();
	}
}
