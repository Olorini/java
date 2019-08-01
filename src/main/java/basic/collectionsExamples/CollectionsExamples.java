package basic.collectionsExamples;

import java.util.*;

public class CollectionsExamples {

	Collection<String> c = new ArrayList<>();

	// Дешевый доступ по индексам
	// Один и тот же элемент встречается несколько раз
	List<String> l1 = new ArrayList<>();
	// Дешевое удаление и добавление в начале/конце списка
	List<String> l2 = new LinkedList<>();

	// Очередь
	Queue<String> q = new LinkedList<>();
	// Дэк
	Deque<String> d1 = new ArrayDeque<>();
	Deque<String> d2 = new LinkedList<>();

	// Множество
	// На основе hashCode, если a = b, то hashCode(a) == hashCode(b)
	Set<String> s1 = new HashSet<>();
	// Когда важен порядок элементов, в порядке добавления
	Set<String> s2 = new LinkedHashSet<>();
	// Сравнимые элементы
	// Элементы должны реализовывать Comparable
	// Либо в конструктор передается Comparator
	Set<String> s3 = new TreeSet<>();

	// Ассоциативный массив
	Map<Integer, String> m1 = new HashMap<>();
	Map<Integer, String> m2 = new LinkedHashMap<>();
	Map<Integer, String> m3 = new TreeMap<>();


	private void examples() {
		// Обход коллекции
		Iterator<String> iterator = c.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}

		// Удалять нельзя
		for (String s : c) {
			System.out.println(s);
		}

		// Удалять нельзя
		c.forEach(System.out::println);

		// Удаление дубликатов
		Set<String> set = new LinkedHashSet<>(c);
		List<String> list = new ArrayList<>(set);

		// Обход ассоц. массива
		// Ключи
		Set<Integer> keys = m1.keySet();
		// Значения
		Collection<String> values = m1.values();
		// Пары ключ - значение
		Set<Map.Entry<Integer, String>> pairs = m1.entrySet();
		m1.forEach((k, v) -> System.out.println(v + k));

		// Перетасовывание
		Collections.shuffle(l1);
		// Сортировка
		Collections.sort(l1);
		// Коллекция на чтение
		List<String> ul = Collections.unmodifiableList(l1);

		// В обычный массив
		String[] arr = l1.toArray(new String[l1.size()]);
		// Обратно
		Set<String> set5 = new HashSet<>(Arrays.asList(arr));
		// Или
		Collections.addAll(set, arr);

		c.remove("k");

		// Проверка на пустоту
		// Не следует использовать size
		if (ul.isEmpty()) {
			ul.sort(Comparator.comparingInt(String::length));
		}

	}
}