package io.github.slupik.schemablock.newparser.compilator.implementation.compilator;

import io.github.slupik.schemablock.newparser.compilator.implementation.Token;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedTypeOfArray extends CompilationException {

    public ExceptedTypeOfArray(Token token) {
        super(token, "Excepted type of array after keyword \"new\" but not found existing type.");
    }
}
