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
public class FunctionToAscii implements Function {

    @Override
    public String getName() {
        return "toAscii";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.STRING);
        return types;
    }

    @Override
    public Value execute(List<Value> args, int line, int position) throws AlgorithmException {
        String text = ((SimpleValue) args.get(0)).getCastedValue();
        if (text.length() == 0) {
            return new SimpleValueImpl(
                    ValueType.INTEGER,
                    0
            );
        } else if (text.length() == 1) {
            char letter = text.charAt(0);
            return new SimpleValueImpl(
                    ValueType.INTEGER,
                    ((int) letter)
            );
        } else {
            Array array = new ArrayImpl(
                    ValueType.INTEGER,
                    1,
                    text.length()
            );
            for (int i = 0; i < text.length(); i++) {
                char letter = text.charAt(0);
                array.setValue(
                        new int[]{i},
                        new SimpleValueImpl(
                                ValueType.INTEGER,
                                ((int) letter)
                        )
                );
            }
            return array;
        }
    }

}
