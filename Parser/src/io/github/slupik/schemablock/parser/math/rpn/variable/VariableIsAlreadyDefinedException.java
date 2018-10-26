package io.github.slupik.schemablock.parser.math.rpn.variable;

/**
 * All rights reserved & copyright ©
 */
public class VariableIsAlreadyDefinedException extends Exception {
    public VariableIsAlreadyDefinedException(String name) {
        super("Variable with name " + name + " is already defined.");
    }
}
