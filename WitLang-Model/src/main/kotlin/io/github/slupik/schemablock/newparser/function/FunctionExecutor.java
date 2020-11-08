package io.github.slupik.schemablock.newparser.function;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Value;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface FunctionExecutor {

    Value execute(List<Function> availableFunctions, List<Value> args) throws AlgorithmException;

}
