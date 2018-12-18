package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public interface Value extends MemoryableElement {
    String getRawValue();
    Object getParsedValue();

    @Override
    default boolean isArray() {
        return false;
    }
}
