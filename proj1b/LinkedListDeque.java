public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node previous;

        public Node(Node p, T i, Node n) {
            item = i;
            next = n;
            previous = p;
        }
    }

    private Node sentinel;
    private int size;

    /** Create empty Deque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newFirst = new Node(sentinel, x, sentinel.next);
        sentinel.next = newFirst;
        sentinel.next.next.previous = newFirst;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newLast = new Node(sentinel.previous, x, sentinel);
        sentinel.previous = newLast;
        sentinel.previous.previous.next = newLast;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removeItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size -= 1;
        return removeItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removeItem = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size -= 1;
        return removeItem;
    }

    @Override
    public T get(int i) {
        if (i >= size || i < 0) {
            return null;
        }
        Node p = sentinel;
        for (int j = 0; j <= i; j++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int i) {
        if (i >= size || i < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, i);
    }

    private T getRecursiveHelper(Node current, int i) {
        if (i == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, i - 1);
    }

    @Override
    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addFirst(3);
//        L.addFirst(5);
//        L.addLast(6);
//        L.addFirst(9);
//        System.out.println(L.size());
//        System.out.println("L is empty: " + L.isEmpty());
//        L.printDeque();
//        Integer remove = L.removeFirst();
//        System.out.println("\nRemove First: " + remove);
//        System.out.println(L.size());
//        System.out.println("L is empty: " + L.isEmpty());
//        remove = L.removeLast();
//        System.out.println("Remove Last: " + remove);
//        System.out.println(L.size());
//        System.out.println("L is empty: " + L.isEmpty());
//        L.printDeque();
//        Integer getitem = L.get(3);
//        System.out.println("\nGet item: " + getitem);
//        getitem = L.getRecursive(2);
//        System.out.println("\nGet item recursivel: " + getitem);
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addLast(0);
//        L.addLast(2);
//        L.addLast(3);
//        System.out.println(L.getRecursive(2));
//        System.out.println(L.get(0));
//    }
}
