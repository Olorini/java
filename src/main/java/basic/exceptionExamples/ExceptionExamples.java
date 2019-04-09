package basic.exceptionExamples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExceptionExamples implements IExceptionExamples {

	//Непроверяемое исключение
	public double calculate(String expr) {
		throw new MyUncheckedException("Unsupported operation found");
	}

	//Проверяемое исключение
	public double resolve(String expr) throws MyCheckedException {
		throw new MyCheckedException("Unsupported type found");
	}

	public static void main(String[] args) throws MyCheckedException {
		ExceptionExamples exceptionExamples = new ExceptionExamples();
		/**
		 * Вызов метода с непроверяемым исключением
		 */
		//exceptionExamples.calculate("no");
		/*
			Exception in thread "main" basic.exceptionExamples.MyUncheckedException:
										Unsupported operation found
			at basic.exceptionExamples.ExceptionExamples.calculate(ExceptionExamples.java:7)
			at basic.exceptionExamples.ExceptionExamples.main(ExceptionExamples.java:17)
		 */

		/**
		 * Вызов метода с проверяемым исключением
		 */
		try {
			exceptionExamples.resolve("no");
		} catch (MyCheckedException e) {
			System.out.println("Нужно проверить проверить! " + e.getMessage());
			e.printStackTrace();
		}
		/*
		basic.exceptionExamples.MyCheckedException: Unsupported type found
			at basic.exceptionExamples.ExceptionExamples.resolve(ExceptionExamples.java:12)
			at basic.exceptionExamples.ExceptionExamples.main(ExceptionExamples.java:25)
		Нужно проверить проверить!
		 */

		/**
		 * Обработать оба исключения
		 */

		try {
			exceptionExamples.resolve("no");
			exceptionExamples.calculate("no");
		} catch (MyCheckedException | MyUncheckedException e) {
			System.out.println("Все проверили!");
		} finally {
			System.out.println("Это выполняется всегда!");
		}

		/**
		 * Try с автоматическим освобождением ресурсов,
		 * Чтобы в finally не вылетало исключение
 		 */

		/*
		Ресурс реализует AutoCloseable, в нем один метод close
		он и выполнится в любом случае
		 */
		try(InputStream is = new FileInputStream("a.txt")) {
			is.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		/**
		 * Обертывание исключения
		 */
		try {
			exceptionExamples.resolve("no");
		} catch (Exception e) {
			throw new MyCheckedException(e);
		}
	}
}
