package io.github.slupik.schemablock.newparser.memory;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;

/**
 * All rights reserved & copyright ©
 */
public interface IndexesExtractor {

    String extractName(String fullName);
    String[] extractIndexes(String fullName) throws AlgorithmException;

}
