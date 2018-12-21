package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapVirArr;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandHeapVirArrImpl extends ByteCommandBase implements ByteCommandHeapVirArr {

    private final ValueType type;
    private final String name;
    private final int dimensionsCount;

    public ByteCommandHeapVirArrImpl(int line, int pos, ValueType type, String name) {
        this(line, pos, type, name, 0);
    }

    public ByteCommandHeapVirArrImpl(int line, int pos, ValueType type, String name, int dimensionsCount) {
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
}
