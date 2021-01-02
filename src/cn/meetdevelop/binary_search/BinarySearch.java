package cn.meetdevelop.binary_search;

/**
 * Author:zgy
 * Date:2021/1/1
 */
public class BinarySearch {

    /**
     * 最普通的二分搜索，在 items 中查找特定元素 item
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int search(E[] items, E item) {
        // 循环不变量 [L, R]
        int L = 0, R = items.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);

            if (items[mid].compareTo(item) == 0) {
                return mid;
            } else if (items[mid].compareTo(item) < 0) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分搜索的递归写法
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int searchR(E[] items, E item) {
        return searchR(items, item, 0, items.length - 1);
    }

    private static <E extends Comparable<E>> int searchR(E[] items, E item, int L, int R) {

        if (L > R) return -1;

        int mid = L + ((R - L) >> 1);

        if (items[mid].compareTo(item) < 0) {
            return searchR(items, item, mid + 1, R);
        } else if (items[mid].compareTo(item) > 0) {
            return searchR(items, item, L, mid - 1);
        }
        return mid;
    }


    public static <E extends Comparable<E>> int search_1(E[] items, E item) {
        // 循环不变量 [L, R)
        int L = 0, R = items.length;

        while (L < R) {
            int mid = L + ((R - L) >> 1);

            if (items[mid].compareTo(item) == 0) {
                return mid;
            } else if (items[mid].compareTo(item) < 0) {
                L = mid + 1;  // 继续在 [mid + 1, R) 的范围内进行查找
            } else {
                R = mid;
            }
        }
        return -1;
    }


    /**
     * 在 items 中查找到大于 item 的最小元素索引
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int upper(E[] items, E item) {
        // 索引范围为 [L, R)
        int L = 0, R = items.length;

        while (L < R) {
            int mid = L + ((R - L) >> 1);

            if (items[mid].compareTo(item) > 0) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }

        return L;
    }

    /**
     * 当数组中包含元素 item 时，返回相同元素的最大索引
     * 当数组中不包含元素 items 时，返回第一个大于它的元素索引
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int upper_ceil(E[] items, E item) {

        int index = upper(items, item);

        if (index - 1 >= 0 && items[index - 1].equals(item)) {
            return index - 1;
        }

        return index;
    }


    /**
     * 如果数组中包含元素 item，那么返回相等元素的最小索引
     * 如果数组中不包含元素 item，返回 upper
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int lower_ceil(E[] items, E item) {
        int L = 0, R = items.length;

        while (L < R) {

            int mid = L + ((R - L) >> 1);

            if (items[mid].compareTo(item) < 0) {
                L = mid + 1;
            } else if (items[mid].compareTo(item) >= 0) {
                R = mid;
            }
        }

        return L;

    }

    /**
     * 当 items 中包含元素 item 时，返回其最小索引
     * 当 items 中不包含元素 item 时，返回 lower
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int lower_floor(E[] items, E item) {
        int index = lower(items, item);

        if (index + 1 < items.length && items[index + 1].equals(item)) {
            return index + 1;
        }
        return index;
    }

    /**
     * 返回小于等于 item 的最大索引
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int upper_floor(E[] items, E item) {
        int L = -1, R = items.length - 1;

        while (L < R) {

            int mid = L + ((R - L + 1) >> 1);

            if (items[mid].compareTo(item) > 0) {
                R = mid - 1;
            } else {
                L = mid;
            }
        }

        return L;
    }


    /**
     * 查找小于 item 的最大值
     *
     * @param items
     * @param item
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> int lower(E[] items, E item) {
        int L = -1, R = items.length - 1;

        while (L < R) {
            int mid = L + ((R - L + 1) >> 1);

            if (items[mid].compareTo(item) >= 0) {
                R = mid - 1;
            } else {
                L = mid;
            }

        }

        return L;

    }

    public static void main(String[] args) {
        Integer[] items = {1, 1, 3, 3, 5, 5};

        System.out.println("upper:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(upper(items, i) + " ");
        }
        System.out.println();
        System.out.println("upper_ceil:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(upper_ceil(items, i) + " ");
        }

        System.out.println();
        System.out.println("lower_ceil:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(lower_ceil(items, i) + " ");
        }

        System.out.println();
        System.out.println("lower:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(lower(items, i) + " ");
        }

        System.out.println();
        System.out.println("lower_floor:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(lower_floor(items, i) + " ");
        }

        System.out.println();
        System.out.println("upper_floor:");
        for (int i = 0; i <= 6; i++) {
            System.out.print(upper_floor(items, i) + " ");
        }


    }
}
