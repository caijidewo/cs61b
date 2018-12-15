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
        Deque<Character> wordD = wordToDeque(word);
        int cnt = wordD.size();
        for (int i = 0; i < cnt / 2; i++) {
            Character head = null, tail = null;
            head = wordD.removeFirst();
            tail = wordD.removeLast();
            if (head != tail && tail != null) {
                status = false;
                break;
            }
        }
        return  status;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        boolean status = true;
        Deque<Character> wordD = wordToDeque(word);
        for (int i = 0; i < wordD.size() / 2; i++) {
            if (!cc.equalChars(wordD.get(i), wordD.get(wordD.size() - 1 - i))) {
                status = false;
                break;
            }
        }
        return  status;
    }
}
