package io.github.slupik.schemablock.parser.math.rpn.pattern;

/**
 * All rights reserved & copyright ©
 */
public class InvalidArgumentsException extends Throwable {
    public InvalidArgumentsException(String token) {
        super("Invalid argument for token: "+token);
    }
    public InvalidArgumentsException() {
        super();
    }
}
