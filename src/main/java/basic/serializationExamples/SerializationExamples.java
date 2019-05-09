package basic.serializationExamples;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class SerializationExamples {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialItem item = new SerialItem();
		item.setId(1L);
		item.setName("Nick");
		item.setBirthDate(LocalDate.of(1978, 3, 12));
		Path path = Paths.get("C:\\Users\\new.txt");
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
			oos.writeObject(item);
		}
		SerialItem desItem;
		try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
			desItem = (SerialItem) ois.readObject();
		}
		System.out.println(desItem.getId());
		System.out.println(desItem.getName());
	}
}
