package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public interface IndexesExtractor {

    String extractName(String fullName);
    int[] extractIndexes(String fullName) throws AlgorithmException;

}
