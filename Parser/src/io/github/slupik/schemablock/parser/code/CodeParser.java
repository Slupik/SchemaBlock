package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableHeap;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

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
            for(String varName:heap.getVariableNames()) {
                String data = heap.getVariable(varName).toString();
                System.out.println("data: " + data);
            }
        }
    }

    public static void clearHeap() {
        heap.clear();
    }
}
