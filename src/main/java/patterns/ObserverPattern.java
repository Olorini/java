package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн проектирования "НАБЛЮДАТЕЛЬ -- СЛУШАТЕЛЬ"
 */
public class ObserverPattern {

	public static void main(String[] args) {
		IObserver ob1 = new ConcreteObserveOne();
		IObserver ob2 = new ConcreteObserveTwo();
		IObservable sub = new ConcreteObservSubject();
		sub.registerObserver(ob1);
		sub.registerObserver(ob2);
		sub.notifyObservers();
		sub.removeObserver(ob2);
		sub.notifyObservers();
	}

/*Слушатели*/
	interface IObserver {
		void update();
	}

	static class ConcreteObserveOne implements IObserver {
		@Override
		public void update() {
			System.out.println("Обновляю данные слушателя: 1");
		}
	}

	static class ConcreteObserveTwo implements IObserver {
		@Override
		public void update() {
			System.out.println("Обновляю данные слушателя: 2");
		}
	}

/*Объект наблюдения*/
	interface IObservable {
		void registerObserver(IObserver ob);
		void removeObserver(IObserver ob);
		void notifyObservers();
	}

	static class ConcreteObservSubject implements IObservable {
		List<IObserver> list = new ArrayList<>();
		@Override
		public void registerObserver(IObserver ob) {
			list.add(ob);
		}
		@Override
		public void removeObserver(IObserver ob) {
			list.remove(ob);
		}
		@Override
		public void notifyObservers() {
			for (IObserver ob : list) {
				ob.update();
			}
		}
	}
}
