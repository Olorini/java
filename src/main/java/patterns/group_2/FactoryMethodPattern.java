package patterns.group_2;

/**
 * Паттерн проектирования "ФАБРИЧНЫЙ МЕТОД"
 * Субклассы решают объекты каких классов создавать
 */

public class FactoryMethodPattern {
	public static void main(String[] args) {
		IProductFactory factory = getFactory("cheap");
		IProduct product = factory.create();
		product.priceDescription();
	}

	private static IProductFactory getFactory(String type) {
		switch (type) {
			case "cheap":
				return new CheapProductFactory();
			case "expensive":
				return new ExpensiveProductFactory();
			default: throw new RuntimeException("Unknown");
		}
	}

	interface IProduct {
		void priceDescription();
	}

	static class ExpensiveProduct implements IProduct{
		@Override
		public void priceDescription() {
			System.out.println("Дорогой продукт");
		}
	}

	static class CheapProduct implements IProduct{
		@Override
		public void priceDescription() {
			System.out.println("Дешевый продукт");
		}
	}

	interface IProductFactory {
		IProduct create();
	}

	static class ExpensiveProductFactory implements IProductFactory {
		@Override
		public IProduct create() {
			return new ExpensiveProduct();
		}
	}

	static class CheapProductFactory implements IProductFactory {
		@Override
		public IProduct create() {
			return new CheapProduct();
		}
	}
}
