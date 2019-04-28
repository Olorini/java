package basic.streamExamples;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextStreamExamples {

	private InputStream fileInputStream;
	private OutputStream fileOutputStream;
	private Reader reader;
	private Writer writer;

	public TextStreamExamples(InputStream fileInputStream, OutputStream fileOutputStream) {
		try {
			this.fileInputStream = new FileInputStream(new File("C:\\in.txt"));
			this.fileOutputStream = new FileOutputStream(new File("C:\\out.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void basic() throws IOException {
		// Поток байтов в поток символов
		reader = new InputStreamReader(fileInputStream, "UTF-8");
		// Запись
		Charset charset = StandardCharsets.UTF_8;
		writer = new OutputStreamWriter(fileOutputStream, charset);
		// Кодировка по умолчанию
		Charset.defaultCharset();
	}

	/**
	 * Чтение и запись массивов и строк
	 */
	private void arrays() throws IOException {
		// Чтение из массива
		reader = new CharArrayReader(new char[] {'a', 'b', 'c'});
		reader = new StringReader("Hello word");

		// Запись
		StringWriter stringWriter = new StringWriter();
		stringWriter.write("Test");
		String stringRes = stringWriter.toString();

		CharArrayWriter charArrayWriter = new CharArrayWriter();
		charArrayWriter.write("TEST");
		String charRes = charArrayWriter.toString();
	}

	/**
	 * Чтение и запись текстовых файлов
	 */
	private void files() throws IOException {
		// Не позволяют указать кодировку. Поэтому исп. метод выше
		reader = new FileReader("C:\\in.txt");
		writer = new FileWriter("C:\\in.txt");
	}

	/**
	 * Чтение и запись с буфферизацией
	 */
	private void buffers() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(reader);
		BufferedReader newBufferedReader
				= Files.newBufferedReader(Paths.get("C\\dr.txt"), StandardCharsets.US_ASCII);
		// Читает из потока целую строку
		bufferedReader.readLine();
		// Строки в виде списка для небольших файлов
		List<String> list
				= Files.readAllLines(Paths.get("C\\dr.txt"), StandardCharsets.US_ASCII);

		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		// Добавление разделителя строк
		bufferedWriter.newLine();
		// Запись небольших данных
		Files.write(Paths.get("C\\dr2.txt"), list, StandardCharsets.US_ASCII);
	}

	/**
	 * Форматированный ввод/вывод
	 */
	private void formats() {
		// Вывод
		PrintWriter printWriter = new PrintWriter(writer);
		printWriter.print(2);
		printWriter.println();
		printWriter.printf("Hi, %s!", "Nick");
		// Т.к. не бросают IOException
		printWriter.checkError();

		PrintStream printStream = new PrintStream(fileOutputStream);
	}

	/**
	 * Разбор потока на слова и числа
	 */
	private void tokenizers() {
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringTokenizer tokenizer2 = new StringTokenizer("Hello word!");

		// Номальный класс
		Scanner scanner = new Scanner(reader)
				.useDelimiter("\\|")
				.useLocale(Locale.forLanguageTag("ru"));

		String token = scanner.next();
		boolean b = scanner.nextBoolean();
		double d = scanner.nextDouble();
		int i = scanner.nextInt();
	}

}
