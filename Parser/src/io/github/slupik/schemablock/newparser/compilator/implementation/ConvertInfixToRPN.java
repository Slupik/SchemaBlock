package io.github.slupik.schemablock.newparser.compilator.implementation;

import java.util.*;

/**
 * All rights reserved & copyright ©
 */
class ConvertInfixToRPN {
    private static final Map<String, Integer> OPERATION = new HashMap<>();

    static {
        //unary logical NOT
        OPERATION.put("!", 44);

        //unary bitwise NOT
        OPERATION.put("~", 44);

        //multiplicative
        OPERATION.put("/", 42);
        OPERATION.put("*", 42);
        OPERATION.put("%", 42);

        //additive
        OPERATION.put("+", 41);
        OPERATION.put("-", 41);

        //shift
        OPERATION.put("<<", 40);
        OPERATION.put(">>", 40);

        //relational
        OPERATION.put("<", 39);
        OPERATION.put(">", 39);
        OPERATION.put(">=", 39);
        OPERATION.put("<=", 39);

        //equality
        OPERATION.put("==", 38);
        OPERATION.put("!=", 38);

        //bitwise AND
        OPERATION.put("&", 34);

        //bitwise XOR
        OPERATION.put("^", 36);

        //bitwise OR
        OPERATION.put("|", 35);

        //logical AND
        OPERATION.put("&&", 34);

        //logical OR
        OPERATION.put("||", 33);

        //assigning a value
        OPERATION.put("=", 1);

        OPERATION.put("(", 0);
    }

    /*
        OPERATION.put("/", 5);
        OPERATION.put("*", 5);
        OPERATION.put("+", 4);
        OPERATION.put("-", 4);
        OPERATION.put("(", 0);
     */

    static Queue<String> convertInfixToRPN(String[] infixNotation) {

        Queue<String> rpn = new LinkedList<>();
        Stack<String> operatorsStack = new Stack<>();

        for (String token : infixNotation) {
            if ("(".equals(token)) {
                operatorsStack.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(operatorsStack.peek())) {
                    rpn.add(operatorsStack.pop());
                }
                operatorsStack.pop();
                continue;
            }
            // an operator
            if (OPERATION.containsKey(token)) {
                while (!operatorsStack.empty() && OPERATION.get(token) <= OPERATION.get(operatorsStack.peek())) {
                    rpn.add(operatorsStack.pop());
                }
                operatorsStack.push(token);
                continue;
            }

//            if (TextUtils.isNumber(token)) {
//                rpn.add(token);
//                continue;
//            }
//            throw new IllegalArgumentException("Invalid input");

            rpn.add(token);
        }
        // at the end, pop all the elements in operatorsStack to rpn
        while (!operatorsStack.isEmpty()) {
            rpn.add(operatorsStack.pop());
        }

        return rpn;
    }
}
