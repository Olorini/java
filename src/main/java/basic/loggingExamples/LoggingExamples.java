package basic.loggingExamples;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExamples {

	private void doIt() throws InterruptedException {

	}

	/**
	 * Получение логгера для класса
	 */
	private static final Logger LOGGER = Logger.getLogger(LoggingExamples.class.getName());

	public void useLogger() throws IOException {
		/**
		 * Настройка логгера
		 * По умолчанию уровень info
		 */
		// В коде или в конфигурационном файле logging.properties
		// Только warning и severe
		LOGGER.setLevel(Level.WARNING);

		String name = "Chloe";
		int level = 2;

		/**
		 * Level -степень серьезности сообщения
		 * Level.SEVERE - ошибки, приводящие к краху
		 * Level.WARNING - предупреждения
		 * Level.CONFIG - конфигурационные параметры
		 * Level.FINE, FINER, FINEST - детальное логирование действий программы
		 * Level.INFO
		 */
		LOGGER.log(Level.INFO, "Some info.");

		/**
		 * Для каждого уровня есть отдельный метод
		 */
		LOGGER.warning("Problem!");

		/**
		 * Всатавка переменных в лог
		 */
		LOGGER.log(Level.INFO, "{1} logon", name);
		LOGGER.log(Level.INFO, "{1} logon with level {2}", new Object[]{name, level});

		/**
		 * Передача в лог исключения
		 */
		try {
			doIt();
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, "do it", e);
		}

		/**
		 * Куда выводить лог
		 * FileHandler -- в файл
		 * SocketHandler -- по сети
		 * ConsoleHandler -- в консоль
		 *
		 * Формат вывода сообщений в лог
		 * SimpleFormatter
		 * XMLFormatter
		 */
		FileHandler fileHandler = new FileHandler();
		LOGGER.addHandler(fileHandler);
	}
}
