package io.github.slupik.schemablock.newparser.compilator.exception;

/**
 * All rights reserved & copyright ©
 */
public class ExceptedArrayButNotReceivedException extends Exception {

    public ExceptedArrayButNotReceivedException(){
        super("Excepted array but received only single value");
    }
}
