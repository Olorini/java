package patterns.randomFactory;

import java.util.Random;

/**
 * Возвращает случайные объекты интерфеса "Фигура"
 */

public class RandomFactory {

	private Random random = new Random(47);

	public Shape next() {
		switch (random.nextInt(3)){
			default:
			case 0: return new Circle();
			case 1: return new Triangle();
			case 2: return new Square();
		}
	}
}
