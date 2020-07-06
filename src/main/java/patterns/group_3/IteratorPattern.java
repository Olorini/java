package patterns.group_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorPattern {

	public static void main(String[] args) {

		FirstMenu firstMenu = new FirstMenu();
		firstMenu.addMenuItem(new MenuItem("Fish", "No", false, 2.00));
		firstMenu.addMenuItem(new MenuItem("Cheese", "No", false, 2.00));

		SecondMenu secondMenu = new SecondMenu();
		secondMenu.addMenuItem(new MenuItem("Cola", "No", false, 2.00));
		secondMenu.addMenuItem(new MenuItem("Sprite", "No", false, 2.00));
		secondMenu.addMenuItem(new MenuItem("Juice", "No", false, 2.00));

		List<Iterable> menus = new ArrayList();
		menus.add(firstMenu);
		menus.add(secondMenu);
		MenuPrinter printer = new MenuPrinter(menus);
		printer.print();
	}

	static class MenuPrinter {
		List<Iterable> menus;

		public MenuPrinter(List<Iterable> menus) {
			this.menus = menus;
		}

		public void print() {
			Iterator mainIterator = menus.iterator();
			while (mainIterator.hasNext()) {
				Iterable menu = (Iterable) mainIterator.next();
				printMenu(menu.iterator());
			}
		}

		private void printMenu(Iterator iterator) {
			while (iterator.hasNext()) {
				MenuItem item = (MenuItem) iterator.next();
				System.out.print(item.getName() + ", ");
				System.out.print(item.getPrice() + " -- ");
				System.out.println(item.getDescription());
			}
		}
	}

	static class MassiveIterator implements Iterator {
		MenuItem[] menuItems;
		int position = 0;

		public MassiveIterator(MenuItem[] menuItems) {
			this.menuItems = menuItems;
		}

		@Override
		public boolean hasNext() {
			return position < menuItems.length && menuItems[position] != null;
		}

		@Override
		public Object next() {
			MenuItem item = menuItems[position];
			position ++;
			return item;
		}
	}


	static class FirstMenu implements Iterable {
		List<MenuItem> menuItems = new ArrayList<>();

		public void addMenuItem(MenuItem item) {
			menuItems.add(item);
		}

		public Iterator iterator() {
			return menuItems.iterator();
		}
	}

	static class SecondMenu implements Iterable{
		static final int MAX_ITEMS = 6;
		int currentNumber = 0;
		MenuItem[] menuItems = new MenuItem[6];

		public void addMenuItem(MenuItem item) {
			if (currentNumber >= MAX_ITEMS) {
				System.out.println("Sorry. Menu is full");
			} else {
				menuItems[currentNumber] = item;
				currentNumber ++;
			}
		}

		public Iterator iterator() {
			return new MassiveIterator(menuItems);
		}
	}

	static class MenuItem {
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

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public boolean isVegetarian() {
			return vegetarian;
		}

		public double getPrice() {
			return price;
		}
	}
}
