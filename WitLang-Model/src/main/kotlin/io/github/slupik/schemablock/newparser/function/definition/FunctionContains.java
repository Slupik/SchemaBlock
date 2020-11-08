package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValueImpl;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionContains implements Function {

    @Override
    public String getName() {
        return "contains";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.STRING);
        types.add(FunctionArgType.STRING);
        return types;
    }

    @Override
    public Value execute(List<Value> args, int line, int position) throws AlgorithmException {
        String text1 = ((SimpleValue) args.get(0)).getCastedValue();
        String text2 = ((SimpleValue) args.get(1)).getCastedValue();
        return new SimpleValueImpl(
                ValueType.BOOLEAN,
                StringUtils.contains(text1, text2)
        );
    }

}
