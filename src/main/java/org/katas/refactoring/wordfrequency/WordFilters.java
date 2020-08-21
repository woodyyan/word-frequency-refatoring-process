package org.katas.refactoring.wordfrequency;

import java.util.List;
import java.util.stream.Collectors;

public class WordFilters {
    private final List<WordFilter> inFilters;
    private final List<WordFilter> outFilters;

    public WordFilters(List<WordFilter> inFilters, List<WordFilter> outFilters) {
        this.inFilters = inFilters;
        this.outFilters = outFilters;
    }

    public List<WordCount> filter(List<WordCount> wordCounts) {
        if (this.inFilters.size() == 0 && this.outFilters.size() == 0) {
            return wordCounts;
        }

        return wordCounts.stream()
            .filter(wordCount -> inFilters.size() == 0 || inFilters.stream().anyMatch(filter -> filter.match(wordCount.getValue())))
            .filter(wordCount -> outFilters.size() == 0 || outFilters.stream().anyMatch(filter -> filter.match(wordCount.getValue())))
            .collect(Collectors.toList());
    }
}
