package cn.meetdevelop.search_help;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: SortHelper
 * date: 2020/11/6 11:27
 * author: zgy
 * version: 1.0
 */
public class SortHelper {
    private SortHelper() {

    }

    private static <T extends Comparable<T>> boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i].compareTo(data[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>> void sortTest(String sortType, T[] data) throws Exception {
        Class clazz = Class.forName(sortType);
        Method method = clazz.getMethod("sort", Comparable[].class);

        long start = System.nanoTime();
        method.invoke(null, new Object[]{data});
        if (!isSorted(data)) {
            throw new RuntimeException(sortType + " is failed!");
        }
        long end = System.nanoTime();

        double time = (end - start) / 1000000000.0;

        System.out.printf("sortType : %s\tn : %d\ttime: %fs\n", sortType, data.length, time);

    }
}
