package org.katas.refactoring.wordfrequency;

public class WordCount {
    private final String value;
    private final int count;

    public WordCount(String word, int count) {
        this.value = word;
        this.count = count;
    }

    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }
}
