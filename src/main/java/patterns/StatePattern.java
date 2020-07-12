package patterns;

/**
 * Паттерн проектирования "СОСТОЯНИЕ"
 * Управляет изменением поведения объекта
 * при изменении его внутреннего состояния
 */
public class StatePattern {

	public static void main(String[] args) {
		GumballMachine machine = new GumballMachine(5);
		machine.insertCoin();
		machine.turnCrank();
	}

	static class GumballMachine {
		IState noCoinState;
		IState hasCoinState;
		IState soldState;
		IState state = soldState;
		int count = 0;

		public GumballMachine(int numberBalls) {
			this.noCoinState = new NoCoinState(this);
			this.hasCoinState = new HasCoinState(this);
			this.soldState = new SoldState(this);
			this.count = numberBalls;
			if (numberBalls > 0) {
				this.setState(noCoinState);
			}
		}

		public void turnCrank() {
			state.turnCrank();

		}
		public void insertCoin() {
			state.insertCoin();

		}
		public void ejectCoin() {
			state.insertCoin();
		}


		public IState getState() {
			return state;
		}
		public void setState(IState state) {
			this.state = state;
		}
	}

	interface IState {
		void turnCrank();
		void insertCoin();
		void ejectCoin();
	}

	static class NoCoinState implements IState {
		private GumballMachine machine;

		public NoCoinState(GumballMachine machine) {
			this.machine = machine;
		}

		@Override
		public void turnCrank() {
			System.out.println("No coin.");
		}
		@Override
		public void insertCoin() {
			System.out.println("You inserted a coin.");
			machine.setState(machine.hasCoinState);

		}
		@Override
		public void ejectCoin() {
			System.out.println("You haven't inserted a coin");
		}
	}

	static class HasCoinState implements IState {
		private GumballMachine machine;

		public HasCoinState(GumballMachine machine) {
			this.machine = machine;
		}

		@Override
		public void turnCrank() {
			System.out.println("You turned");
			machine.setState(machine.soldState);
		}
		@Override
		public void insertCoin() {
			System.out.println("You can't insert another coin");
		}
		@Override
		public void ejectCoin() {
			System.out.println("Coin returned");
			machine.setState(machine.noCoinState);
		}
	}

	static class SoldState implements IState {
		private GumballMachine machine;

		public SoldState(GumballMachine machine) {
			this.machine = machine;
		}

		@Override
		public void turnCrank() {
			System.out.println("Turning twice doesn't get you another gumball.");
		}
		@Override
		public void insertCoin() {
			System.out.println("Please wait, we're already giving your a gumball");

		}
		@Override
		public void ejectCoin() {
			System.out.println("Sorry, you already turn the crank.");

		}
	}
}
