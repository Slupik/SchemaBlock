package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;
import static io.github.slupik.schemablock.newparser.memory.element.ValueType.BOOLEAN;

/**
 * All rights reserved & copyright ©
 */
class ComparisonOperationExecutor extends OperationExecutor {

    static Value smallerOrEqual(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA<=parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA<=parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<=");
    }

    static Value greaterOrEqual(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA>=parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA>=parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">=");
    }

    static Value smaller(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA<parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA<parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<");
    }

    static Value greater(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA>parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA>parsedB);
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">");
    }

    static Value equal(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA==parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA==parsedB);
            }
        }
        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA==parsedB);
        }
        if(priority == STRING) {
            return new ValueImpl(BOOLEAN, String.valueOf(a.getValue()).equals(String.valueOf(b.getValue())));
        }
        throw new IllegalOperation(a.getType(), b.getType(), "==");
    }

    static Value notEqual(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority.IS_NUMBER) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(priority==DOUBLE || priority==FLOAT) {
                double parsedA = nA.doubleValue();
                double parsedB = nB.doubleValue();
                return new ValueImpl(BOOLEAN, parsedA!=parsedB);
            } else {
                long parsedA = nA.longValue();
                long parsedB = nB.longValue();
                return new ValueImpl(BOOLEAN, parsedA!=parsedB);
            }
        }
        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA!=parsedB);
        }
        if(priority == STRING) {
            return new ValueImpl(BOOLEAN, !String.valueOf(a.getValue()).equals(String.valueOf(b.getValue())));
        }
        throw new IllegalOperation(a.getType(), b.getType(), "!=");
    }
}
