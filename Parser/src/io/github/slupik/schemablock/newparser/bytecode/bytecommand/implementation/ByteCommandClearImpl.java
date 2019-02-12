package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandClear;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandClearImpl extends ByteCommandBase implements ByteCommandClear {

    private final boolean clearOnlyLast;

    public ByteCommandClearImpl(int line, int pos, boolean clearOnlyLast) {
        super(line, pos, ByteCommandType.CLEAR_EXEC_HEAP);
        this.clearOnlyLast = clearOnlyLast;
    }

    @Override
    public boolean clearOnlyLast() {
        return clearOnlyLast;
    }

    @Override
    public String toString(){
        return getCommandType().toString();
    }
}
