package patterns.group_2;

public class AbstractFactoryPattern {
	public static void main(String[] args) {
		IProjectFactory factory = new WebFactoryProject();
		IDeveloper developer = factory.createDeveloper();
		ITester tester = factory.createTester();
		IProjectManager manager = factory.createPM();
		manager.manage();
		developer.writeCode();
		tester.test();

		IProjectFactory factory2 = new GameFactoryProject();
		IDeveloper developer2 = factory2.createDeveloper();
		ITester tester2 = factory2.createTester();
		IProjectManager manager2 = factory2.createPM();
		manager2.manage();
		developer2.writeCode();
		tester2.test();
	}

	public interface ITester {
		void test();
	}
	public static class WebTester implements ITester {
		@Override
		public void test() {
			System.out.println("Test web site");
		}
	}
	public static class GameTester implements ITester {
		@Override
		public void test() {
			System.out.println("Test game");
		}
	}

	public interface IDeveloper {
		void writeCode();
	}
	public static class PhpDeveloper implements IDeveloper {
		@Override
		public void writeCode() {
			System.out.println("Write php code");
		}
	}
	public static class CppDeveloper implements IDeveloper {
		@Override
		public void writeCode() {
			System.out.println("Write cpp code");
		}
	}

	public interface IProjectManager {
		void manage();
	}
	public static class WebPM implements IProjectManager {
		@Override
		public void manage() {
			System.out.println("Manage web site creation");
		}
	}
	public static class GamePM implements IProjectManager{
		@Override
		public void manage() {
			System.out.println("Mange game creation");
		}
	}

	public interface IProjectFactory {
		IDeveloper createDeveloper();
		ITester createTester();
		IProjectManager createPM();
	}
	public static class WebFactoryProject implements IProjectFactory {
		@Override
		public IDeveloper createDeveloper() {
			return new PhpDeveloper();
		}
		@Override
		public ITester createTester() {
			return new WebTester();
		}
		@Override
		public IProjectManager createPM() {
			return new WebPM();
		}
	}
	public static class GameFactoryProject implements IProjectFactory {
		@Override
		public IDeveloper createDeveloper() {
			return new CppDeveloper();
		}
		@Override
		public ITester createTester() {
			return new GameTester();
		}
		@Override
		public IProjectManager createPM() {
			return new GamePM();
		}
	}
}
