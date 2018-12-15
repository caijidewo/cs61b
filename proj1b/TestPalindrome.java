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
    } //Uncomment this class once you've created your Palindrome class.
    @Test
    public void TestPalindrome() {
        String example1 = "racecar";
        assertTrue(palindrome.isPalindrome(example1));
        String example2 = "a";
        assertTrue(palindrome.isPalindrome(example2));
        String example3 = "";
        assertTrue(palindrome.isPalindrome(example3));
        String example4 = "hello";
        assertFalse(palindrome.isPalindrome(example4));
        String example5 = "Abccba";
        assertFalse(palindrome.isPalindrome(example5));
    }
}
