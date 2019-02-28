package patterns.factory;

public class Games {

	public static void playGame(IGameFactory factory){
		IGame s = factory.getGame();
		while (s.move()) {

		}
	}

	public static void main(String[] args) {
		playGame(Checkers.factory() );
		playGame(Chess.factory() );
		/*Checkers move: 0
		Checkers move: 1
		Checkers move: 2
		Chess move: 0
		Chess move: 1
		Chess move: 2
		Chess move: 3*/
	}

}
