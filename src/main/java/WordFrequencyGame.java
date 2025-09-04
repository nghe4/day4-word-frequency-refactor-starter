import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr){

        //split the input string with 1 to n pieces of spaces
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);

        if (words.length==1)
            return inputStr + " 1";
        else {
            try {
                List<Input> wordFrequencies = countWordFrequency(words);

                wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return composeOutput(wordFrequencies);
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<Input> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getValue() + " " + wordFrequency.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<Input> countWordFrequency(String[] words) {
        Map<String, List<String>> wordGroups = groupSameWords(words);

        return wordGroups.entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        // ref: https://blog.csdn.net/winterking3/article/details/131671534
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word));
    }


}
