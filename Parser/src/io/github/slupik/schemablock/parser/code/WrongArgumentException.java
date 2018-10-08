package io.github.slupik.schemablock.parser.code;

/**
 * All rights reserved & copyright ©
 */
public class WrongArgumentException extends Exception {
    public WrongArgumentException(String excepted, String received) {
        super("Excepted "+excepted+" but received "+received);
    }
}
