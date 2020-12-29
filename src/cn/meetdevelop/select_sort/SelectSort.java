package cn.meetdevelop.select_sort;

import cn.meetdevelop.search_help.ArrayGenerator;
import cn.meetdevelop.search_help.SortHelper;

/**
 * description: SelectSort
 * date: 2020/11/6 10:52
 * author: zgy
 * version: 1.0
 */
public class SelectSort {

    private SelectSort() {

    }

    // 对数据 data 进行排序
    public static <T extends Comparable<T>> void sort(T[] data) {
        // 每次循环保持 [0, i) 有序，[i, n) 无序
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;

            for (int j = i; j < data.length; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(data, i, minIndex);
        }
    }

    // 对上面的排序方式进行改造
    // 从后向前进行排序
    public static <T extends Comparable<T>> void sort1(T[] data) {

        for (int i = data.length - 1; i > 0; i --) {
            int maxIndex = i;
            for (int j = i; j >= 0; j --) {
                if (data[j].compareTo(data[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(data, maxIndex, i);
        }
    }

    private static <T> void swap(T[] data, int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) throws Exception {
        int[] dataLength = new int[]{10000, 100000};

        for (int length : dataLength) {
            System.out.println("OrderedArray:   ");
            Integer[] orderArray = ArrayGenerator.generateOrderArray(length);
            SortHelper.sortTest("cn.meetdevelop.select_sort.SelectSort", orderArray);
            System.out.println("RandomArray:   ");
            Integer[] randomArray = ArrayGenerator.generateRandomArray(length, length);
            SortHelper.sortTest("cn.meetdevelop.select_sort.SelectSort", randomArray);

        }

    }
}
