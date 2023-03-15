package ru.geekbrains.lesson2;

import java.util.Arrays;
import java.util.Random;

public class Program {

    private static final Random random = new Random();

    public static void main(String[] args) {

       /* int[] array = ArrayUtils.prepareArray();
        ArrayUtils.printArray(array);
        // сортировка выбором
        SortUtils.directSort(array);
        ArrayUtils.printArray(array);

        // быстрая сортировка
        array = ArrayUtils.prepareArray();
        ArrayUtils.printArray(array);
        SortUtils.quickSort(array);
        ArrayUtils.printArray(array);*/

        int[] testArr = ArrayUtils.prepareArray(10000);
        long startTime = System.currentTimeMillis();
        SortUtils.directSort(testArr.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время выполнения сортировки выбором - %d мс\n", processingTime);

        startTime = System.currentTimeMillis();
        SortUtils.quickSort(testArr.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время выполнения быстрой сортировки - %d мс\n", processingTime);

        startTime = System.currentTimeMillis();
        Arrays.sort(testArr.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время выполнения системной сортировки - %d мс\n", processingTime);

        startTime = System.currentTimeMillis();
        SortUtils.heapSort(testArr.clone());
        endTime = System.currentTimeMillis();
        processingTime = endTime - startTime;
        System.out.printf("Время выполнения пирамидальной сортировки - %d мс\n", processingTime);
    /*

        int[] testArr = new int[]{-5, 100, -1, 3, 4, 5, 9, 22, 9, 10, -6};
        ArrayUtils.printArray(testArr);
        SortUtils.heapSort(testArr);
        ArrayUtils.printArray(testArr);
        int searchElem = 9;
        int res01 = SearchUtils.binarySearch(testArr, searchElem);
        System.out.printf("Элемент %d %s\n", searchElem, res01 >= 0 ? String.format("найден в массиве по индексу %d", res01) : "не найден");*/
    }


    static class ArrayUtils {

        /**
         * Подготовить массив случайных чисел
         *
         * @return
         */
        static int[] prepareArray() {
            int[] arr = new int[random.nextInt(16) + 5];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(101) - 50;
            }
            return arr;
        }

        /**
         * Подготовить массив случайных чисел
         *
         * @param length кол-во элементов
         * @return
         */
        static int[] prepareArray(int length) {
            int[] arr = new int[length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(101) - 50;
            }
            return arr;
        }

        /**
         * Распечатать массив
         *
         * @param arr
         */
        static void printArray(int[] arr) {
            for (int item : arr) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }


    }

    static class SortUtils {

        /**
         * Сортировка выбором
         *
         * @param array
         */
        static void directSort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                int minNum = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minNum]) {
                        minNum = j;
                    }
                    if (minNum != i) {
                        int temp = array[i];
                        array[i] = array[minNum];
                        array[minNum] = temp;
                    }
                }
            }
        }

        static void quickSort(int[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        /**
         * Быстрая сортировка
         *
         * @param arr
         * @param start
         * @param end
         */
        private static void quickSort(int[] arr, int start, int end) {
            int left = start;
            int right = end;
            int mid = arr[(start + end) / 2];

            do {
                while (arr[left] < mid) {
                    left++;
                }

                while (arr[right] > mid) {
                    right--;
                }
                if (left <= right) {
                    if (left < right) {
                        int buf = arr[left];
                        arr[left] = arr[right];
                        arr[right] = buf;
                    }
                    left++;
                    right--;
                }
            } while (left <= right);
            if (left < end) {
                quickSort(arr, left, end);
            }
            if (start < right) {
                quickSort(arr, start, right);
            }
        }
        private static void heapify(int[] arr, int n, int i) {

            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[left] > arr[largest]) {
                largest = left;
            }

            if (right < n && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != i) {
                int temp = arr[i];
                arr[i] = arr[largest];
                arr[largest] = temp;

                heapify(arr, n, largest);
            }
        }
        static void heapSort(int arr[]){
            heapSort(arr, arr.length);
        }
        private static void heapSort(int arr[], int n) {
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }
            for (int i = n - 1; i >=0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0);
            }

        }

    }

    static class SearchUtils {
        static int binarySearch(int[] array, int value) {
            return binarySearch(array, value, 0, array.length - 1);
        }

        static int binarySearch(int[] array, int value, int left, int right) {
            if (right < left) {
                return -1;
            }
            int mid = (left + right) / 2;

            if (array[mid] == value) {
                return mid;
            } else if (array[mid] > value) {
                return binarySearch(array, value, left, mid - 1);
            } else {
                return binarySearch(array, value, mid + 1, right);
            }
        }


    }

}
