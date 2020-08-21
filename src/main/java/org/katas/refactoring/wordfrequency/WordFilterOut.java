package org.katas.refactoring.wordfrequency;

public class WordFilterOut extends WordFilter {
    public WordFilterOut(String filter) {
        super(filter);
    }

    @Override
    public boolean match(String word) {
        return !filter.equals(word);
    }
}
