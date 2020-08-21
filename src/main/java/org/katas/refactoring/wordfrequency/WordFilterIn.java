package org.katas.refactoring.wordfrequency;

public class WordFilterIn extends WordFilter {
    public WordFilterIn(String filter) {
        super(filter);
    }

    @Override
    public boolean match(String word) {
        return filter.equals(word);
    }
}
