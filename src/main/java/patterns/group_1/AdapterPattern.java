package patterns.group_1;

public class AdapterPattern {
	/**
	 *  Паттерн проектирования "АДАПТЕР"
	 *  Обертка -- преобразует интерфейс к другому интерфейсу
	 */

	public static void main(String[] args) {

	}

	private class TurkeyAdapter implements Duck {
		ITurkey turkey;

		public TurkeyAdapter(ITurkey turkey) {
			this.turkey = turkey;
		}

		@Override
		public void quack() {
			turkey.gobble();
		}

		@Override
		public void fly() {
			for (int i = 0; i < 5; i++) {
				turkey.fly();
			}
		}
	}

	private interface ITurkey {
		void gobble();
		void fly();
	}

	private class WildTurkey implements ITurkey {
		@Override
		public void gobble() {
			System.out.println("Gobble, gobble!");
		}

		@Override
		public void fly() {
			System.out.println("I'm flying short distance");
		}
	}

	private interface Duck {
		void quack();
		void fly();
	}

}
