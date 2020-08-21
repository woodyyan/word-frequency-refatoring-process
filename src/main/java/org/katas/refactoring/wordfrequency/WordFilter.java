package org.katas.refactoring.wordfrequency;

public class WordFilter {
    private final String filter;
    private final boolean filterIn;

    public WordFilter(String filter, boolean isFilterIn) {
        this.filter = filter;
        this.filterIn = isFilterIn;
    }

    public boolean isFilterIn() {
        return filterIn;
    }

    public boolean match(String word) {
        if (filterIn) {
            return filter.equals(word);
        } else {
            return !filter.equals(word);
        }
    }
}
