package io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction;

import io.github.slupik.schemablock.newparser.bytecode.ValueType;

/**
 * All rights reserved & copyright ©
 */
public interface ByteCommandHeapValue extends ByteCommand {
    ValueType getValueType();
    String getRawValue();
}
