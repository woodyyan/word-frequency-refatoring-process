package org.katas.refactoring.wordfrequency;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WordFrequencyGameTest {
    @Test
    public void smoke_test() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the the is");
        assertThat(result, is("the 2\nis 1"));
    }
}
