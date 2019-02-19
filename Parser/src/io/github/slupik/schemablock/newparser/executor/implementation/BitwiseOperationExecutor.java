package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.TypeParser;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class BitwiseOperationExecutor extends OperationExecutor {

    static SimpleValue leftShift(SimpleValue a, SimpleValue b) throws IllegalOperation, ValueTooBig {
        ValueType priorityType = getResultType(a, b);

        if(priorityType.IS_NUMBER && priorityType!=DOUBLE && priorityType!=FLOAT) {
            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(a.getType() == BYTE) {
                byte parsedA = nA.byteValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == SHORT) {
                short parsedA = nA.shortValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == INTEGER) {
                int parsedA = nA.intValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == LONG) {
                long parsedA = nA.longValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<<");
    }

    static SimpleValue rightShift(SimpleValue a, SimpleValue b) throws IllegalOperation, ValueTooBig {
        ValueType priorityType = getResultType(a, b);

        if(priorityType.IS_NUMBER && priorityType!=DOUBLE && priorityType!=FLOAT) {
            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(a.getType() == BYTE) {
                byte parsedA = nA.byteValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == SHORT) {
                short parsedA = nA.shortValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == INTEGER) {
                int parsedA = nA.intValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == LONG) {
                long parsedA = nA.longValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">>");
    }

    static SimpleValue and(SimpleValue a, SimpleValue b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER && resultType!=DOUBLE && resultType!=FLOAT) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            long parsedA = nA.longValue();
            long parsedB = nB.longValue();
            return new SimpleValueImpl(resultType, parsedA&parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "&");
    }

    static SimpleValue xor(SimpleValue a, SimpleValue b) throws IllegalOperation, ValueTooBig {
        ValueType priorityType = getResultType(a, b);

        if(priorityType.IS_NUMBER && priorityType!=DOUBLE && priorityType!=FLOAT) {
            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(a.getType() == BYTE) {
                byte parsedA = nA.byteValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
            }
            if(a.getType() == SHORT) {
                short parsedA = nA.shortValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
            }
            if(a.getType() == INTEGER) {
                int parsedA = nA.intValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
            }
            if(a.getType() == LONG) {
                long parsedA = nA.longValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new SimpleValueImpl(TypeParser.getType(String.valueOf(parsedA^parsedB)), parsedA^parsedB);
                }
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "^");
    }

    static SimpleValue or(SimpleValue a, SimpleValue b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType.IS_NUMBER && resultType!=DOUBLE && resultType!=FLOAT) {

            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            long parsedA = nA.longValue();
            long parsedB = nB.longValue();
            return new SimpleValueImpl(resultType, parsedA|parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "|");
    }
    
    static SimpleValue not(SimpleValue a) throws IllegalOperation {
        if(a.getType().IS_NUMBER && a.getType()!=DOUBLE && a.getType()!=FLOAT) {
            Number nA = a.getCastedValue();
            long parsedA = nA.longValue();
            return new SimpleValueImpl(a.getType(), ~parsedA);
        }
        throw new IllegalOperation(a.getType(), "~");
    }
}
