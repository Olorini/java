package basic.filesExamples;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;

public class FilesExamples {

	public static void main(String[] args) throws IOException {

		/**
		 * Старые классы для работы с файлами и папками
		 */
		// Получить файл
		File myTextFile = new File("C:\\files\\my.txt");

		// Получить папку
		File myFolder = new File("C:\\files");

		// Cформировать путь к файлу, подходящий для всех ОС
		StringJoiner joiner = new StringJoiner(File.separator);
		joiner.add("C:").add("files");
		String dirPath = joiner.toString();

		// Конструктор с двумя параметрами
		// Путь к папке + Название файла
		File file = new File(dirPath, "my.txt");

		// Получить абсолютный путь к файлу
		file.isAbsolute();
		file.getAbsolutePath();

		// Исходный путь
		file.getPath();
		// Имя с расширением
		file.getName();
		// Имя родительской папки
		file.getParent();
		// Канонический путь
		String canonicalPath = file.getCanonicalPath();
		String canPath = myTextFile.getCanonicalPath();
		// Сравнение двух файлов
		if (canonicalPath.equals(canPath)) { }

		// File может указывать на несущ. файл или папку
		// Проверка существования
		file.exists();

		// Является ли файлом
		file.isFile();
		// Размер файла
		file.length();
		//Дата и время последней модификации
		file.lastModified();

		// Является ли папкой
		myFolder.isDirectory();
		// Список содержимого в папке в строках
		myFolder.list();
		// Список содержимого в файлах
		myFolder.listFiles();
		// Фильтр для поиска содержимого
		File[] sourceFiles = myFolder.listFiles(f -> f.getName().endsWith(".java"));

		// Создание файла
		// Используется редко
		boolean success = file.createNewFile();

		// Создание папки
		// одной
		myFolder.mkdir();
		// несколько вложенных
		myFolder.mkdirs();

		// Удаление файла или папки
		// Удалить можно только пустую папку
		file.delete();

		// Переименовывает и перемещает. Зависит от файловой системы
		file.renameTo(myFolder);

		/*-------------------------------------------------------*/
		/**
		 * Новые классы для работы с файлами и папками
		 */

		// Аналог File
		// Есть такие же методы, что и в File
		Path path = Paths.get("C:\\examples\\red.txt");
		// Конвертация
		File f = path.toFile();
		Path p = f.toPath();

		// Получение части пути по индексу
		path.getName(1);
		path.startsWith("C:");
		path.getNameCount();
		// Вычисление относительного пути между двумя местами
		Paths.get("C:\\ut").relativize(p);

		// Проверка существования
		Files.exists(p);
		Files.isRegularFile(p);
		Files.size(p);
		Files.getLastModifiedTime(p).toMillis();

		// Копирование
		Files.copy(p, Paths.get("C:\\kot"), StandardCopyOption.REPLACE_EXISTING);
		// Перемещение
		Files.move(p, Paths.get("C:\\kot"), StandardCopyOption.REPLACE_EXISTING);

		//Получение содержимого папки
		Path dir = Paths.get("C:\\dirs");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path p2 : stream) {
				System.out.println(p2);
			}
		}

		// Создание папок
		Files.createDirectories(dir);
		Files.createDirectory(dir);

		/*----------------------------------------------------*/

		/**
		 * Удаление директорий со всем содержимым
 		 */

		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}
			//После выхода из папки
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc == null) {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				} else {
					throw exc;
				}
			}
		});

		/**
		 * Работа с zip архивом
 		 */

		Path zipPath = Paths.get("dfdf\\src.zip");
		try (FileSystem zipFs = FileSystems.newFileSystem(zipPath, null)) {
			for (Path pZip : zipFs.getRootDirectories()) {
				Files.walkFileTree(pZip, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						System.out.println(file);
						return FileVisitResult.CONTINUE;
					}
				});
			}
		}
	}
}
