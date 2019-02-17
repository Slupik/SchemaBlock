package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import io.github.slupik.schemablock.newparser.utils.TypeParser;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class BitwiseOperationExecutor extends OperationExecutor {

    static Value leftShift(Value a, Value b) throws IllegalOperation, ValueTooBig {
        ValueType priorityType = getResultType(a, b);

        if(priorityType.IS_NUMBER && priorityType!=DOUBLE && priorityType!=FLOAT) {
            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(a.getType() == BYTE) {
                byte parsedA = nA.byteValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == SHORT) {
                short parsedA = nA.shortValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == INTEGER) {
                int parsedA = nA.intValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
            if(a.getType() == LONG) {
                long parsedA = nA.longValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA<<parsedB)), parsedA<<parsedB);
                }
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), "<<");
    }

    static Value rightShift(Value a, Value b) throws IllegalOperation, ValueTooBig {
        ValueType priorityType = getResultType(a, b);

        if(priorityType.IS_NUMBER && priorityType!=DOUBLE && priorityType!=FLOAT) {
            Number nA = a.getCastedValue();
            Number nB = b.getCastedValue();

            if(a.getType() == BYTE) {
                byte parsedA = nA.byteValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == SHORT) {
                short parsedA = nA.shortValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == INTEGER) {
                int parsedA = nA.intValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
            if(a.getType() == LONG) {
                long parsedA = nA.longValue();
                if(b.getType() == BYTE) {
                    byte parsedB = nB.byteValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == SHORT) {
                    short parsedB = nB.shortValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == INTEGER) {
                    int parsedB = nB.intValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
                if(b.getType() == LONG) {
                    long parsedB = nB.longValue();
                    return new ValueImpl(TypeParser.getType(String.valueOf(parsedA>>parsedB)), parsedA>>parsedB);
                }
            }
        }
        throw new IllegalOperation(a.getType(), b.getType(), ">>");
    }
}
