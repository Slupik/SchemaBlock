package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandHeapArray;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandHeapArrayImpl extends ByteCommandBase implements ByteCommandHeapArray {

    private final String name;
    private final int dimensionsCount;

    public ByteCommandHeapArrayImpl(int line, int pos, String name, int dimensionsCount) {
        super(line, pos, ByteCommandType.HEAP_ARRAY);
        this.name = name;
        this.dimensionsCount = dimensionsCount;
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
