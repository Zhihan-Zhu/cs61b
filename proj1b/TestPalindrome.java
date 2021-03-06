import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(("racecar")));
        assertTrue(palindrome.isPalindrome(("a")));
    }

    @Test
    public void testisPalindromeOffByOne() {
        CharacterComparator obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
    }
//    @Test
//    public void testisFirstEqualLast() {
//        assertTrue(palindrome.isFirstEqualLast(palindrome.wordToDeque("racecar")));
//    }
    //Uncomment this class once you've created your Palindrome class. */
}
