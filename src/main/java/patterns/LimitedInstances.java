package patterns;

/**
 * Создает ограниченное количество экземпляров класса
 */

public class LimitedInstances {

	private LimitedInstances(){ }
	private static int count = 0;

	public static LimitedInstances getLimitedInstance() throws Exception {
		if (count < 9) {
			count = count++;
			return new LimitedInstances();
		} else {
			throw new Exception("Количество объектов привышает лимит");
		}
	}
}
