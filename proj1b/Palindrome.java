public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }
    public boolean isPalindrome(String word) {
        Boolean status = true;
        Deque<Character> WordD = wordToDeque(word);
        int cnt = WordD.size();
        for (int i = 0; i < cnt / 2; i++) {
            Character head = null, tail = null;
            head = WordD.removeFirst();
            tail = WordD.removeLast();
            if (head != tail && tail != null) {
                status = false;
                break;
            }
        }
        return  status;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {

    }
}
