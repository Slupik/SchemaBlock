package io.github.slupik.schemablock.newparser.function.definition;

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
public class FunctionPower implements Function {

    @Override
    public String getName() {
        return "power";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.NUMBER);
        types.add(FunctionArgType.NUMBER);
        return types;
    }

    @Override
    public Value execute(List<Value> args, int line, int position) {
        return new SimpleValueImpl(
                ValueType.DOUBLE,
                Math.pow(
                        Double.parseDouble(
                                ((SimpleValue) args.get(0)).getValue().toString()
                        ),
                        Double.parseDouble(
                                ((SimpleValue) args.get(1)).getValue().toString()
                        )
                )
        );
    }

}
