package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.ValueType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapValue;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandHeapValueImpl extends ByteCommandBase implements ByteCommandHeapValue {

    private final ValueType type;
    private final String name;

    public ByteCommandHeapValueImpl(int line, int pos, ValueType type, String name) {
        super(line, pos, ByteCommandType.HEAP_VALUE);
        this.type = type;
        this.name = name;
    }

    @Override
    public ValueType getValueType() {
        return type;
    }

    @Override
    public String getRawValue() {
        return name;
    }
}
