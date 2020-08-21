package org.katas.refactoring.wordfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE = " ";

    public String getResult(String inputStr) {
        try {
            List<WordCount> wordCountList = transferToDomainModel(inputStr);

            wordCountList = countWord(wordCountList);

            wordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return renderResult(wordCountList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordCount> transferToDomainModelNew(String input) {
        String[] items = input.trim().split(SPACE);
        List<WordCount> wordCounts = new ArrayList<>();
        for (String item : items) {
            wordCounts.add(new WordCount(item, 1));
        }
        return wordCounts;
    }

    private List<WordCount> transferToDomainModel(String inputStr) {
        //split the input string with 1 to n pieces of spaces
        String[] arr = inputStr.split("\\s+");

        List<WordCount> wordCountList = new ArrayList<>();
        for (String s : arr) {
            WordCount wordCount = new WordCount(s, 1);
            wordCountList.add(wordCount);
        }
        return wordCountList;
    }

    private List<WordCount> countWord(List<WordCount> wordCountList) {
        //get the map for the next step of sizing the same word
        Map<String, List<WordCount>> map = getListMap(wordCountList);

        List<WordCount> list = new ArrayList<>();
        for (Map.Entry<String, List<WordCount>> entry : map.entrySet()) {
            WordCount wordCount = new WordCount(entry.getKey(), entry.getValue().size());
            list.add(wordCount);
        }
        wordCountList = list;
        return wordCountList;
    }

    private String renderResult(List<WordCount> wordCountList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordCount w : wordCountList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordCount>> getListMap(List<WordCount> wordCountList) {
        Map<String, List<WordCount>> map = new HashMap<>();
        for (WordCount wordCount : wordCountList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordCount.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordCount);
                map.put(wordCount.getValue(), arr);
            } else {
                map.get(wordCount.getValue()).add(wordCount);
            }
        }
        return map;
    }


    public static void main(String[] args) {
        WordFrequencyGame game = new WordFrequencyGame();

        String result = game.getResult("the the is");

        System.out.println(result);
    }
}
