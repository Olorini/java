package patterns.group_2;

/**
 * Паттерн проектирования "ШАБЛОННЫЙ МЕТОД"
 * Субклассы определяют реализацию шагов алгоритма
 */

public class TemplateMethodPattern {

	public static void main(String[] args) {
		CaffeineBeverage tea = new Tea();
		tea.prepareRecipe();
		CaffeineBeverage coffee = new Coffee();
		coffee.prepareRecipe();
	}

	public static abstract class CaffeineBeverage {

		// Обязательно final
		final public void prepareRecipe(){
			boilWater();
			brew();
			pourInCup();
			if (customerWantsCondiments()) {
				addCondiments();
			}
		}

		public abstract void brew();

		public abstract void addCondiments();

		public void boilWater() {
			System.out.println("Boiling water");
		}

		public void pourInCup() {
			System.out.println("Pouring into cup");
		}

		// Hook
		public boolean customerWantsCondiments() {
			return true;
		}
	}

	public static class Tea extends CaffeineBeverage {
		@Override
		public void brew() {
			System.out.println("Steeping the tea");
		}

		@Override
		public void addCondiments() {
			System.out.println("Adding lemon");
		}
	}

	public static class Coffee extends CaffeineBeverage {
		@Override
		public void brew() {
			System.out.println("Dripping through filter");
		}

		@Override
		public void addCondiments() {
			System.out.println("Adding milk and sugar");
		}

		@Override
		public boolean customerWantsCondiments() {
			boolean need = false;
			return need;
		}
	}
}
