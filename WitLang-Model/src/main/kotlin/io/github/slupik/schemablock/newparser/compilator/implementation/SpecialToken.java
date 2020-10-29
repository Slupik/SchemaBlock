package io.github.slupik.schemablock.newparser.compilator.implementation;

/**
 * All rights reserved & copyright ©
 */
public class SpecialToken extends Token {

    SpecialToken(Token token) {
        super(token.getData(), token.getLine(), token.getPos());
    }

    SpecialToken(String message, int line, int pos) {
        super(message, line, pos);
    }

    @Override
    public boolean isSpecialToken() {
        return true;
    }
}
