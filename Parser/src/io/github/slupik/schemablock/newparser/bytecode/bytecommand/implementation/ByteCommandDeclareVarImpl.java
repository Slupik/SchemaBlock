package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandDeclareVar;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandDeclareVarImpl extends ByteCommandBase implements ByteCommandDeclareVar {

    private final ValueType type;
    private final String name;
    private final int dimensionsCount;

    public ByteCommandDeclareVarImpl(int line, int pos, ValueType type, String name) {
        this(line, pos, type, name, 0);
    }

    public ByteCommandDeclareVarImpl(int line, int pos, ValueType type, String name, int dimensionsCount) {
        super(line, pos, ByteCommandType.DECLARE_VAR);
        this.type = type;
        this.name = name;
        this.dimensionsCount = dimensionsCount;
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDimensionsCount() {
        return dimensionsCount;
    }

    @Override
    public String toString(){
        return getCommandType().toString()+" "+type.toString()+" "+name+" "+dimensionsCount;
    }
}
