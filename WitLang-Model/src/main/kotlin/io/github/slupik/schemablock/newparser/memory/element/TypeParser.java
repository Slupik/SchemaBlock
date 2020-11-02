package io.github.slupik.schemablock.newparser.memory.element;

/**
 * All rights reserved & copyright ©
 */
class TypeParser<V> {

    private final V val;

    TypeParser(V val) {
        this.val = val;
    }

    <T> T get(Class<T> clazz) {
        return clazz.cast(val);
    }
}
