package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TokenizingIndexesExtractor implements IndexesExtractor {

    private static final char INDEX_OPEN_CHAR = '[';
    private static final char INDEX_CLOSE_CHAR = ']';

    @Inject
    public TokenizingIndexesExtractor() {
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
    public String[] extractIndexes(String fullName) throws AlgorithmException {
        final int startOfIndexesPart = fullName.indexOf(INDEX_OPEN_CHAR);
        if (fullName.length() == 0 || startOfIndexesPart < 0) {
            return new String[0];
        }
        List<String> indexes = new ArrayList<>();
        String indexesPart = fullName.substring(startOfIndexesPart);
        int nestLevel = 0;
        StringBuilder singleIndex = new StringBuilder();
        for (int i = 0; i < indexesPart.length(); i++) {
            char currentChar = indexesPart.charAt(i);
            int currentPositionInLine = startOfIndexesPart + i;
            if (INDEX_OPEN_CHAR == currentChar) {
                nestLevel++;
                if (nestLevel == 1) {
                    if (singleIndex.length() > 0) {
                        throw new ExceptedEndOfIndex(0, currentPositionInLine);
                    }
                } else {
                    singleIndex.append(currentChar);
                }
            } else if (INDEX_CLOSE_CHAR == currentChar) {
                nestLevel--;
                if (nestLevel == 0) {
                    indexes.add(singleIndex.toString());
                    singleIndex = new StringBuilder();
                } else {
                    singleIndex.append(currentChar);
                }
            } else {
                if (nestLevel < 1) {
                    throw new UnexpectedCharBetweenIndexes(0, currentPositionInLine, currentChar);
                }
                singleIndex.append(currentChar);
            }
        }
        return indexes.toArray(new String[0]);
    }

}
