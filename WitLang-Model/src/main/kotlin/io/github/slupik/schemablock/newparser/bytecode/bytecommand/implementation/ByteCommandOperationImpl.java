package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandOperationImpl extends ByteCommandBase implements ByteCommandOperation {

    private final String symbol;

    public ByteCommandOperationImpl(int line, int pos, String symbol) {
        super(line, pos, ByteCommandType.OPERATION);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return getCommandType().toString() + " " + symbol;
    }
}
