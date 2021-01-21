public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node previous;

        public Node(Node p, T i, Node n ) {
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

    public void addFirst(T x) {
        Node newFirst = new Node(sentinel, x, sentinel.next);
        sentinel.next = newFirst;
        sentinel.next.next.previous = newFirst;
        size += 1;
    }

    public void addLast(T x) {
        Node newLast = new Node(sentinel.previous, x, sentinel);
        sentinel.previous = newLast;
        sentinel.previous.previous.next = newLast;
        size += 1;
    }

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

    public T get(int i) {
        if (i >= size || i <0) {
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
        } else if (i == 0) {
            return sentinel.next.item;
        }
        sentinel = sentinel.next;
        return getRecursive(i-1);
    }

    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    public int size() {
        return size;
    }

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
//    }
}