package io.github.slupik.schemablock.newparser.function.exception;

import io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType;

import static io.github.slupik.schemablock.model.ui.error.AlgorithmErrorType.FUNCTION_ILLEGAL_ARGUMENTS_AMOUNT;

/**
 * All rights reserved & copyright ©
 */
public class WrongAmountOfArguments extends FunctionExecutionException {

    public WrongAmountOfArguments(int line, int position, String name) {
        super("Function " + name + " doesn't accept given number of arguments.", line, position, name);
    }

    @Override
    public AlgorithmErrorType getType() {
        return FUNCTION_ILLEGAL_ARGUMENTS_AMOUNT;
    }

}
