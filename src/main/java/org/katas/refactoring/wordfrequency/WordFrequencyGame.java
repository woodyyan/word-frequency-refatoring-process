package org.katas.refactoring.wordfrequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public String getResult(String inputStr) {
        try {
            List<WordCount> wordCountList = transferToDomainModel(inputStr);

            wordCountList = countWord(wordCountList);

            wordCountList.sort((first, second) -> second.getWordCount() - first.getWordCount());

            return renderResult(wordCountList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordCount> transferToDomainModel(String input) {
        String[] items = input.trim().split(SPACE);
        List<WordCount> wordCounts = new ArrayList<>();
        for (String item : items) {
            wordCounts.add(new WordCount(item, 1));
        }
        return wordCounts;
    }

    private List<WordCount> countWord(List<WordCount> wordCountList) {
        List<WordCount> wordCounts = new ArrayList<>();
        List<String> words = wordCountList.stream().map(WordCount::getValue).collect(Collectors.toList());
        for (String word : words.stream().distinct().collect(Collectors.toList())) {
            wordCounts.add(new WordCount(word, Collections.frequency(words, word)));
        }
        return wordCounts;
    }

    private String renderResult(List<WordCount> wordCountList) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (WordCount wordCount : wordCountList) {
            String line = wordCount.getValue() + SPACE + wordCount.getWordCount();
            joiner.add(line);
        }
        return joiner.toString();
    }


    public static void main(String[] args) {
        WordFrequencyGame game = new WordFrequencyGame();

        String result = game.getResult("the the is");

        System.out.println(result);
    }
}
