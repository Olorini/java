package patterns;

import java.util.ArrayList;
import java.util.List;

public class Commander {
	/**
	 * Паттерн проектирования - КОМАНДА
	 * Подходит для организации очереди запросов
	 * и регистрации событий
	 */

	public static void main(String[] args) {
		Remote remote = new Remote();
		remote.setCommand(1, new LightOnCommand(new Light()));
		remote.setCommand(2, new LightOffCommand(new Light()));
		remote.call(1);
		remote.call(2);
		remote.undo();
	}

	public static class Remote {
		ICommand[] commands = new ICommand[7];
		ICommand lastCommand;

		public Remote() {
			for (int i = 0; i < 7; i++){
				commands[i] = new NoCommand();
			}
			this.lastCommand = new NoCommand();
		}

		void setCommand(int slotNumber, ICommand command) {
			commands[slotNumber] = command;
		}

		void call(int slotNumber) {
			commands[slotNumber].execute();
			lastCommand = commands[slotNumber];
		}

		void undo() {
			lastCommand.undo();
		}
	}

	/**
	 * Команды
	 */
	public interface ICommand {
		void execute();
		void undo();
		// void store() -- сохранить состояние
		// void load() -- восстановить состояние

	}

	public static class LightOnCommand implements ICommand {
		Light light;

		public LightOnCommand(Light light) {
			this.light = light;
		}

		@Override
		public void execute() {
			light.on();
		}

		@Override
		public void undo() {
			light.off();
		}
	}

	public static class LightOffCommand implements ICommand {
		Light light;

		public LightOffCommand(Light light) {
			this.light = light;
		}

		@Override
		public void execute() {
			light.off();
		}

		@Override
		public void undo() {
			light.on();
		}
	}

	public class FanOnCommand implements ICommand {
		Fan fan;

		public FanOnCommand(Fan fan) {
			this.fan = fan;
		}

		@Override
		public void execute() {
			fan.onHigh();
		}

		@Override
		public void undo() {
			fan.offMedium();
		}
	}

	public class FanOffCommand implements ICommand {
		Fan fan;

		public FanOffCommand(Fan fan) {
			this.fan = fan;
		}

		@Override
		public void execute() {
			fan.offMedium();
		}

		@Override
		public void undo() {
			fan.onHigh();
		}
	}

	public class MacroCommand implements ICommand{
		List<ICommand> commands = new ArrayList<>();

		public MacroCommand(List<ICommand> commands) {
			this.commands = commands;
		}

		@Override
		public void execute() {
			for (ICommand command : commands) {
				command.execute();
			}
		}

		@Override
		public void undo() {
			for (ICommand command : commands) {
				command.undo();
			}

		}
	}

	public static class NoCommand implements ICommand{
		@Override
		public void execute() { }

		@Override
		public void undo() { }
	}

	/**
	 * Внешнее API
	 */

	public static class Light{
		public void on(){}
		public void off(){}
	}

	public static class Fan{
		public void onHigh(){}
		public void offMedium(){}
	}
}
