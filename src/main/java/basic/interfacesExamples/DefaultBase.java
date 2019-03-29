package basic.interfacesExamples;

import java.math.BigDecimal;

public class DefaultBase implements IDefault {

	@Override
	public String getMainString() {
		return "Main";
	}

	/*
	Лямбда выражения
	 */
	public static long measureTime(Runnable runnable) {
		long startTime = System.currentTimeMillis();
		runnable.run();
		return System.currentTimeMillis() - startTime;
	}

	public static void main(String[] args) {
		//Вызов метода
		//Реализуем функ. интерфейс прямо на месте, чтобы передать в метод
		//Создается экземпляр анонимного класса
		long time = measureTime(() -> new BigDecimal(1234567).pow(120));
		System.out.println(time);

		//Вызов при помощи ссылки на метод
		long time2 = measureTime(DefaultBase::getFactorial);
		System.out.println(time2);
	}

	private static void getFactorial() {
		long fact = 1;
		for (int i = 1; i <= 100; i++) {
			fact *= i;
		}
	}
}
