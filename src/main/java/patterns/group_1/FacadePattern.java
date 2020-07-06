package patterns.group_1;

public class FacadePattern {
	/**
	 *  Паттерн проектирования "ФАСАД"
	 *  Обертка -- Упрощает интерфейс
	 */

	public static void main(String[] args) {

	}

	public class HomeTheatreFacade {
		private DVDPlayer dvdPlayer;
		private CDPlayer cdPlayer;
		private Screen screen;

		public HomeTheatreFacade(DVDPlayer dvdPlayer, CDPlayer cdPlayer, Screen screen) {
			this.dvdPlayer = dvdPlayer;
			this.cdPlayer = cdPlayer;
			this.screen = screen;
		}

		public void on() {
			dvdPlayer.on();
			cdPlayer.on();
			cdPlayer.setVolume(11);
			screen.up();
		}

		public void off() {
			dvdPlayer.off();
			cdPlayer.setVolume(0);
			screen.down();
		}

		// и т.д.
	}

	public interface DVDPlayer {
		void on();
		void off();
		void pause();
		void play();
	}

	public interface Screen {
		void up();
		void down();
	}

	public interface CDPlayer {
		void on();
		void setVolume(int level);
	}

}
