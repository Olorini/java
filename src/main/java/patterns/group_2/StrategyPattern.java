package patterns.group_2;

/**
 * Паттерн проектирования "СТРАТЕГИЯ"
 * Инкапсуляция вариантов поведения
 * и выбор варианта посредством делегирования
 */

public class StrategyPattern {

	public static void main(String[] args) {
		Duck liveDuck = new MallardDuck();
		liveDuck.setFlyBehavior(new FlyWithWingsBehavior());
		liveDuck.setQuackBehavior(new SimpleQuackBehavior());
		liveDuck.fly();
		liveDuck.quack();
		liveDuck.swim();

		Duck rubberDuck = new RubberDuck();
		rubberDuck.setFlyBehavior(new FlyNoWingsBehavior());
		rubberDuck.setQuackBehavior(new SqueakQuackBehavior());
		rubberDuck.fly();
		rubberDuck.quack();
		rubberDuck.swim();
	}

	abstract static class Duck {
		private IFlyBehavior flyBehavior;
		private IQuackBehavior quackBehavior;
		void setFlyBehavior(IFlyBehavior flyBehavior) {
			this.flyBehavior = flyBehavior;
		}
		void setQuackBehavior(IQuackBehavior quackBehavior) {
			this.quackBehavior = quackBehavior;
		}
		public abstract void swim();
/*Делегирование полномочий*/
		void quack() {
			quackBehavior.quack();
		}
		void fly(){
			flyBehavior.fly();
		}
	}

	static class MallardDuck extends Duck {
		@Override
		public void swim() {
			System.out.println("I swim");
		}
	}

	static class RubberDuck extends Duck {
		@Override
		public void swim() {
			System.out.println("I only sit on water");
		}
	}

/**
 * Инкапсулируем алгоритмы поведения
 */

	interface IFlyBehavior {
		void fly();
	}

	static class FlyWithWingsBehavior implements IFlyBehavior {
		@Override
		public void fly() {
			System.out.println("I fly with wings");
		}
	}

	static class FlyNoWingsBehavior implements IFlyBehavior {
		@Override
		public void fly() {
			System.out.println("I can't fly");
		}
	}

/*-------------------------------------------*/
	interface IQuackBehavior {
		void quack();
	}

	static class SimpleQuackBehavior implements IQuackBehavior {
		@Override
		public void quack() {
			System.out.println("I quack");
		}
	}

	static class SqueakQuackBehavior implements IQuackBehavior {
		@Override
		public void quack() {
			System.out.println("I squeak");
		}
	}
}
