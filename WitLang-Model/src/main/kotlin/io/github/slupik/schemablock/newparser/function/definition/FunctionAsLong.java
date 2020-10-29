package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.function.exception.CannotParseData;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionAsLong implements Function {

    @Override
    public String getName() {
        return "asLong";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.ANY);
        return types;
    }

    @Override
    public Value execute(List<Value> args) throws AlgorithmException {
        Object value = ((SimpleValue) args.get(0)).getValue();
        try {
            return new SimpleValueImpl(
                    ValueType.LONG,
                    Long.parseLong(value.toString())
            );
        } catch (Exception e) {
            throw new CannotParseData(value, ValueType.LONG);
        }
    }

}
