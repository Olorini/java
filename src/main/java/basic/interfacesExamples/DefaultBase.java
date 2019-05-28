package basic.interfacesExamples;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.*;

public class DefaultBase implements IDefault {

	private int count;

	@Override
	public String getMainString() {
		return "Main";
	}

	@Override
	public void calculateSquare() {
		// Обращение к полям класса в лямбда выражениях
		IntSupplier intSupplier = () -> ++count;
		// do something
	}

	public static long measureTime(Runnable runnable) {
		long startTime = System.currentTimeMillis();
		runnable.run();
		return System.currentTimeMillis() - startTime;
	}

	public static void main(String[] args) {
		/*
		Лямбда выражения или замыкания
		 */
		// Реализуем функ. интерфейс прямо на месте, чтобы передать в метод
		// Создается экземпляр анонимного класса
		long time = measureTime(() -> new BigDecimal(1234567).pow(120));
		System.out.println(time);

		// Вызов при помощи ссылки на метод
		long time2 = measureTime(DefaultBase::getFactorial);
		System.out.println(time2);

		// Трюк с массивом единичной длины. Для изменения пременных метода
		// в лямбда функции
		final Integer[] c = {0};

		// Стандартные функциональные интерфейсы
		Supplier<String> supplier = () -> "hi!";
		IntSupplier intSupplier = () -> ++c[0];

		Consumer<String> consumer1 = s ->  System.out.println(s);
		Consumer<String> consumer2 = System.out::println;
		BiConsumer<String, Integer> biConsumer = (s, i) -> {};
		// Выполнение консьюмеров по очереди
		consumer1.andThen(consumer2);

		Predicate<String> predicate1 = s -> true;
		Predicate<String> predicate2 = s -> s.length() == 0;
		BiPredicate<String, Integer> biPredicate = (s, i) -> true;
		// Получение обратного предиката
		predicate1.negate();
		// Логические операции над предикатами
		predicate1.and(predicate2);

		Function<String, Integer> function1 = s -> s.length();
		Function<Integer, Integer> function3 = i -> i*2;
		Function<String, String> function4 = s -> s.substring(2);
		Function<String, Integer> function2 = String::length;
		UnaryOperator<String> unaryOperator = s -> "Hi!";
		// Композиции функций
		function1.andThen(function3);
		function1.compose(function4);
		DoubleUnaryOperator square = x ->x*x;
		DoubleUnaryOperator sin = Math::sin;
		// sin ^ 2 (x)
		sin.andThen(square);
		// sin (x^2)
		sin.compose(square);

		// Компаратор
		Comparator<Double> absoluteValueComparator
				// abs -- метод, который выполнится перед сравнением
				= Comparator.comparing(Math::abs, Double::compare);

	}

	private static void getFactorial() {
		long fact = 1;
		for (int i = 1; i <= 100; i++) {
			fact *= i;
		}
	}
}
