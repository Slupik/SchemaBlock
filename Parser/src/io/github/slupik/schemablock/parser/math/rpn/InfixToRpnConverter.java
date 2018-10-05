package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;

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

        for (int i=0;i<infixNotation.length;i++) {
            String token = infixNotation[i];

            if(token.equals(",")) continue;


            if ("(".equals(token)) {
                stack.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(stack.peek())) {
                    queue.add(stack.pop());
                }
                stack.pop();//remove "(" from stack
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

            if(infixNotation.length>i+1 && infixNotation[i+1].equals("(")) {
                String[] function = getFunctionWithArguments(infixNotation, i);
                String[][] args = getArgsOfFun(function);

                for(String[] arg:args) {
                    Queue<String> inner = convertInfixToRPN(arg);
                    queue.addAll(inner);
                }

                queue.add(token+";"+getAmountOfArguments(infixNotation, i));
                i += function.length;
                continue;
            }

            throw new IllegalArgumentException("Invalid input ("+token+")");
        }
        // at the end, pop all the elements in stack to queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        return queue;
    }

    private static String[][] getArgsOfFun(String[] function) {
        List<String[]> args = new ArrayList<>();
        List<String> elements = new ArrayList<>();
        int argIndex = 0;
        int deepness = 0;
        boolean addComma = false;
        if(function.length>4) {
            addComma = function[4].equals("(");
        }

        for(int i=2;i<function.length;i++) {
            String token = function[i];
            if(token.equals("(")) deepness++;
            if(token.equals(")")) deepness--;

            if(deepness==-1) {
                break;
            }

            if(deepness == 0 && token.equals(",")) {
                if(addComma) {
                    elements.add(token);
                }
                args.add(argIndex, elements.toArray(new String[0]));
                argIndex++;
                addComma = function[i+2].equals("(");
                elements.clear();
                continue;
            }

            elements.add(token);
        }
        if(elements.size()>0) {
            args.add(argIndex, elements.toArray(new String[0]));
        }

        String[][] converted = new String[args.size()][];
        for(int i=0;i<args.size();i++) {
            converted[i] = args.get(i);
        }
        return converted;
    }

    //TODO write tests
    private static int getAmountOfArguments(String[] infixNotation, int functionStartIndex) {
        int argumentsStart = functionStartIndex+2;
        int argsAmount = 0;
        int deepness = 0;
        for(int i=argumentsStart;i<infixNotation.length;i++) {
            String token = infixNotation[i];
            if(token.equals("(")) {
                deepness++;
                continue;
            }
            if(token.equals(")")) {
                deepness--;
                if(deepness==-1) {
                    break;
                }
                continue;
            }

            if(deepness==0) {
                if(token.equals(",")) {
                    argsAmount++;
                } else if(argsAmount==0) {
                    argsAmount++;
                }
            }
        }
        return argsAmount;
    }

    //TODO write tests
    private static String[] getFunctionWithArguments(String[] infixNotation, int startOfFunction) {
        List<String> function = new ArrayList<>();
        function.add(infixNotation[startOfFunction]);
        int deepness = 0;
        for(int i=startOfFunction+1;i<infixNotation.length;i++) {
            String token = infixNotation[i];
            if(token.equals("(")) deepness++;
            if(token.equals(")")) deepness--;

            function.add(token);

            if(deepness==0) {
                break;
            }
        }
        return function.toArray(new String[0]);
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
