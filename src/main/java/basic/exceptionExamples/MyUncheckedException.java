package basic.exceptionExamples;


// Создаем непроверяемое исключение от RuntimeException или Error
// Не обязательно обрабатывать и обозначають в заголовке метода, но возможно
// Заметны на этапе выполнения программы
public class MyUncheckedException extends RuntimeException {

	public MyUncheckedException(String message) {
		super(message);
	}

	public MyUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}
}
