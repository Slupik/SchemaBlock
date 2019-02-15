package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapVariable;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandHeapVariableImpl extends ByteCommandBase implements ByteCommandHeapVariable {

    private final String name;
    private final int indexes;

    public ByteCommandHeapVariableImpl(int line, int pos, String name) {
        this(line, pos, name, 0);
    }

    public ByteCommandHeapVariableImpl(int line, int pos, String name, int indexes) {
        super(line, pos, ByteCommandType.HEAP_VAR);
        this.name = name;
        this.indexes = indexes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIndexes() {
        return indexes;
    }

    @Override
    public String toString(){
        if(indexes==0) {
            return getCommandType().toString()+" "+name;
        } else {
            return getCommandType().toString()+" "+name+" "+indexes;
        }
    }
}
