package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.UNKNOWN;

/**
 * All rights reserved & copyright ©
 */
class OperationExecutor {

    protected static final ValueType[] PRIORITY_TYPES = new ValueType[]{STRING, DOUBLE, FLOAT, LONG, INTEGER, SHORT, BYTE};
    protected static ValueType getResultType(Value a, Value b) {
        for(ValueType type:PRIORITY_TYPES) {
            if(isTypeOf(type, a, b)) {
                return type;
            }
        }

        //if is not found
        for(ValueType type:ValueType.values()) {
            if(isTypeOf(type, a, b)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    protected static boolean isTypeOf(ValueType type, Value a, Value b) {
        return a.getType() == type || b.getType() == type;
    }
}
