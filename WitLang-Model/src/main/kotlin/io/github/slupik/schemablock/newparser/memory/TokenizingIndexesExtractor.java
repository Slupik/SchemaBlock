package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.executor.Executor;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
/*
TODO !!!!
Zrobić z tego IndexTokenizer
pobierać listę String z kodem który kompiluje się na wartości indexów
W NewHeapSpy dorzucić Executora i stamtąd brać result (wartości indexów ze stringu)
Dorobić logikę używania indexów

 */
public class TokenizingIndexesExtractor implements IndexesExtractor {

    private static final char INDEX_OPEN_CHAR = '[';
    private static final char INDEX_CLOSE_CHAR = ']';

    private final Executor executor;

    public TokenizingIndexesExtractor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public String extractName(String fullName) {
        int positionOfIndexesOpen = fullName.indexOf(INDEX_OPEN_CHAR);
        if (positionOfIndexesOpen < 0) {
            return fullName;
        }
        return fullName.substring(0, positionOfIndexesOpen);
    }

    @Override
    public int[] extractIndexes(String fullName) throws AlgorithmException {
        List<Integer> indexes = new ArrayList<>();
        String indexesPart = fullName.substring(fullName.indexOf(INDEX_OPEN_CHAR, fullName.length()));
        int nestLevel = 0;
        StringBuilder singleIndex = new StringBuilder();
        for (int i = 0; i < indexesPart.length(); i++) {
            char currentChar = indexesPart.charAt(i);
            if (INDEX_OPEN_CHAR == currentChar) {
                nestLevel++;
                if (nestLevel == 1) {
                    singleIndex = new StringBuilder();
                } else {
                    singleIndex.append(currentChar);
                }
            } else if (INDEX_CLOSE_CHAR == currentChar) {
                nestLevel--;
                if (nestLevel == 0) {
                    indexes.add(calculate(singleIndex.toString()));
                } else {
                    singleIndex.append(currentChar);
                }
            } else {
                if (nestLevel < 1) {
                    int currentPositionInLine = fullName.indexOf(INDEX_OPEN_CHAR) + i;
                    throw new UnexpectedCharBetweenIndexes(0, currentPositionInLine, currentChar);
                }
                singleIndex.append(currentChar);
            }
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }

    private Integer calculate(String singleIndex) throws AlgorithmException {
        System.out.println("singleIndex = " + singleIndex);
        return executor.getResult(singleIndex).getCastedValue();
    }

}
