public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isFirstEqualLast(d);
    }

    private boolean isFirstEqualLast(Deque<Character> d) {
        if (d.size() <= 1){
            return true;
        }
        char first = d.removeFirst();
        char last = d.removeLast();
        return (first == last) && isFirstEqualLast(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isFirstEqualLast(d, cc);
    }

    private boolean isFirstEqualLast(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1){
            return true;
        }
        char first = d.removeFirst();
        char last = d.removeLast();
        return (cc.equalChars(first, last)) && isFirstEqualLast(d, cc);
    }

}
