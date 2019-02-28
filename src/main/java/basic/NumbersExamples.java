package basic;

public class NumbersExamples {

	public static void main(String[] args) {

		//Литералы
		Long longValue = 200L;
		long longPrimitiveValue = 200L;
		float floatValue = 1F;
		double doubleValue = 2.0D;


		//Запись вещественного числа: 1,39 * 10 в -43
		floatValue = 1.39e-43F;

		/* Запись чисел в разных системах отсчета*/
		//Восьмеричная
		int v = 0755;
		//Шестнадцатиричная
		int v2 = 0xFF;
		//Двоичная
		int v3 = 0b101;

		//Указание разрядов числа
		long bigValue = 10_000_000_000L;

		//Максимальные значения для типов
		int maxInt = Integer.MAX_VALUE;

		//Сравнение вещественных чисел
		double dValue = 0;
		for (int i = 0; i < 10; i++) {
			dValue += 0.1;
		}
		System.out.println(dValue);//0.9999999999999999
		if (Math.abs(dValue - doubleValue) < 0.01) {
			System.out.println("OK!");
		}
	}
}