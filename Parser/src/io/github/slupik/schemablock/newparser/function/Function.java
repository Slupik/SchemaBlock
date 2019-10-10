package io.github.slupik.schemablock.newparser.function;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.memory.element.Value;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface Function {

    String getName();

    List<FunctionArgType> getArgumentsType();

    Value execute(List<Value> args) throws AlgorithmException;

}
