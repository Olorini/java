package patterns.strategy;

import patterns.strategy.notMyLibrary.Filter;
import patterns.strategy.notMyLibrary.Waveform;

public class FilterAdapter implements IProcessor {

	/**
	 * Паттерн "Адптор": адаптирование сторонней библиотеки
	 * через интерфейс и класс
	 * Применяем делегирование
	 */

	private Filter filter;

	public FilterAdapter(Filter filter) {
		this.filter = filter;
	}

	@Override
	public String name() {
		return filter.name();
	}

	@Override
	public Object process(Object input) {
		return filter.process( (Waveform) input);
	}
}
