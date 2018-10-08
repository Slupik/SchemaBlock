package io.github.slupik.schemablock.parser.code;

/**
 * All rights reserved & copyright ©
 */
public class VariableNotFound extends Exception {
    public VariableNotFound(String varName) {
        super("Variable with name "+varName+" doesn't exists");
    }
}
