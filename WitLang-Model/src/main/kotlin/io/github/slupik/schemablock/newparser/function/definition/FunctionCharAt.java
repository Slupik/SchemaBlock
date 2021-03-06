package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionCharAt implements Function {

    @Override
    public String getName() {
        return "charAt";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.STRING);
        types.add(FunctionArgType.INTEGER);
        return types;
    }

    @Override
    public Value execute(List<Value> args, int line, int position) throws AlgorithmException {
        String text = ((SimpleValue) args.get(0)).getCastedValue();
        Integer index = ((SimpleValue) args.get(1)).getCastedValue();
        char letter = text.charAt(index);
        return new SimpleValueImpl(
                ValueType.STRING,
                String.valueOf(letter)
        );
    }

}
