package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapVariable;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandHeapVariableImpl extends ByteCommandBase implements ByteCommandHeapVariable {

    private final String name;

    public ByteCommandHeapVariableImpl(int line, int pos, String name) {
        super(line, pos, ByteCommandType.HEAP_VAR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
