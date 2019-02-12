package io.github.slupik.schemablock.newparser.compilator.implementation;

/**
 * All rights reserved & copyright ©
 */
public class Token {
    private final String data;
    private final int line;
    private final int pos;
    private int functionArguments = -1;//When is smaller than 0 then is not a function
    private boolean variable = false;

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

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
        if(variable) {
            functionArguments = -1;
        }
    }

    public int getFunctionArguments() {
        return functionArguments;
    }

    public void setFunctionArguments(int functionArguments) {
        this.functionArguments = functionArguments;
        if(functionArguments>=0) {
            variable = false;
        }
    }

    public boolean isFunction() {
        return getFunctionArguments()>=0;
    }
}
