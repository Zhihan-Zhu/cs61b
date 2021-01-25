public interface Deque<T> {
    void addFirst(T x);

    void addLast(T x);

    T removeFirst();

    T removeLast();

    T get(int i);

    void printDeque();

    int size();

    boolean isEmpty();
}
