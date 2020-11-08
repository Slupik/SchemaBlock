package io.github.slupik.schemablock.newparser.compilator.exception;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedMoreDimensionsThanExists extends Exception {

    private final int exists;
    private final int excepted;

    public ExceptedMoreDimensionsThanExists(int exists, int excepted) {
        super("Excepted array with " + excepted + " dimensions but exists only " + exists);
        this.exists = exists;
        this.excepted = excepted;
    }

    public int getExists() {
        return exists;
    }

    public int getExcepted() {
        return excepted;
    }

}
