package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights reserved & copyright ©
 */
public class NoMatchingFunction extends FunctionExecutionException {

    public NoMatchingFunction(String name, List<ValueType> argsTypes) {
        super("Function with name: " + name + " with types of arguments: " +
                argsTypes.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("-", "", ""))
                + " doesn't exists."
        );
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.FUNCTION_NOT_EXISTS;
    }

}
