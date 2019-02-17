package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class LogicOperationExecutor extends OperationExecutor {

    static Value and(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA&&parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "&&");
    }

    static Value or(Value a, Value b) throws IllegalOperation {
        ValueType resultType = getResultType(a, b);

        if(resultType == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA||parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "||");
    }
}
