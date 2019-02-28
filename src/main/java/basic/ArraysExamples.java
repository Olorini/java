package basic;

import java.util.Arrays;

public class ArraysExamples {

	//Инициализация
	private static int[] arr = {1, 2, 3};
	int[] arr2 = arr;
	int[] arr3 = new int[10];

	//Двумерные массивы
	private static int[][] matrix = {{1, 2}, {3, 4, 5}};

	//Метод с аргументами переменной длины
	private static void func(int ...args) {
		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			arr[i] = args[i];
		}
		//Вывод массива
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		//Вызовы метода с аргументами переменной длины
		func(5, 6, 7);
		func(arr);

		//Сравнение одномерных массивов
		int[] arr4 = {1, 3, 3};
		System.out.println(Arrays.equals(arr, arr4));

		//Сравнение и вывод на консоль многомерных массивов
		int[][] matrix2 = {{1, 2}, {3, 8, 5}};
		System.out.println(Arrays.deepToString(matrix2) + " " + Arrays.deepEquals(matrix2, matrix));
	}
}
