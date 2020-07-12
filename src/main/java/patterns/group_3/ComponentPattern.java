package patterns.group_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Паттерн проектирования "КОМПОНОВЩИК"
 * Позволяет выполнять одинаковые операции
 * с коллекциями и объектами этих коллекций
 */

public class ComponentPattern {

	public static void main(String[] args) {
		MenuComponent dessert_menu = new Menu("Dessert menu", "*");
		dessert_menu.add(new MenuItem("Cake", "No", false, 2.00));
		dessert_menu.add(new MenuItem("Cookies", "No", true, 2.00));

		MenuComponent dinner_menu = new Menu("Dinner menu", "*");
		dinner_menu.add(new MenuItem("Fish", "No", false, 2.00));
		dinner_menu.add(new MenuItem("Salad", "No", true, 2.00));
		dinner_menu.add(dessert_menu);

		//MenuPrinter.printMenu(dinner_menu);
		MenuPrinter.printVegetarianMenu(dinner_menu);

	}

	abstract static class MenuComponent {
		public void add(MenuComponent component) {}
		public void remove(MenuComponent component) {}
		public MenuComponent getChild(int i) { return null; }
		public String getName() {
			return "";
		}
		public String getDescription() { return ""; }
		public double getPrice() {
			return 1.00;
		}
		public boolean isVegetarian() {
			return false;
		}
		public void print() {}
		public abstract Iterator createIterator();
	}

	static class MenuItem extends MenuComponent {
		String name;
		String description;
		boolean vegetarian;
		double price;

		public MenuItem(String name, String description, boolean vegetarian, double price) {
			this.name = name;
			this.description = description;
			this.vegetarian = vegetarian;
			this.price = price;
		}

		@Override
		public String getName() {
			return name;
		}
		@Override
		public String getDescription() {
			return description;
		}
		@Override
		public boolean isVegetarian() {
			return vegetarian;
		}
		@Override
		public double getPrice() {
			return price;
		}
		@Override
		public void print() {
			System.out.print(getName() + ", ");
			if (isVegetarian()) {
				System.out.print("(V)");
			}
			System.out.print(getPrice() + " -- ");
			System.out.println(getDescription());
		}
		@Override
		public Iterator createIterator() {
			return new NullIterator();
		}
	}

	static class Menu extends MenuComponent {
		List<MenuComponent> components = new ArrayList<>();
		Iterator iterator;
		String name;
		String description;

		public Menu(String name, String description) {
			this.name = name;
			this.description = description;
		}

		@Override
		public String getName() {
			return name;
		}
		@Override
		public String getDescription() {
			return description;
		}
		@Override
		public void add(MenuComponent component) {
			components.add(component);
		}
		@Override
		public void remove(MenuComponent component) {
			components.remove(component);
		}
		@Override
		public MenuComponent getChild(int i) {
			return  components.get(i);
		}
		@Override
		public void print() {
			System.out.print(getName() + ", ");
			System.out.println(getDescription());
			System.out.println("------------");

			Iterator iterator = components.iterator();
			while (iterator.hasNext()) {
				MenuComponent component = (MenuComponent) iterator.next();
				component.print();
			}
		}
		@Override
		public Iterator createIterator() {
			if (iterator == null) {
				iterator = new CompositeIterator(components.iterator());
			}
			return iterator;
		}
	}

	static class MenuPrinter {
		public static void printMenu(MenuComponent menus) {
			menus.print();
		}

		public static void printVegetarianMenu(MenuComponent menus) {
			Iterator iterator = menus.createIterator();
			System.out.println("Vegetarian menu");
			while (iterator.hasNext()) {
				MenuComponent menuComponent = (MenuComponent) iterator.next();
				if (menuComponent.isVegetarian()) {
					menuComponent.print();
				}
			}
		}
	}

	static class CompositeIterator implements Iterator {
		Stack stack = new Stack();

		public CompositeIterator(Iterator iterator) {
			stack.push(iterator);
		}

		@Override
		public Object next() {
			if (hasNext()) {
				Iterator iterator = (Iterator) stack.peek();
				MenuComponent component = (MenuComponent) iterator.next();
				if (component instanceof Menu) {
					stack.push(component.createIterator());
				}
				return component;
			}
			return null;
		}
		@Override
		public boolean hasNext() {
			if (stack.empty()) {
				return false;
			} else {
				Iterator iterator = (Iterator) stack.peek();
				if (!iterator.hasNext()) {
					stack.pop();
					return hasNext();
				} else {
					return true;
				}
			}
		}
	}

	static class NullIterator implements Iterator {
		@Override
		public Object next() {
			return null;
		}
		@Override
		public boolean hasNext() {
			return false;
		}
	}
}
