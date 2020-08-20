package org.katas.refactoring.wordfrequency;

import org.junit.Ignore;
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

    // split start

    @Test
    public void should_split_input_by_1_space() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the the");
        assertThat(result, is("the 2"));
    }

    @Test
    public void should_split_input_by_n_space() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the is the is");
        assertThat(result, is("the 2\nis 2"));
    }

    @Test
    public void should_split_input_to_one_element_given_one_word() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the");
        assertThat(result, is("the 1"));
    }

    //TODO found a bug
    @Ignore
    @Test
    public void should_space_will_not_be_counted() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the      ");
        assertThat(result, is("the 1"));
    }

    // split end
}
