package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandImpl implements ByteCommand {

    private final int line;
    private final int pos;
    private final ByteCommandType comType;
    private VariableType varType;
    private String varName;
    private int howManyValues;

    public ByteCommandImpl(int line, int pos, ByteCommandType comType){
        this.line = line;
        this.pos = pos;
        this.comType = comType;
    }

    public ByteCommandImpl(int line, int pos, ByteCommandType comType, VariableType varType, String varName){
        this.line = line;
        this.pos = pos;
        this.comType = comType;
        this.varType = varType;
        this.varName = varName;
    }

    public ByteCommandImpl(int line, int pos, ByteCommandType comType, VariableType arrayType, String arrayName, int howManyValues){
        this.line = line;
        this.pos = pos;
        this.comType = comType;
        this.varType = arrayType;
        this.varName = arrayName;
        this.howManyValues = howManyValues;
    }

    @Override
    public ByteCommandType getCommandType() {
        return comType;
    }

    @Override
    public VariableType getVarType() {
        return varType;
    }

    @Override
    public String getVarName() {
        return varName;
    }

    @Override
    public int getHowManyValues() {
        return howManyValues;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getPosition() {
        return pos;
    }
}
