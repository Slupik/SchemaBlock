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
public class FunctionAbs implements Function {

    @Override
    public String getName() {
        return "abs";
    }

    @Override
    public List<FunctionArgType> getArgumentsType() {
        List<FunctionArgType> types = new ArrayList<>();
        types.add(FunctionArgType.NUMBER);
        return types;
    }

    @Override
    public Value execute(List<Value> args, int line, int position) {
        SimpleValue arg = ((SimpleValue) args.get(0));
        switch (arg.getType()) {
            case SHORT: {
                return new SimpleValueImpl(
                        ValueType.SHORT,
                        Math.abs(
                                Short.parseShort(
                                        arg.getValue().toString()
                                )
                        )
                );
            }
            case INTEGER: {
                return new SimpleValueImpl(
                        ValueType.INTEGER,
                        Math.abs(
                                Integer.parseInt(
                                        arg.getValue().toString()
                                )
                        )
                );
            }
            case LONG: {
                return new SimpleValueImpl(
                        ValueType.LONG,
                        Math.abs(
                                Long.parseLong(
                                        arg.getValue().toString()
                                )
                        )
                );
            }
            case FLOAT: {
                return new SimpleValueImpl(
                        ValueType.FLOAT,
                        Math.abs(
                                Float.parseFloat(
                                        arg.getValue().toString()
                                )
                        )
                );
            }
            default: {
                return new SimpleValueImpl(
                        ValueType.DOUBLE,
                        Math.abs(
                                Double.parseDouble(
                                        arg.getValue().toString()
                                )
                        )
                );
            }
        }
    }

}
