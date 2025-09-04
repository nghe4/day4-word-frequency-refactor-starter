import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        //get the map for the next step of sizing the same word
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(word)){
                ArrayList arr = new ArrayList<>();
                arr.add(word);
                map.put(word, arr);
            }

            else {
                map.get(word).add(word);
            }
        }
        return map;
    }


}
