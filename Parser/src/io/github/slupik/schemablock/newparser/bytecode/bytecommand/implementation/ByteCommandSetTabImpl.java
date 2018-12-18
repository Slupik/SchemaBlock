package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandSetTab;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandSetTabImpl extends ByteCommandBase implements ByteCommandSetTab {

    private final String name;
    private final int dimensionsCount;

    public ByteCommandSetTabImpl(int line, int pos, String name, int dimensionsCount) {
        super(line, pos, ByteCommandType.SET_TAB);
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
