package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.memory.element.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionLength implements Function {

    @Override
    public String getName() {
        return "length";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.STRING);
        return types;
    }

    @Override
    public Value execute(List<Value> args) throws AlgorithmException {
        String text = ((SimpleValue) args.get(0)).getCastedValue();
        return new SimpleValueImpl(
                ValueType.INTEGER,
                text.length()
        );
    }

}
