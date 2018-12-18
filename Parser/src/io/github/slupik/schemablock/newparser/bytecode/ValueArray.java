package io.github.slupik.schemablock.newparser.bytecode;

/**
 * All rights reserved & copyright ©
 */
public interface ValueArray extends MemoryableElement {
    int getDimensions();
    void setSingleValue(Object value, int... indexes);
    void setSingleValue(String value, int... indexes);
    Object getValue(int... indexes);
    String getRawValue(int... indexes);

    @Override
    default boolean isArray() {
        return true;
    }
}
