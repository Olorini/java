package basic.serializationExamples;

import java.io.Serializable;
import java.time.LocalDate;

public class SerialItem implements Serializable {
	private Long id;
	private String name;
	private LocalDate birthDate;
	// поле которое не надо сохранять
	private transient int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		if (age == 0) {
			return birthDate.until(LocalDate.now()).getYears();
		}
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
