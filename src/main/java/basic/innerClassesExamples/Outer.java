package basic.innerClassesExamples;

public class Outer {
	private int i;

	public Outer() {
		System.out.println("Outer!");
	}

	//Внутренний класс в самом классе
	public class Inner1 {
		public Inner1() {
			System.out.println("Inner1!");
		}
		public void doIt() {
			//Обращение к объекту внешнего классса из внутреннего
			Outer.this.i = 21;
		}
	}

	//Внутренний класс в области действия метода
	public IDestination getDestination(String label) {
		class Inner2 implements IDestination {
			private String label;
			/*Конструктор*/
			private Inner2(String label) {
				this.label = label;
			}

			@Override
			public String readLabel() {
				return label;
			}
		}
		return new Inner2(label);
	}

	//Анонимный внутренний класс в области действия метода
	public IDestination getDestination2(final String label) {
		return new IDestination() {
			private String label2 = label;

			@Override
			public String readLabel() {
				return label2;
			}
		};
	}

	//Внутренний класс в произвольной области действия
	private void intenalTracking(boolean isOk) {
		if (isOk) {
			class Inner3 {
				private String id;
				/*Конструктор*/
				public Inner3(String id) {
					this.id = id;
				}

				public String getSlip() {
					return id;
				}
			}
			Inner3 ts = new Inner3("slip");
			String s = ts.getSlip();
		}
	}
}
