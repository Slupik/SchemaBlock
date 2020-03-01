package io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.ByteCommandType;

/**
 * All rights reserved & copyright ©
 */
public interface ByteCommand {
    ByteCommandType getCommandType();

    int getLine();
    int getPosition();
}
