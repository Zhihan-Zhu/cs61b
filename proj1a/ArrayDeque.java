public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    private final static int R_FACTOR = 2;
    private final static double USAGE_RATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    /** Resizes the underlying array to the target capacity.
     * Directly copy the array values to the new array if the first item if in the front of the last item.
     * Otherwise reorder the items in the new array so that first item is in the front
     * if the last item is in front of the first item. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int indexFirst = indexPlus(nextFirst,1);
        int indexLast = indexMinus(nextLast,1);
        if (indexFirst <= indexLast) {
            System.arraycopy(items, indexFirst, a, 0, size);
        } else {
            System.arraycopy(items, indexFirst, a, 0, items.length - indexFirst);
            System.arraycopy(items, 0, a, items.length - indexFirst,
                    size - (items.length - indexFirst));
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Increase index by i. */
    private int indexPlus(int index, int i) {
        if (index + i >= items.length){
            index = index + i - items.length;
            return index;
        }
        index = index + i;
        return index;
    }

    /** Decrease index by i. */
    private int indexMinus(int index, int i) {
        if (index - i < 0){
            index = items.length + (index - i);
            return index;
        }
        index = index - i;
        return index;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextFirst] = x;
        size = size + 1;
        nextFirst = indexMinus(nextFirst, 1);
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = x;
        size = size + 1;
        nextLast = indexPlus(nextLast, 1);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int indexFirst = indexPlus(nextFirst, 1);
        T removeItem = items[indexFirst];
        items[indexFirst] = null;
        size = size - 1;
        nextFirst = indexFirst;

        while (items.length >= 16 && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }
        return removeItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int indexLast = indexMinus(nextLast, 1);
        T removeItem = items[indexLast];
        items[indexLast] = null;
        size = size - 1;
        nextLast = indexLast;

        while (items.length >= 16 && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }
        return removeItem;
    }

    public T get(int i) {
        if (i >= size || i <0) {
            return null;
        }
        i = indexPlus(nextFirst, i+1);
        return items[i];
    }

    public void printDeque() {
        int i = indexPlus(nextFirst, 1);
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = indexPlus(i, 1);
        }
        System.out.println("");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
//
//    public static void main(String[] args) {
//        ArrayDeque<Integer> A = new ArrayDeque<>();
//        A.printDeque();
//
//        A.addLast(0);
//        A.addFirst(1);
//        System.out.println(A.removeLast());
//        A.printDeque();
//        A.addLast(3);
//        A.addFirst(4);
//        A.addLast(5);
//        A.addFirst(6);
//        A.addLast(7);
//        A.addLast(8);
//        A.printDeque();
//
//        System.out.println(A.get(3));
//        A.addFirst(11);
//        A.addLast(12);
//        A.printDeque();
//
//        System.out.println(A.removeLast());
//        System.out.println(A.removeFirst());
//        A.addFirst(15);
//        A.printDeque();
//
//        System.out.println(A.removeLast());
//        System.out.println(A.removeLast());
//        A.printDeque();
//
//        System.out.println(A.removeFirst());
//        A.printDeque();
//
//        System.out.println(A.removeFirst());
//        A.printDeque();
//
//        System.out.println(A.removeFirst());
//        A.printDeque();
//
//        A.addFirst(21);
//        A.printDeque();
//        System.out.println(A.removeFirst());
//        System.out.println(A.get(2));
//        A.printDeque();
//    }
}
