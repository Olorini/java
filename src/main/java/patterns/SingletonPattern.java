package patterns;

/**
 * Одиночка
 * гарантирует, что класс имеет только один экземпляр
 * предоставляет глобальную точку доступа к этому экземпляру
 */
public class SingletonPattern {
	// Потокобезпасный с лучшей производительностью
	private volatile static SingletonPattern instance;

	private SingletonPattern() {}

	public static SingletonPattern getInstance() {
		if (instance == null) {
			synchronized (SingletonPattern.class) {
				if (instance == null) {
					instance = new SingletonPattern();
				}
			}
		}
		return instance;
	}
}

class SingletonOne {
	// Потокобезпасный
	private static SingletonOne instance = new SingletonOne();

	private SingletonOne() {}

	public static SingletonOne getInstance() {
		return instance;
	}
}
