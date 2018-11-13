import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class WordChecker {
    private WordList wordList;
    private ArrayList<String> suggestions;
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


    public WordChecker(WordList wordList) {
        this.wordList = wordList;
    }

    public boolean wordExists(String word) {
        return this.wordList.lookup(word);
    }

    public ArrayList<String> getSuggestions(String word) {
        if (!wordExists(word)) {
            this.suggestions = new ArrayList<String>();
            checkWithSwapping(word);
            checkWithRepleacing(word);
            checkWithDeleting(word);
            checkWithInsert(word);
            checkWithSplit(word);
        }
        return this.suggestions;
    }

    public void checkWithSwapping(String word) {
        for (int i = 0; i < word.length() - 1; i++) {

            char[] wordArr = word.toCharArray();
            char temp = wordArr[i];
            wordArr[i] = wordArr[i + 1];
            wordArr[i + 1] = temp;
            String possibleWord = new String(wordArr).toUpperCase();

            if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                this.suggestions.add(possibleWord);
            }
        }
    }

    public void checkWithRepleacing(String word) {
        for (int i = 0; i < word.length(); i++) {

            for (int j = 0; j < this.alphabet.length; j++) {
                char[] wordArr = word.toCharArray();

                wordArr[i] = this.alphabet[j];
                String possibleWord = new String(wordArr).toUpperCase();
                if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                    this.suggestions.add(possibleWord.toUpperCase());
                }
            }
        }
    }

    public void checkWithDeleting(String word) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            String possibleWord = sb.toString().toUpperCase();
            if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                this.suggestions.add(possibleWord);
            }
        }
    }

    public void checkWithInsert(String word) {
        for (int i = 0; i < word.length() + 1; i++) {

            for (int j = 0; j < this.alphabet.length; j++) {
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, this.alphabet[j]);
                String possibleWord = sb.toString().toUpperCase();
                if (wordExists(possibleWord) && !this.suggestions.contains(possibleWord)) {
                    this.suggestions.add(possibleWord);
                }
            }
        }
    }

    public void checkWithSplit(String word){
        for (int i = 1; i < word.length() - 1; i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.insert(i, " ");
            String[] possibleWords = sb.toString().split(" ", 2);
            String possibleWord1 = possibleWords[0];
            String possibleWord2 = possibleWords[1];
            if (wordExists(possibleWord1) && wordExists(possibleWord2)) {
                String suggestion = Arrays.stream(possibleWords).collect(Collectors.joining(" "));
                this.suggestions.add(suggestion);
            }
        }
    }
}
