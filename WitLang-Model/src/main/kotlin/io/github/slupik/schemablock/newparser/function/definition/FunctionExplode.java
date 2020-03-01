package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.memory.element.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionExplode implements Function {

    @Override
    public String getName() {
        return "explode";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.STRING);
        types.add(FunctionArgType.STRING);
        return types;
    }

    @Override
    public Value execute(List<Value> args) throws AlgorithmException {
        String text = ((SimpleValue) args.get(0)).getCastedValue();
        String delimiter = ((SimpleValue) args.get(1)).getCastedValue();
        String[] parts = StringUtils.split(text, delimiter);
        Array array = new ArrayImpl(
                ValueType.STRING,
                1,
                parts.length
        );
        for(int i=0;i<parts.length;i++) {
            array.setValue(
                    new int[]{i},
                    new SimpleValueImpl(
                            ValueType.STRING,
                            parts[i]
                    )
            );
        }
        return array;
    }

}
