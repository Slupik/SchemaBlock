package io.github.slupik.schemablock.newparser.function;

import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction;
import io.github.slupik.schemablock.newparser.memory.element.Value;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class DefaultFunctionExecutor implements FunctionExecutor {

    @Override
    public Value execute(List<Function> availableFunctions, List<Value> args) throws NoMatchingFunction {
        //TODO add exceptions:
        // - no enough arguments
        // - too many arguments
        // - type of argument not match
        for (Function function : availableFunctions) {
            if (function.getArgumentsType().size() == args.size()) {
                boolean isCompatible = true;
                for (int i = 0; i < args.size(); i++) {
                    Value arg = args.get(i);
                    if (!isArgumentMatch(arg, function.getArgumentsType().get(i))) {
                        isCompatible = false;
                        break;
                    }
                }
                if (isCompatible) {
                    return function.execute(args);
                }
            }
        }
        throw new NoMatchingFunction();
    }

    private boolean isArgumentMatch(Value value, FunctionArgType argType) {
        //TODO simplify (remove array enums)
        ValueType valueType = value.getType();
        if (value.isArray()) {
            if(argType == FunctionArgType.NUMBER_ARRAY) {
                return valueType.IS_NUMBER;
            } else if (argType == FunctionArgType.INTEGER_ARRAY) {
                return (valueType == ValueType.BYTE || valueType == ValueType.SHORT || valueType == ValueType.INTEGER || valueType == ValueType.LONG);
            } else if (argType == FunctionArgType.DECIMAL_ARRAY) {
                return (valueType == ValueType.DOUBLE || valueType == ValueType.FLOAT);
            } else if (argType == FunctionArgType.STRING_ARRAY) {
                return valueType == ValueType.STRING;
            }
        } else {
            if(argType == FunctionArgType.NUMBER) {
                return valueType.IS_NUMBER;
            } else if (argType == FunctionArgType.INTEGER) {
                return (valueType == ValueType.BYTE || valueType == ValueType.SHORT || valueType == ValueType.INTEGER || valueType == ValueType.LONG);
            } else if (argType == FunctionArgType.DECIMAL) {
                return (valueType == ValueType.DOUBLE || valueType == ValueType.FLOAT);
            } else if (argType == FunctionArgType.STRING) {
                return valueType == ValueType.STRING;
            }
        }
        return false;
    }

}
