package basic;

public class StringAndCharExamples {

	private void example() {
		//Проверка: является ли символ буквой
		boolean isLetter = Character.isLetter('a');

		//Конвертация строк в массив символов и обратно
		char[] charArr = {'a', 'd', 'f'};
		String s = new String(charArr);
		char[] chars = s.toCharArray();

		//Получить символ по индексу
		char symbol = s.charAt(2);

		//Чем заканчивается строка
		s.endsWith("df");

		//С чего начинается строка
		boolean b1 = s.startsWith("ad");

		//Содержит ли подстроку
		boolean b2 = s.contains("d");

		//Отредактированную строку сохраняют в новую переменную
		//Получение подстроки
		String s1 = s.substring(0, 1);

		//Поиск и замена
		String s2 = s.replace("ad", "qt");

		//Допускает изменение содержимого, для постоянно меняющихся строк
		StringBuilder stringBuilder = new StringBuilder();

		//Сравнение без учета регистра
		boolean b3 = s1.equalsIgnoreCase(s2);

		// Форматирование строк
		String name = "Ann";
		String.format("Hello, %s", name);
	}
}
