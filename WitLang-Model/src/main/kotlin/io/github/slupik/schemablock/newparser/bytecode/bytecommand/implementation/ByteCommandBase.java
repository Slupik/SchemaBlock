package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;

/**
 * All rights reserved & copyright ©
 */
public abstract class ByteCommandBase implements ByteCommand {

    private final int line;
    private final int pos;
    private final ByteCommandType comType;

    public ByteCommandBase(int line, int pos, ByteCommandType comType) {
        this.line = line;
        this.pos = pos;
        this.comType = comType;
    }

    @Override
    public final ByteCommandType getCommandType() {
        return comType;
    }

    @Override
    public final int getLine() {
        return line;
    }

    @Override
    public final int getPosition() {
        return pos;
    }
}
