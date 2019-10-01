package io.github.slupik.schemablock.newparser.function;

import io.github.slupik.schemablock.newparser.function.definition.FunctionMin;
import io.github.slupik.schemablock.newparser.function.definition.FunctionPower;
import io.github.slupik.schemablock.newparser.function.definition.FunctionSqrt;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class DefaultFunctionContainer implements FunctionContainer {

    private static final List<Function> IMPLEMENTED_FUNCTIONS = new ArrayList<>();

    static {
        IMPLEMENTED_FUNCTIONS.add(new FunctionSqrt());
        IMPLEMENTED_FUNCTIONS.add(new FunctionMin());
        IMPLEMENTED_FUNCTIONS.add(new FunctionPower());
    }

    @Override
    public List<Function> getMatchingFunctions(String name) {
        List<Function> matching = new ArrayList<>();
        for(Function function:IMPLEMENTED_FUNCTIONS) {
            if(function.getName().equals(name)) {
                matching.add(function);
            }
        }
        return matching;
    }

}
