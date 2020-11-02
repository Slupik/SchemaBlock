package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandExecute;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandExecuteImpl extends ByteCommandBase implements ByteCommandExecute {

    private final String name;
    private final int argsCount;

    public ByteCommandExecuteImpl(int line, int pos, String name, int argsCount) {
        super(line, pos, ByteCommandType.EXECUTE);
        this.name = name;
        this.argsCount = argsCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getArgsCount() {
        return argsCount;
    }

    @Override
    public String toString() {
        return getCommandType().toString() + " " + name + " " + argsCount;
    }
}
