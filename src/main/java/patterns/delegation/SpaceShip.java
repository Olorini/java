package patterns.delegation;

/**
 * Делегирование
 * Передача управления другому классу
 */

public class SpaceShip {

	private String name;
	private SpaceShipControl control = new SpaceShipControl();

	public SpaceShip(String name) {
		this.name = name;
	}

	public void up() {
		control.up();
	}
	public void down() {
		control.down();
	}
	public void left() {
		control.left();
	}
	public void right() {
		control.right();
	}
}
