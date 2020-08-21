package org.katas.refactoring.wordfrequency;

public abstract class WordFilter {
    protected final String filter;

    public WordFilter(String filter) {
        this.filter = filter;
    }

    public abstract boolean match(String word);
}
