package cn.meetdevelop.insert_sort;

import cn.meetdevelop.search_help.ArrayGenerator;
import cn.meetdevelop.search_help.SortHelper;

/**
 * description: InsertSort
 * date: 2020/11/10 9:35
 * author: zgy
 * version: 1.0
 */
public class InsertSort {

    private InsertSort() {

    }

    public static <T extends Comparable<T>> void sort(T[] data) {
        for (int i = 1; i < data.length; i++) {

            for (int j = i; j > 0; j--) {
                // 这个条件也为循环继续的条件
                if (data[j].compareTo(data[j - 1]) < 0) {
                    swap(data, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sort1(T[] data) {
        for (int i = 1; i < data.length; i++) {

            for (int j = i; j > 0 && data[j].compareTo(data[j - 1]) < 0; j--) {
                swap(data, j, j - 1);
            }
        }
    }

    // 插入排序优化
    public static <T extends Comparable<T>> void sort2(T[] data) {
        for (int i = 1; i < data.length; i++) {

            T tmp = data[i];
            int j = i;
            // 首先将要插入的元素保存，然后找到其要插入的位置
            // 将 swap 的操作修改为了赋值操作
            for (; j > 0 && tmp.compareTo(data[j - 1]) < 0; j--) {
                data[j] = data[j - 1];
            }
            data[j] = tmp;
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
            SortHelper.sortTest("cn.meetdevelop.insert_sort.InsertSort", orderArray);
            System.out.println("RandomArray:   ");
            Integer[] randomArray = ArrayGenerator.generateRandomArray(length, length);
            SortHelper.sortTest("cn.meetdevelop.insert_sort.InsertSort", randomArray);
        }
    }

}
