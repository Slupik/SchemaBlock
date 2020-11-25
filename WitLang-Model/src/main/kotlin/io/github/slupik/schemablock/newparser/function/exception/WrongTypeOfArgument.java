package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;

import static io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType.FUNCTION_ILLEGAL_ARGUMENTS_TYPE;

/**
 * All rights reserved & copyright ©
 */
public class WrongTypeOfArgument extends FunctionExecutionException {

    public WrongTypeOfArgument(int line, int position, String name) {
        super("Function with name " + name + " doesn't accept given arguments.", line, position, name);
    }

    @Override
    public AlgorithmErrorType getType() {
        return FUNCTION_ILLEGAL_ARGUMENTS_TYPE;
    }

}
