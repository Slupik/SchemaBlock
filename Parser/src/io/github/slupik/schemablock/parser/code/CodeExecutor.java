package io.github.slupik.schemablock.parser.code;

import io.github.slupik.schemablock.parser.math.rpn.MathCalculation;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.Variable;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableHeap;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

import static io.github.slupik.schemablock.parser.math.rpn.variable.value.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
public class CodeExecutor {

    public static boolean execute(VariableHeap heap, List<String> tokens) throws WrongArgumentException, VariableIsAlreadyDefinedException, VariableNotFound, InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException {

        createVariables(heap, tokens);

        for(int i=tokens.size()-1;i>=0;i--) {
            String token = tokens.get(i);

            //set value to variable
            if(token.equals("=")) {
                if(i<1) {
                    throw new WrongArgumentException("variable name", "nothing");
                }
                String varName = tokens.get(i-1);
                if(isNumber(varName)) {
                    throw new WrongArgumentException("variable name", "number");
                }
                if(i+1>=tokens.size()) {
                    throw new WrongArgumentException("variable value", "nothing");
                }
                Variable var = heap.getVariable(varName);
                if(var==null) {
                    throw new VariableNotFound(varName);
                }

                String varValue = tokens.get(i+1);
                Object result = MathCalculation.getResult(heap, varValue);
                var.setValue(result.toString());
                i--;
            }
        }

        return false;
    }

    private static void createVariables(VariableHeap heap, List<String> tokens) throws WrongArgumentException, VariableIsAlreadyDefinedException {
        for(int i=0;i<tokens.size();i++) {
            String token = tokens.get(i);

            //register variable
            ValueType type = getTypeForTypeName(token);
            if (type != null) {
                do {
                    if (i + 1 >= tokens.size()) {
                        throw new WrongArgumentException("variable name", "nothing");
                    }
                    i++;
                    String varName = tokens.get(i);
                    if (isNumber(varName)) {
                        throw new WrongArgumentException("variable name", "number");
                    }

                    heap.registerVariable(new Variable(varName, type, null));
                } while(i + 1 < tokens.size() && tokens.get(++i).equals(","));

                continue;
            }
        }
    }

    private static boolean isNumber(String varName) {
        if(NumberUtils.isParsable(varName)) {
            if(!varName.startsWith("0x")) {
                for (int i=0;i<varName.length();i++) {
                    if(!Character.isDigit(varName.charAt(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static ValueType getTypeForTypeName(String name) {
        switch (name) {
            case "short": {
                return SHORT;
            }
            case "int": {
                return INT;
            }
            case "long": {
                return LONG;
            }
            case "float": {
                return FLOAT;
            }
            case "double": {
                return DOUBLE;
            }
            case "String": {
                return STRING;
            }
            case "byte": {
                return BYTE;
            }
            case "boolean": {
                return BOOLEAN;
            }
            case "char": {
                return CHAR;
            }
        }
        return null;
    }
}
