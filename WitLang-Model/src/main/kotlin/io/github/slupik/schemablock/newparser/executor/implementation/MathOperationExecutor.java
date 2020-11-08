package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class MathOperationExecutor extends OperationExecutor {

    static SimpleValue add(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if (resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (resultType == DOUBLE || resultType == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(resultType, parsedA + parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(resultType, parsedA + parsedB);
            }
        }
        if (resultType == STRING) {
            return new SimpleValueImpl(resultType, String.valueOf(a.getValue()) + b.getValue());
        }
        throw new IllegalOperation(a.getType(), b.getType(), "+", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue subtract(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if (resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (resultType == DOUBLE || resultType == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(resultType, parsedA - parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(resultType, parsedA - parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "-", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue multiply(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if (resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (resultType == DOUBLE || resultType == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(resultType, parsedA * parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(resultType, parsedA * parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "*", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue divide(SimpleValue a, SimpleValue b, boolean rest, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if (resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (resultType == DOUBLE || resultType == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                double result = parsedA / parsedB;
                if (rest) {
                    return new SimpleValueImpl(resultType, result);
                } else {
                    return new SimpleValueImpl(resultType, ((long) result));
                }
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                long result = parsedA / parsedB;
                return new SimpleValueImpl(resultType, result);
            }
        }
        if (rest) {
            throw new IllegalOperation(a.getType(), b.getType(), "/", commandOperation.getLine(), commandOperation.getPosition());
        } else {
            throw new IllegalOperation(a.getType(), b.getType(), "\\", commandOperation.getLine(), commandOperation.getPosition());
        }
    }

    static SimpleValue modulo(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if (resultType.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (resultType == DOUBLE || resultType == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(resultType, parsedA % parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(resultType, parsedA % parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "%", commandOperation.getLine(), commandOperation.getPosition());
    }
}
