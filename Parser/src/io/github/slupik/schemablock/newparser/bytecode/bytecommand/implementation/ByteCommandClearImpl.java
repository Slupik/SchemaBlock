package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandClear;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandClearImpl extends ByteCommandBase implements ByteCommandClear {

    private final boolean clearLast;

    public ByteCommandClearImpl(int line, int pos, boolean clearLast) {
        super(line, pos, ByteCommandType.CLEAR_EXEC_HEAP);
        this.clearLast = clearLast;
    }

    @Override
    public boolean clearLast() {
        return clearLast;
    }
}
