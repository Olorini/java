package patterns;

/**
 * Одиночка
 * гарантирует, что класс имеет только один экземпляр
 * предоставляет глобальную точку доступа к этому экземпляру
 */
public class Singleton {
	// Потокобезпасный с лучшей производительностью
	private volatile static Singleton instance;

	private Singleton() {}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
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
