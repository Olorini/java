package basic.innerClasses;

public class RunClass {

	public static void doId() {
		Outer outer = new Outer();
		//Создание объекта внутреннего класса извне
		Outer.Inner1 inner = outer.new Inner1();
	}

	public static void main(String[] args) {
		doId();
	}
}
