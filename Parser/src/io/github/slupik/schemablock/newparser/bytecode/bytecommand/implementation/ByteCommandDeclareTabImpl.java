package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandDeclareTab;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandDeclareTabImpl extends ByteCommandBase implements ByteCommandDeclareTab {

    private final ValueType type;
    private final String name;
    private final int dimensionsCount;

    public ByteCommandDeclareTabImpl(int line, int pos, ValueType type, String name, int dimensionsCount) {
        super(line, pos, ByteCommandType.DECLARE_TAB);
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
