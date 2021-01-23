package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.IllegalOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class ComparisonOperationExecutor extends OperationExecutor {

    static SimpleValue smallerOrEqual(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA <= parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA <= parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<=", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue greaterOrEqual(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA >= parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA >= parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">=", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue smaller(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA < parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA < parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue greater(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA > parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA > parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue equal(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA == parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA == parsedB);
            }
        }
        if (priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA == parsedB);
        }
        if (priority == STRING) {
            return new SimpleValueImpl(BOOLEAN, String.valueOf(a.getValue()).equals(String.valueOf(b.getValue())));
        }
        throw new IllegalOperation(a.getType(), b.getType(), "==", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue notEqual(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if (priority == DOUBLE || priority == FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new SimpleValueImpl(BOOLEAN, parsedA != parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new SimpleValueImpl(BOOLEAN, parsedA != parsedB);
            }
        }
        if (priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA != parsedB);
        }
        if (priority == STRING) {
            return new SimpleValueImpl(BOOLEAN, !String.valueOf(a.getValue()).equals(String.valueOf(b.getValue())));
        }
        throw new IllegalOperation(a.getType(), b.getType(), "!=", commandOperation.getLine(), commandOperation.getPosition());
    }
}
