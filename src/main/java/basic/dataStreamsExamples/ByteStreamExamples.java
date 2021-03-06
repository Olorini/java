package basic.dataStreamsExamples;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteStreamExamples {

	private void basic() throws IOException {

		// Ввод данных. Чтение
		InputStream inputStream;
		// Чтение из файла
		InputStream fileInputStream = new FileInputStream(new File("C:\\in.txt"));
		InputStream fileInputStreamNew =
				Files.newInputStream(Paths.get("C:\\in.txt"));

		// Вывод данных. Запись
		OutputStream outputStream;
		// Запись в файл
		OutputStream fileOutputStream = new FileOutputStream(new File("C:\\out.txt"));
		OutputStream fileOutputStreamNew =
				Files.newOutputStream(Paths.get("C:\\in.txt"));

		/**
		 * Копирование данных из входного потока в выходной
		 */
		int totalBytesWritten = 0;
		// Врем. буфер 1 Кб.
		byte[] buf = new byte [1024];
		int blockSize;
		while ((blockSize = fileInputStream.read(buf)) > 0) {
			fileOutputStream.write(buf, 0, blockSize);
			totalBytesWritten += blockSize;
		}

		/**
		 * Обертки
		 */
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		// Запись строк
		dataOutputStream.writeUTF("AA");
		// Запись чисел
		dataOutputStream.writeInt(456);
	}

	public static void main(String[] args) {
		try {
			//readFromSocket();
			readClass();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Чтение байткода класса
	 */
	private static void readClass() throws IOException {
		try (InputStream inputStream
				= ByteStreamExamples.class.getResourceAsStream("ByteStreamExamples.class")) {
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			int read = dataInputStream.read();
			while (read > 0) {
				System.out.print(read);
				read = dataInputStream.read();
			}
		}
	}


	/**
	 * Чтение  и запись данных в сети
	 */
	private static void readFromSocket() throws IOException {
		try (Socket socket = new Socket("ya.ru", 80)) {
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("GET / HTTP/1.1\r\n\r\n".getBytes());
			outputStream.flush();

			InputStream inputStream = socket.getInputStream();
			int read = inputStream.read();
			while (read > 0) {
				System.out.print((char) read);
				read = inputStream.read();
			}
		}
	}

	/**
	 * Из массива байт
	 */
	private void readArrayOfByte() {
		byte[] data = {1, 2, 3, 4, 5};
		InputStream inputStream = new ByteArrayInputStream(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] result = outputStream.toByteArray();
	}

	/**
	 * Неблокирующий ввод/вывод. Не нужно ждать освобождение ресурса потоком
	 * Сервер, обслуживающий в одном потоке несколько клиентов
	 */
	private void notBlock() {
		Path path = Paths.get("C\\:path");
		try (ReadableByteChannel in = FileChannel.open(path);
		     WritableByteChannel out = Channels.newChannel(System.out)) {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (in.read(buffer) >= 0 || buffer.position() != 0) {
				buffer.flip();
				out.write(buffer);
				buffer.compact();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
