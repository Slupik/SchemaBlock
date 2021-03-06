package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedTypeOfArray extends CompilationException {

    public ExceptedTypeOfArray(Token token) {
        super(token, "Excepted type of array after keyword \"new\" but not found existing type.");
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.TYPE_OF_ARRAY_AFTER_KEYWORD_NEW_NOT_FOUND;
    }

}
