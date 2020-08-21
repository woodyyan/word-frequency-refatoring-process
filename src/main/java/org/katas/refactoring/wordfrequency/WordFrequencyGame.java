package org.katas.refactoring.wordfrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String inputStr) {
        try {
            List<Input> inputList = transferToDomainModel(inputStr);

            inputList = countWord(inputList);

            inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return renderResult(inputList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<Input> transferToDomainModel(String inputStr) {
        //split the input string with 1 to n pieces of spaces
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }

    private List<Input> countWord(List<Input> inputList) {
        //get the map for the next step of sizing the same word
        Map<String, List<Input>> map = getListMap(inputList);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        return inputList;
    }

    private String renderResult(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : inputList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


    public static void main(String[] args) {
        WordFrequencyGame game = new WordFrequencyGame();

        String result = game.getResult("the the is");

        System.out.println(result);
    }
}
