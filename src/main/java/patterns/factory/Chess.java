package patterns.factory;

public class Chess implements IGame {

	private int moves = 0;
	private static final int MOVES = 4;

	@Override
	public boolean move() {
		System.out.println("Chess move: " + moves);
		return ++moves != MOVES;
	}

	public static IGameFactory factory() {
		return Chess::new;
	}
}
