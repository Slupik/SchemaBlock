package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
class LogicOperationExecutor extends OperationExecutor {

    static SimpleValue and(SimpleValue a, SimpleValue b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA&&parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "&&");
    }

    static SimpleValue or(SimpleValue a, SimpleValue b) throws IllegalOperation {
        ValueType priority = getResultType(a, b);

        if(priority == BOOLEAN) {
            boolean parsedA = a.getCastedValue();
            boolean parsedB = b.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, parsedA||parsedB);
        }
        throw new IllegalOperation(a.getType(), b.getType(), "||");
    }

    static SimpleValue not(SimpleValue a) throws IllegalOperation {
        if(a.getType() == BOOLEAN) {
            boolean parsedA = a.getCastedValue();

            return new SimpleValueImpl(BOOLEAN, !parsedA);
        }
        throw new IllegalOperation(a.getType(), "!");
    }
}
