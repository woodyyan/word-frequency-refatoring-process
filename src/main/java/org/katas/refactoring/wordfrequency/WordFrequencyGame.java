package org.katas.refactoring.wordfrequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private final List<WordFilter> wordFilters;

    public WordFrequencyGame(List<WordFilter> wordFilters) {
        this();
        this.wordFilters.addAll(wordFilters);
    }

    public WordFrequencyGame() {
        this.wordFilters = new ArrayList<>();
    }

    public String getResult(String inputStr) {
        try {
            List<WordCount> wordCountList = transferToDomainModel(inputStr);

            wordCountList = filter(wordCountList);

            wordCountList = countWord(wordCountList);

            wordCountList.sort((first, second) -> second.getWordCount() - first.getWordCount());

            return renderResult(wordCountList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordCount> filter(List<WordCount> wordCountList) {
        if (this.wordFilters.size() == 0) {
            return wordCountList;
        }

        List<WordFilter> inFilters = this.wordFilters.stream().filter(WordFilter::isFilterIn).collect(Collectors.toList());
        List<WordFilter> outFilters = this.wordFilters.stream().filter(filter -> !filter.isFilterIn()).collect(Collectors.toList());

        return wordCountList.stream()
            .filter(wordCount -> inFilters.size() == 0 || inFilters.stream().anyMatch(filter -> filter.match(wordCount.getValue())))
            .filter(wordCount -> outFilters.size() == 0 || outFilters.stream().anyMatch(filter -> filter.match(wordCount.getValue())))
            .collect(Collectors.toList());
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
