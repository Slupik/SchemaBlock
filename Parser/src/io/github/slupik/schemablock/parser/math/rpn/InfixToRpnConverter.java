package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;

import java.util.*;

/**
 * All rights reserved & copyright ©
 */
public class InfixToRpnConverter {

    private static final Map<String, Integer> OPERATION = new HashMap<>();
    private static final PatternFinder FUNCTIONS = new PatternFinder();

    static {
        OPERATION.put("/", 5);
        OPERATION.put("*", 5);
        OPERATION.put("+", 4);
        OPERATION.put("-", 4);
        OPERATION.put("(", 0);

        FUNCTIONS.registerPattern(new MathPatternSqrt());
        FUNCTIONS.registerPattern(new MathPatternSum());
    }

     public static Queue<String> convertInfixToRPN(String[] infixNotation) {
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        for (String token : infixNotation) {
            if ("(".equals(token)) {
                stack.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(stack.peek())) {
                    queue.add(stack.pop());
                }
                stack.pop();
                continue;
            }
            // an operator
            if (OPERATION.containsKey(token)) {
                while (!stack.empty() && OPERATION.get(token) <= OPERATION.get(stack.peek())) {
                    queue.add(stack.pop());
                }
                stack.push(token);
                continue;
            }

            if (isNumber(token)) {
                queue.add(token);
                continue;
            }

            if (FUNCTIONS.getForName(getFunctionName(token))!=null){
                queue.add(token);
                continue;
            }

            throw new IllegalArgumentException("Invalid input");
        }
        // at the end, pop all the elements in stack to queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        return queue;
    }

    private static String getFunctionName(String token) {
        if(token.indexOf('(')==-1) {
            return null;
        }
        return token.substring(0, token.indexOf('(')).trim();
    }

    private static boolean isNumber(String str) {
        try{
            Double.valueOf(str);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
