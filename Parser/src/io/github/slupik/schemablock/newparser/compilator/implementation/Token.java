package io.github.slupik.schemablock.newparser.compilator.implementation;

/**
 * All rights reserved & copyright ©
 */
class Token {
    private final String data;
    private final int line;
    private final int pos;

    Token(String data, int line, int pos) {
        this.data = data;
        this.line = line;
        this.pos = pos;
    }

    public String getData() {
        return data;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

}
