public class OffByN implements CharacterComparator {
    private int _N;
    OffByN(int N) {
        _N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == _N) || (x - y == -_N);
    }
}
