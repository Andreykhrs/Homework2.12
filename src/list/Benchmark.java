package list;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class Benchmark {
    public static void main(String[] args) {
        System.out.printf("Среднее время выполнения пузырьком: %d ms%n", calculateExecutionTimeInMillis(10,
                100_000, array -> sortBubble(array)));
        System.out.printf("Среднее время выполнения выбором: %d ms%n", calculateExecutionTimeInMillis(10,
                100_000, array -> sortSelection(array)));
        System.out.printf("Среднее время выполнения вставками: %d ms%n", calculateExecutionTimeInMillis(10,
                100_000, array -> sortInsertion(array)));


    }
    private static long calculateExecutionTimeInMillis(int repeat,
                                                       int size,
                                                       Consumer<int[]> sortAlgorithm) {
        long timeInMillis = 0;
        for (int i = 0; i < repeat; i++) {
            long start = System.currentTimeMillis();
            sortAlgorithm.accept(generateArray(size));
            timeInMillis += System.currentTimeMillis() - start;
        }
        return timeInMillis / repeat;
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
        }
        return array;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


}
