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
        ValueType priority = getResultType(a, b);

        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA&&parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "&&");
    }

    static Value or(Value a, Value b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new ValueImpl(BOOLEAN, parsedA||parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "||");
    }

    static Value not(Value a) throws IllegalOperation {
        if(a.getType() == BOOLEAN) {
            boolean parsedA = a.getCastedValue();

            return new ValueImpl(BOOLEAN, !parsedA);
        }
        throw new IllegalOperation(a.getType(), "!");
    }
}
