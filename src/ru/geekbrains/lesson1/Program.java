package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {
    public static void main(String[] args) {
        int lastNumber = 4;
        AtomicInteger counter = new AtomicInteger();
        /*System.out.printf("Сумма всех чисел от 1 до %d равна %d; Количество итераций %d \n",
                lastNumber, sum1(lastNumber, counter), counter.get());
        counter.setRelease(0);
        List<Integer> simpleNumbers = findSimpleNumbers(lastNumber, counter);
        for (int item : simpleNumbers) {
            System.out.printf("Простые числа от 1 до %d => %d; Количество итераций %d \n",
                    lastNumber, item, counter.get());
        }

        f(4);
*/
        long startTime = System.currentTimeMillis();
        System.out.printf("(1)%d количество итераций - %d\n", fib(10, counter), counter.get());
        long endTime = System.currentTimeMillis();
        long processTime = endTime - startTime;
        System.out.printf("Операция выполнена за %d мс.\n", processTime);

        counter.setRelease(0);
        startTime = System.currentTimeMillis();
        System.out.printf("(2)%d количество итераций - %d\n", fib2(10, counter), counter.get());
        endTime = System.currentTimeMillis();
        processTime = endTime - startTime;
        System.out.printf("Операция выполнена за %d мс.\n", processTime);

        counter.setRelease(0);
        startTime = System.currentTimeMillis();
        System.out.printf("(1)%d количество итераций - %d\n", fib(38, counter), counter.get());
        endTime = System.currentTimeMillis();
        processTime = endTime - startTime;
        System.out.printf("Операция выполнена за %d мс.\n", processTime);

        counter.setRelease(0);
        startTime = System.currentTimeMillis();
        System.out.printf("(2)%d количество итераций - %d\n", fib2(38, counter), counter.get());
        endTime = System.currentTimeMillis();
        processTime = endTime - startTime;
        System.out.printf("Операция выполнена за %d мс.\n", processTime);
    }

    /**
     * Необходимо написать алгоритм, считающий сумму всех чисел от 1 до N
     */
    public static int sum1(int lastNumber, AtomicInteger counter) {
        int sum = 0;
        for (int i = 0; i <= lastNumber; i++) {
            sum += i;
            counter.getAndIncrement();
        }
        return sum;
    }

    public static List<Integer> findSimpleNumbers(int lastNumber, AtomicInteger counter) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i <= lastNumber; i++) {
            boolean simple = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    counter.getAndIncrement();
                    simple = false;
                    break;
                }
            }
            if (simple) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    /**
     * n = 4
     * recursive function
     *
     * @param n is a number
     */
    static void f(int n) {
        System.out.println(n);
        if (n >= 3) {
            f(n - 1);
            f(n - 2);
            f(n - 2);
        }
    }


    static long fib(int num, AtomicInteger counter) {
        counter.getAndIncrement(); // +1
        if (num == 0 || num == 1) return num;
        return fib(num - 1, counter) + fib(num - 2, counter);
    }

    static long fib2(int num, AtomicInteger counter){
        if (num == 0 || num == 1) return num;
        long[] numbers = new  long[num + 1];
        numbers[0]= 0;
        numbers[1] = 1;
        for (int i = 2; i <= num; i++) {
            numbers[i] = numbers[i-1] + numbers[i-2];
            counter.getAndIncrement();
        }
        return numbers[num];
    }
}
