package basic.streamsExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExamples {

	public void examples() throws IOException {
		int sum = IntStream
				// == ЧАСТЬ 1. Получение
				// Создаем стрим
				// Беск. последовательность чисел, образованная по правилу
				.iterate(1, n -> n+1)
				// == ЧАСТЬ 2. Операции преобразования
				// Фильтруем стрим
				.filter(n -> n%5 == 0 && n%2 != 0)
				// Ограничиваем
				.limit(10)
				// Обрабатываем
				// Каждый элемент возводим в квадрат
				.map(n -> n*n)
				// == ЧАСТЬ 3. Запуск
				// Итоговая функция - сумма
				// Только здесь начинается вычисление
				// Монада
				.sum();
				// == ЧАСТЬ 4. Закрытие стрима, если работал с ресурсами
				//.close();

		/**
		 *  Получение стрима
		 *  */
		// Из коллекции
		Set<String> s = new TreeSet<>();
		Stream<String> stream = s.stream();

		// Из потока данных
		Reader reader = reader = new StringReader("Hello word");
		BufferedReader bufferedReader = new BufferedReader(reader);
		Stream<String> stream1 = bufferedReader.lines();
		stream1.close();

		// Из папки на диске
		Path path = Paths.get("C:\\examples\\red.txt");
		// Один уровень
		Stream<Path> stream2 = Files.list(path);
		// Все уровни
		Stream<Path> stream3 = Files.walk(path);
		stream2.close();
		stream3.close();

		// Из строки
		IntStream stream4 = "dfdfdf".chars();

		// Генерация при помощи саплаера
		DoubleStream stream5 = DoubleStream.generate(Math::random);

		// Итерация
		IntStream stream6 = IntStream.iterate(0, n -> n + 2);

		// Из диапазона чисел
		// Невключительно
		IntStream stream7 = IntStream.range(0, 100);
		// Включительно
		IntStream stream8 = IntStream.rangeClosed(0, 100);

		// Сложение стримов
		IntStream stream9 = IntStream.concat(stream7, stream8);

		// Пустой
		IntStream stream10 = IntStream.empty();

		// Из массива
		double[] arr = new double[] {1, 2, 3};
		DoubleStream stream11 = Arrays.stream(arr);

		// Пречислить явно
		IntStream stream12 = IntStream.of(1, 2, 3);

		/**
		 * Промежуточные операции
		 */
		stream6
				// Фильтрация
				.filter(n -> n>100)
				// Преобразование каждого элемента стрима
				.mapToObj(Integer::toString)
				// Преобразование в стрим
				.flatMapToInt(CharSequence::chars)
				// Убирает из стрима дубликаты
				.distinct()
				// Сортировка
				.sorted()
				// Посмотреть, что происходит
				.peek(System.out::println)
				// Пропустить первые 3 элемента
				.skip(3)
				// Ограничивает оставшиеся элементы
				.limit(2);

		/**
		 * Терминальные операции
		 * Вызывается только один раз
		 */
		// Для каждого элемента выполняет консьюмер
		stream6.forEach(System.out::println);
		// Возвращает первый элемент стрима
		OptionalInt res = stream6.findFirst();
		// Проверить предикат для всех элементов
		boolean b = stream.allMatch(k -> k.length() > 10);
		// Хотя бы один элемент удовлетворяет предикату
		boolean b2 = stream.anyMatch(k -> k.length() > 10);
		// Ни одного
		boolean b3 = stream.noneMatch(k -> k.length() > 10);
		// Минимальный элемент
		Optional<String> res2 = stream.min(Comparator.comparing(String::length, Integer::compare));
		// Количество
		long res3 = stream6.count();

		// Сохранить элементы стрима в список
		List<String> list = stream.collect(Collectors.toList());

		// Свертка элементов стрима
		BigInteger[] b47 = new BigInteger[] {BigInteger.TEN};
		Stream<BigInteger> stream13 = Arrays.stream(b47);
		BigInteger sum47 = stream13.reduce(BigInteger.ZERO, BigInteger::add);
	}

	// Вычисление факториала
	private BigInteger factorial(int n) {
		return IntStream.rangeClosed(0, n)
				.mapToObj(i -> BigInteger.valueOf(i))
				.reduce(BigInteger.ONE, BigInteger::multiply);
	}

	// Определение полиндрома
	private boolean polindrom(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.chars()
				.filter(Character::isLetterOrDigit)
				.map(Character::toLowerCase)
				.forEach(sb::appendCodePoint);
		StringBuilder sb2 = new StringBuilder(sb).reverse();
		return sb2.toString().equals(sb.toString());
	}
}