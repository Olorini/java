package basic.exceptionExamples;


// Создаем проверяемое исключение от Exception или Throwable
// Обязательно обрабатываются или объявляются в заголовке метода
// Наличие проявляется на этапе компиляции

public class MyCheckedException extends Exception {

	public MyCheckedException(String message) {
		super(message);
	}

	public MyCheckedException(String message, Throwable cause) {
		super(message, cause);
	}
}
