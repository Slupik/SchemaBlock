package io.github.slupik.schemablock.newparser.executor.implementation;

/**
 * All rights reserved & copyright ©
 */
public class UnknownOperation extends Exception {
    public UnknownOperation(String symbol) {
        super("Unknown operation marked as: "+symbol);
    }
}
