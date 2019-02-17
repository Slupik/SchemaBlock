package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.ValueType;

/**
 * All rights reserved & copyright ©
 */
public class IllegalOperation extends Exception {
    public IllegalOperation(ValueType type1, ValueType type2, String operation) {
        super("Cannot end operation '"+operation+"' for values with types: "+type1+" and "+type2);
    }

    public IllegalOperation(ValueType type, String operation) {
        super("Cannot end operation '"+operation+"' for values with types: "+type);
    }
}
