package io.github.slupik.schemablock.newparser.executor.exception;

/**
 * All rights reserved & copyright ©
 */
public class IncompatibleArrayException extends Exception {

    private final int excepted;
    private final int actual;

    public IncompatibleArrayException(int excepted, int actual) {
        super("Excepted array with "+excepted+" dimensions but received "+actual);
        this.excepted = excepted;
        this.actual = actual;
    }

    public int getExcepted() {
        return excepted;
    }

    public int getActual() {
        return actual;
    }

}
