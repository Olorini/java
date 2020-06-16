package patterns.group_one;

public class Decorator {
	/**
	 *  Паттерн проектирования "ДЕКОРАТОР"
	 *  Обертка -- не меняет интефейс,
	 *  только добавляет новые обязанности
	 */

	public static void main(String[] args) {
		IComponent component = new ConcreteComponent();
		System.out.println(component.count());
		System.out.println(component.getCaption());
		IComponent wrappedComponent = new WrappedComponent(component);
		System.out.println(wrappedComponent.count());
		System.out.println(wrappedComponent.getCaption());
	}

	interface IComponent {
		int count();
		String getCaption();
	}

	static class ConcreteComponent implements IComponent {
		@Override
		public int count() {
			return 10;
		}
		@Override
		public String getCaption() {
			return "Конкретный компонент";
		}
	}

	static class WrappedComponent implements IComponent {
		IComponent component;
		WrappedComponent(IComponent component) {
			this.component = component;
		}
		@Override
		public int count() {
			return component.count() + 2;
		}
		@Override
		public String getCaption() {
			return component.getCaption() + ": Обертка.";
		}
	}
}
