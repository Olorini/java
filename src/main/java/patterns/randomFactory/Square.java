package patterns.randomFactory;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("draw square");
	}

	@Override
	public void erase() {
		System.out.println("erase square");
	}
}
