package io.github.slupik.schemablock.newparser.function;

import io.github.slupik.schemablock.newparser.function.definition.*;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class DefaultFunctionContainer implements FunctionContainer {

    private static final List<Function> IMPLEMENTED_FUNCTIONS = new ArrayList<>();

    static {
        //math
        IMPLEMENTED_FUNCTIONS.add(new FunctionSqrt());
        IMPLEMENTED_FUNCTIONS.add(new FunctionMin());
        IMPLEMENTED_FUNCTIONS.add(new FunctionMax());
        IMPLEMENTED_FUNCTIONS.add(new FunctionPower());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAbs());
        IMPLEMENTED_FUNCTIONS.add(new FunctionCeil());
        IMPLEMENTED_FUNCTIONS.add(new FunctionCos());
        IMPLEMENTED_FUNCTIONS.add(new FunctionExp());
        IMPLEMENTED_FUNCTIONS.add(new FunctionFloor());
        IMPLEMENTED_FUNCTIONS.add(new FunctionLn());
        IMPLEMENTED_FUNCTIONS.add(new FunctionLog10());
        IMPLEMENTED_FUNCTIONS.add(new FunctionMax());
        IMPLEMENTED_FUNCTIONS.add(new FunctionSin());
        IMPLEMENTED_FUNCTIONS.add(new FunctionTan());
        //convert
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsBoolean());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsByte());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsShort());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsInteger());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsLong());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsFloat());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsDouble());
        IMPLEMENTED_FUNCTIONS.add(new FunctionAsString());
        //io
        IMPLEMENTED_FUNCTIONS.add(new FunctionPrintln());
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
