package cn.meetdevelop.merge_sort;

import cn.meetdevelop.search_help.ArrayGenerator;
import cn.meetdevelop.search_help.SortHelper;

import java.util.Arrays;

/**
 * description: MergeSort
 * date: 2020/11/20 21:02
 * author: zgy
 * version: 1.0
 */
public class MergeSort {

    private MergeSort() {

    }


    /**
     * @param array
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }


    private static <E extends Comparable<E>> void sort(E[] array, int l, int r) {

        if (l >= r) return;

        int mid = l + ((r - l) >> 1);


        sort(array, l, mid);
        sort(array, mid + 1, r);
        // 当左半部分整体都小于右半部分时，可以省略 merge 操作
//        if (array[mid].compareTo(array[mid + 1]) > 0) {
//            merge(array, l, mid, r);
//        }
        merge(array, l, mid, r);
    }


    /**
     * 合并两个已经排好序的区间   arr[l, mid]，arr[mid + 1, r]
     *
     * @param array
     * @param l
     * @param mid
     * @param r
     * @param <E>
     */
    private static <E extends Comparable<E>> void merge(E[] array, int l, int mid, int r) {
        E[] tmp = Arrays.copyOfRange(array, l, r + 1);

        int i = l, j = mid + 1;

        // 对两个已经排好序的区间进行归并
        for (int k = l; k <= r; k++) {

            // 如果前半区间已经扫描完毕
            if (i > mid) {
                array[k] = tmp[j - l];   // array 在 l 位置的元素与 tmp 在 0 位置的元素相等
                j++;
            } else if (j > r) {
                array[k] = tmp[i - l];
                i++;
            } else if (tmp[i - l].compareTo(tmp[j - l]) <= 0) {
                array[k] = tmp[i - l];
                i++;
            } else {
                array[k] = tmp[j - l];
                j++;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int n = 100000;

        Integer[] integers = ArrayGenerator.generateRandomArray(n, n);

        SortHelper.sortTest("cn.meetdevelop.merge_sort.MergeSort", integers);

    }
}
