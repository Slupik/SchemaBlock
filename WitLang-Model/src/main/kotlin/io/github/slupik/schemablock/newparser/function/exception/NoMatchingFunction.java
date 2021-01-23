package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights reserved & copyright ©
 */
public class NoMatchingFunction extends FunctionExecutionException {

    public final List<ValueType> argsTypes;

    public NoMatchingFunction(String name, List<ValueType> argsTypes, int line, int position) {
        super("Function with name: " + name + " with types of arguments: " +
                argsTypes.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ", "", ""))
                + " doesn't exists.", line, position,
                name);
        this.argsTypes = argsTypes;
    }

    @Override
    public AlgorithmErrorType getType() {
        return AlgorithmErrorType.FUNCTION_NOT_EXISTS;
    }

}
