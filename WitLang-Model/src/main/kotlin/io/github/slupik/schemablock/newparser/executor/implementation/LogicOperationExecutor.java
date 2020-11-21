package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.IllegalOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.BOOLEAN;

/**
 * All rights reserved & copyright ©
 */
class LogicOperationExecutor extends OperationExecutor {

    static SimpleValue and(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA && parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "&&", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue or(SimpleValue a, SimpleValue b, ByteCommandOperation commandOperation) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if (priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA || parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "||", commandOperation.getLine(), commandOperation.getPosition());
    }

    static SimpleValue not(SimpleValue a, ByteCommandOperation commandOperation) throws IllegalOperation {
        if (a.getType() == BOOLEAN) {
            boolean parsedA = a.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, !parsedA);
        }
        throw new IllegalOperation(a.getType(), "!", commandOperation.getLine(), commandOperation.getPosition());
    }
}
