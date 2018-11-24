package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.MathCalculation;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.Variable;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableHeap;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class CodeParser {

    private static final boolean TEST_MODE = false;
    private static final VariableHeap heap = new VariableHeap();

    public static void execute(String codeBlock) throws WrongArgumentException, NotFoundTypeException, InvalidArgumentsException, VariableNotFound, UnsupportedValueException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        List<String> lines = BlockTokenizer.tokenize(codeBlock);
        for(String line:lines) {
            List<String> tokens = LineTokenizer.tokenize(line);
            CodeExecutor.execute(heap, tokens);
        }

        if(TEST_MODE) {
            System.out.println("==============");
            for(String varName:heap.getVariableNames()) {
                String data = heap.getVariable(varName).toString();
                System.out.println("data: " + data);
            }
            System.out.println("==============");
        }
    }

    public static void updateVariable(String varName, String newValue) throws NotFoundTypeException, VariableNotFound, IncompatibleTypeException {
        Variable var = getHeap().getVariable(varName);
        if(var==null) {
            throw new VariableNotFound(varName);
        }
        ValueType resultType = ValueType.getStandardizedType(newValue);
        if(ValueType.isCompatible(var.getType(), resultType)) {
            var.setValue(newValue);
        } else {
            throw new IncompatibleTypeException(var.getType(), resultType);
        }
    }

    public static String getValueToPrint(String value) throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException {
        return MathCalculation.getResult(heap, value).toString();
    }

    public static void clearHeap() {
        heap.clear();
    }

    public static VariableHeap getHeap() {
        return heap;
    }
}
