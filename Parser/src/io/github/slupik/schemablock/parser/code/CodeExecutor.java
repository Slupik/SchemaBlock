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

    public static boolean execute(VariableHeap heap, List<String> tokens) throws WrongArgumentException, VariableIsAlreadyDefinedException, VariableNotFound, InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, IncompatibleTypeException {

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

                ValueType resultType = ValueType.getStandardizedType(result);
                if(ValueType.isCompatible(var.getType(), resultType)) {
                    var.setValue(result.toString());
                } else {
                    throw new IncompatibleTypeException(resultType, var.getType());
                }

                i--;
            }
        }

        return false;
    }

    private static void createVariables(VariableHeap heap, List<String> tokens) throws WrongArgumentException, VariableIsAlreadyDefinedException, InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException {
        for(int i=0;i<tokens.size();i++) {
            String token = tokens.get(i);

            //register variable
            ValueType type = getTypeForTypeName(token);
            if (type != null) {
                do {
                    String[] command = getStandardizedCreation(tokens.subList(i, tokens.size()));

                    String name = command[2];
                    if(command[1]!=null && command[1].length()>0) {
                        Object lengthResult = MathCalculation.getResult(heap, command[1]);
                        if(lengthResult instanceof Integer) {
                            Integer length = ((Integer) lengthResult);
                            heap.registerArray(type, name, length);
                        } else {
                            throw new WrongArgumentException("length of array in integer", "length in type "+type);
                        }
                    } else {
                        heap.registerVariable(new Variable(name, type, null));
                    }
                    i++;
                } while(i + 1 < tokens.size() && tokens.get(++i).equals(","));
            }
        }
    }

    private static String[] getStandardizedCreation(List<String> tokens) throws WrongArgumentException {
        String varType;
        String varName;
        String arrayLength;

        int i=0;
        varType = tokens.get(i);

        if (i + 1 >= tokens.size()) {
            throw new WrongArgumentException("variable name", "nothing");
        }
        i++;
        String nameOrBrackets = tokens.get(i);

        if(isBrackets(nameOrBrackets)) {
            arrayLength = nameOrBrackets.substring(1, nameOrBrackets.length()-1);

            if (i + 1 >= tokens.size()) {
                throw new WrongArgumentException("array index", "nothing");
            }
            i++;
            nameOrBrackets = tokens.get(i);

            if(isBrackets(nameOrBrackets)) {
                throw new WrongArgumentException("array name", "array index");
            }
            varName = nameOrBrackets;
            if (isNumber(varName)) {
                throw new WrongArgumentException("variable name", "number");
            }
        } else {
            arrayLength = "";
        }
        varName = nameOrBrackets;
        if (isNumber(varName)) {
            throw new WrongArgumentException("variable name", "number");
        }

        return new String[]{varType, arrayLength, varName};
    }

    private static boolean isBrackets(String toCheck) {
        return toCheck.startsWith("[") && toCheck.endsWith("]");
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
