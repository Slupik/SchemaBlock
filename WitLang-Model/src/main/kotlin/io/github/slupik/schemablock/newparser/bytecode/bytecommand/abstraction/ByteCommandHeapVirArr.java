package io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public interface ByteCommandHeapVirArr extends ByteCommand {
    ValueType getType();

    int getElementsCount();

    boolean isEmpty();
}
