package io.github.slupik.schemablock.newparser.compilator.implementation;

/**
 * All rights reserved & copyright ©
 */
public class SpecialToken extends Token {

    SpecialToken(Token token) {
        super(token.getData(), token.getLine(), token.getPos());
    }

    @Override
    public boolean isSpecialToken() {
        return true;
    }
}
