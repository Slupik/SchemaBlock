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
public class FunctionToTextFromAscii implements Function {

    @Override
    public String getName() {
        return "toTextFromAscii";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.INTEGER);
        return types;
    }

    @Override
    public Value execute(List<Value> args) throws AlgorithmException {
        int code = ((SimpleValue) args.get(0)).getCastedValue();
        char text = (char) code;
        return new SimpleValueImpl(
                ValueType.STRING,
                String.valueOf(text)
        );
    }

}
