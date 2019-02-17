package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class MathOperationExecutor {

    static Value add(Value a, Value b) {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType == DOUBLE) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
            if(resultType == FLOAT) {
                float parsedA = nA.floatValue();
                float parsedB = nB.floatValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
            if(resultType == LONG) {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
            if(resultType == INTEGER) {
                int parsedA = nA.intValue();
                int parsedB = nB.intValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
            if(resultType == SHORT) {
                short parsedA = nA.shortValue();
                short parsedB = nB.shortValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
            if(resultType == BYTE) {
                byte parsedA = nA.byteValue();
                byte parsedB = nB.byteValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
        }
        return null;
    }

//    static Value subtract(Value a, Value b) {
//
//    }

    private static ValueType getResultType(Value a, Value b) {
        ValueType[] numberTypes = new ValueType[]{DOUBLE, FLOAT, LONG, INTEGER, SHORT, BYTE};
        for(ValueType type:numberTypes) {
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

    private static boolean isTypeOf(ValueType type, Value a, Value b) {
        return a.getType() == type || b.getType() == type;
    }
}
