package io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public interface ByteCommandDeclareTab extends ByteCommand {
    ValueType getType();
    String getName();
    int getDimensionsCount();
}
