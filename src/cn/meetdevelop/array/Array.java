package cn.meetdevelop.array;

/**
 * description: array
 * date: 2020/11/13 9:49
 * author: zgy
 * version: 1.0
 */
public class Array<E> {

    // 底层的数组结构，用来存放真实数据
    private E[] items;
    private int size;

    public Array(int capacity) {
        items = (E[]) new Object[capacity];
        size = 0;
    }

    // 如果没有指定，默认的容量为 10
    public Array() {
        this(10);
    }


    /**
     * 获得数组中元素的个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获得数组的容量
     *
     * @return
     */
    public int getCapacity() {
        return items.length;
    }

    /**
     * 获得指定索引位置上的元素，通过 get 封装可以对 index 进行二次判断
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Require index >= 0 and index <= size");
        }

        return items[index];
    }


    /**
     * 获得数组中的第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得数组中的最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 对指定索引位置上的元素进行修改
     *
     * @param index
     * @param item
     */
    public void set(int index, E item) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Require index >= 0 and index < size");
        }

        items[index] = item;
    }

    /**
     * 查看数组中是否包含指定元素
     *
     * @param item
     * @return
     */
    public boolean contains(E item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 查找指定元素在数组中的索引
     *
     * @param item
     * @return 不存在返回 -1，存在则返回指定的索引
     */
    public int find(E item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 删除指定位置的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Require index >= 0 and index < size");
        }
        E item = items[index];
        // 从后向前进行覆盖, 这种写法是错误的， 会导致所有的元素都等于最后一个元素
        // for (int i = size - 1; i > index; i--) {
        //     items[i - 1] = items[i];
        // }

        // 从前向后进行覆盖
        for (int i = index + 1; i < size; i++) {
            items[i - 1] = items[i];
        }
        size--;
        // 垃圾回收
        items[size] = null;
        if (size == items.length / 4 && items.length / 2 != 0) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * 删除头部元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除尾部元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定的元素，如果数组中存在多个相同的元素，只会删除第一个位置出现的元素
     *
     * @param item
     * @return 如果删除成功返回 true， 删除失败返回 false
     */
    public boolean removeElement(E item) {
        int index = find(item);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 向头部添加元素
     *
     * @param item
     */
    public void addFirst(E item) {
        add(0, item);
    }


    /**
     * 向尾部添加元素
     *
     * @param item
     */
    public void addLast(E item) {
        add(size, item);
    }

    /**
     * 向指定的索引位置添加元素
     *
     * @param index
     * @param item
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Require index >= 0 and index <= size");
        }
        // 如果数组容量已满，进行扩容操作
        if (size == items.length) {
            resize(2 * items.length);
        }

        // 为了能在 index 处插入元素，需要将 index 处的元素都向后移一位
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = item;
        size++;
    }

    private void resize(int cap) {
        E[] newArray = (E[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("array: size = %d, capacity = %d\n", size, items.length));
        sb.append("front [");

        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 1; i < 20; i++) {
            array.addFirst(i);
            if (i % 3 == 0) {
                System.out.println(array);
            }
        }

        array.add(2, 100);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeFirst();
        array.removeLast();
        System.out.println(array);

        array.removeElement(10);
        System.out.println(array);
    }
}
