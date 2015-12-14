package radix_sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Radix_sort {
   
    public static void countingSort(String[] A, int W) {
        //сортировка А по старшим W символам
        int N = A.length; // длина массива строк
        // количество символов в ASCII алфавите (2^n), 1 символ = 8 бит.
        int K = 256; //Мощность алфавита   

        String[] B = new String[N]; // Массив, который хранит отсортированные значения

        for (int d = W - 1; d >= 1; d--) {
            // Поразрядная сортировка подсчетом по d-му симвволу

            // Вычисление счетчиков повторений
            int[] C = new int[K + 1];
            for (int i = 0; i < N; i++) {
                C[A[i].charAt(d) + 1]++;
            }

            // Преобразование счетчиков в индексы
            for (int r = 0; r < K; r++) {
                C[r + 1] += C[r];
            }

            // Распределение данных
            for (int i = 0; i < N; i++) {
                B[C[A[i].charAt(d)]++] = A[i];
            }

            // Копирование назад
            for (int i = 0; i < N; i++) {
                A[i] = B[i];
            }
        }

    }
        private static String[] initArray() {
            //Считываем массив из файла
            String[] temp = new String[1000];
        try {
            Scanner scanner = new Scanner(new File("D:\\!Самообразование\\Prometheus\\Тесты\\anagrams.txt"));
            int i = 0;
            while (scanner.hasNext()) {
                temp[i++] = scanner.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Radix_sort.class.getName()).log(Level.SEVERE, null, ex);
        }
            return temp;
    }
    private static void countLiterals(String[]A){
        // Подсчет количества каждого символа в строке
        int n = 0;
        String[] array = new String[3 * A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length(); j++) {
               array[n++] = String.valueOf(A[i].charAt(j));
            }
        }
        Arrays.stream(array)
                .collect(Collectors.groupingBy(s -> s))
                .forEach((k, v) -> System.out.println(k + " " + v.size()));
        
    }    

    public static void main(String[] args) {
        String A[]= initArray();// инициализация массива строк
        int N = A.length; // длина массива
        countLiterals(A); // выводим количество каждого символа в массиве
        
        // проверяем, чтобы строка имела фиксированную длину
        int W = A[0].length();
        for (int i = 0; i < N; i++) {
            assert A[i].length() == W : "Strings must have fixed length";
        }

        // сортировка строк
        countingSort(A, W);

        // печать результата
        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }

    }
}
