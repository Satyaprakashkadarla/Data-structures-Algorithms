import java.util.ArrayList;
import java.util.List;

class Solution {
    public String reverseWords(String str) {
        List<String> words = new ArrayList<>();
        String[] splitWords = str.split("\\."); // Split the string by '.'
        
        // Add words to the list
        for (String word : splitWords) {
            words.add(word);
        }
        
        StringBuilder reversed = new StringBuilder();
        
        // Reverse the order of words
        for (int i = words.size() - 1; i >= 0; i--) {
            reversed.append(words.get(i));
            if (i != 0) {
                reversed.append("."); // Append '.' except for the last word
            }
        }
        
        return reversed.toString();
    }
}
