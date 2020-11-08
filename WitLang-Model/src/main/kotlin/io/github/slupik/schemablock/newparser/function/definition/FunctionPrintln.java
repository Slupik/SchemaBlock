package io.github.slupik.schemablock.newparser.function.definition;

import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionArgType;
import io.github.slupik.schemablock.newparser.memory.element.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class FunctionPrintln implements Function {

    @Override
    public String getName() {
        return "println";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.ANY);
        return types;
    }

    @Override
    public Value execute(List<Value> args) {
        String text;
        if (args.get(0).isArray()) {
            text = toString(((Array) args.get(0)));
        } else {
            text = toString(((SimpleValue) args.get(0)));
        }
        System.out.println(text);
        return new SimpleValueImpl(
                ValueType.VOID,
                null
        );
    }

    private String toString(Array array) {
        StringBuilder output = new StringBuilder("[");

        int index = 0;
        for (ArrayCell cell : array.getCells()) {
            if (cell.getValue().isArray()) {
                if (index == 0) {
                    output.append(index).append(": ").append(toString(((Array) cell.getValue())));
                } else {
                    output.append(", ").append(index).append(": ").append(toString(((Array) cell.getValue())));
                }
            } else {
                if (index == 0) {
                    output.append(index).append(": ").append(toString(((SimpleValue) cell.getValue())));
                } else {
                    output.append(", ").append(index).append(": ").append(toString(((SimpleValue) cell.getValue())));
                }
            }
            index++;
        }
        output.append("]");
        return output.toString();
    }

    private String toString(SimpleValue value) {
        return String.valueOf(value.getValue());
    }

}
