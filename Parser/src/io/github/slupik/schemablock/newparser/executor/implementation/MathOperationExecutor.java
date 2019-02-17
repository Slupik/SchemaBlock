package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class MathOperationExecutor {

    static Value add(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType==DOUBLE || resultType==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(resultType, parsedA+parsedB);
            }
        }
        if(resultType == STRING) {
            return new ValueImpl(resultType, String.valueOf(a.getValue())+String.valueOf(b.getValue()));
        }
        throw new IllegalOperation(a.getType(), b.getType(), "+");
    }

    static Value subtract(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType==DOUBLE || resultType==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(resultType, parsedA-parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(resultType, parsedA-parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "-");
    }

    static Value multiply(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType==DOUBLE || resultType==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(resultType, parsedA*parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(resultType, parsedA*parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "*");
    }

    static Value divide(Value a, Value b, boolean rest) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType==DOUBLE || resultType==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                double result = parsedA/parsedB;
                if(rest) {
                    return new ValueImpl(resultType, result);
                } else {
                    return new ValueImpl(resultType, ((long) result));
                }
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                long result = parsedA/parsedB;
                return new ValueImpl(resultType, result);
            }
        }
        if(rest) {
            throw new IllegalOperation(a.getType(), b.getType(), "/");
        } else {
            throw new IllegalOperation(a.getType(), b.getType(), "\\");
        }
    }

    static Value modulo(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(resultType==DOUBLE || resultType==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(resultType, parsedA%parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(resultType, parsedA%parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "%");
    }

    private static final ValueType[] PRIORITY_TYPES = new ValueType[]{DOUBLE, FLOAT, LONG, INTEGER, SHORT, BYTE, STRING};
    private static ValueType getResultType(Value a, Value b) {
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

    private static boolean isTypeOf(ValueType type, Value a, Value b) {
        return a.getType() == type || b.getType() == type;
    }
}
