package io.github.slupik.schemablock.newparser.bytecode.bytecommand.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandSetVar;

/**
 * All rights reserved & copyright ©
 */
public class ByteCommandSetVarImpl extends ByteCommandBase implements ByteCommandSetVar {

    private final String name;

    public ByteCommandSetVarImpl(int line, int pos, String name) {
        super(line, pos, ByteCommandType.SET_VAR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
