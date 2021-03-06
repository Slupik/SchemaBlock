package io.github.slupik.schemablock.newparser.memory.element;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;

/**
 * All rights reserved & copyright ©
 */
public class VariableImpl implements Variable {

    private final ValueType type;
    private final int dimensions;
    private final String name;

    private Value value;

    public VariableImpl(ValueType type, int dimensions, String name) {
        this.type = type;
        this.dimensions = dimensions;
        this.name = name;
        if (0 == dimensions) {
            value = new SimpleValueImpl(type);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Value getContent() {
        return value;
    }

    @Override
    public void setContent(Value value) throws AlgorithmException {
        if (value != null) {
            if (!ValueType.isCompatible(type, value.getType())) {
                throw new IncompatibleTypeException(type, value.getType());
            }
            if (value.isArray()) {
                if (getDimensionsCount() > 0) {
                    Array array = ((Array) value);
                    if (array.getDimensionsCount() != dimensions) {
                        throw new IncompatibleArrayException(dimensions, array.getDimensionsCount());
                    }
                }
            } else {
                if (dimensions != 0) {
                    throw new ExceptedValue();
                }
            }
            if (value instanceof SimpleValue) {
                Object castedValueContent = ValueConverter.castValueToType(type, ((SimpleValue) value).getValue());
                this.value = new SimpleValueImpl(type, castedValueContent);
            } else {
                this.value = value;
            }
        } else {
            this.value = null;
        }
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public int getDimensionsCount() {
        return dimensions;
    }

}
