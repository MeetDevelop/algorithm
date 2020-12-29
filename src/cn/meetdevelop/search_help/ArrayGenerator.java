package cn.meetdevelop.search_help;


import java.lang.reflect.Array;
import java.util.Random;

/**
 * description: ArrayGenerator
 * date: 2020/11/6 11:09
 * author: zgy
 * version: 1.0
 */
public class ArrayGenerator {

    private ArrayGenerator() {

    }


    public static Integer[] generateOrderArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        return array;
    }


    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] array = new Integer[n];

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }

}
