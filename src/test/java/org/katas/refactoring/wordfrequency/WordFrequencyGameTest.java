package org.katas.refactoring.wordfrequency;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void should_space_will_not_be_counted() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the      ");
        assertThat(result, is("the 1"));
    }

    // split end

    // count start

    @Test
    public void should_count_as_1_given_only_one_word() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the");
        assertThat(result, is("the 1"));
    }

    @Test
    public void should_count_same_word_given_only_one_word_repeatedly() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the the the");
        assertThat(result, is("the 3"));
    }

    @Test
    public void should_count_multiple_words_given_multiple_words() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the the is");
        assertThat(result, is("the 2\nis 1"));
    }

    // count end

    // sort start

    @Test
    public void should_sort_result_by_count_number() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("is the the");
        assertThat(result, is("the 2\nis 1"));
    }

    @Test
    public void should_sort_result_when_only_one_word() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("is the");
        assertThat(result, is("is 1\nthe 1"));
    }

    // sort end

    // render start

    @Test
    public void should_render_result_when_only_one_line() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the");
        assertThat(result, is("the 1"));
    }

    @Test
    public void should_render_result_when_multiple_lines() {
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame();
        String result = wordFrequencyGame.getResult("the a is");
        assertThat(result, is("the 1\na 1\nis 1"));
    }

    // render end

    // filter

    @Test
    public void should_count_specific_word_given_filter_in_word_is_the() {
        WordFilterIn filterIn = new WordFilterIn("the");
        WordFilters wordFilters = new WordFilters(Collections.singletonList(filterIn), Collections.emptyList());
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame(wordFilters);
        String result = wordFrequencyGame.getResult("the a is");
        assertThat(result, is("the 1"));
    }

    @Test
    public void should_count_specific_word_given_filter_out_word_is_the() {
        WordFilter filterOut = new WordFilterOut("the");
        WordFilters wordFilters = new WordFilters(Collections.emptyList(), Collections.singletonList(filterOut));
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame(wordFilters);
        String result = wordFrequencyGame.getResult("the a is");
        assertThat(result, is("a 1\nis 1"));
    }

    @Test
    public void should_count_specific_word_given_multiple_filters() {
        WordFilter inFilter1 = new WordFilterIn("the");
        WordFilter inFilter2 = new WordFilterIn("a");
        WordFilter outFilter = new WordFilterOut("a");
        WordFilters wordFilters = new WordFilters(Arrays.asList(inFilter1, inFilter2), Collections.singletonList(outFilter));
        WordFrequencyGame wordFrequencyGame = new WordFrequencyGame(wordFilters);
        String result = wordFrequencyGame.getResult("the a is");
        assertThat(result, is("the 1"));
    }
}
