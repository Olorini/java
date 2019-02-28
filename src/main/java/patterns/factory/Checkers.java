package patterns.factory;

public class Checkers implements IGame {

	private int moves = 0;
	private static final int MOVES = 3;

	public Checkers() {
	}

	@Override
	public boolean move() {
		System.out.println("Checkers move: " + moves);
		return ++moves != MOVES;
	}

	public static IGameFactory factory() {
		return Checkers::new;
	}
}
