package io.github.slupik.schemablock.parser.math.rpn;

import io.github.slupik.schemablock.parser.math.rpn.pattern.PatternFinder;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSum;
import io.github.slupik.schemablock.parser.math.rpn.pattern.specific.MathPatternSqrt;
import org.apache.commons.lang3.math.NumberUtils;

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

    private static int nestINFO = 0;

     public static Queue<String> convertInfixToRPN(String[] infixNotation) {
         nestINFO++;
         System.out.println("new convertInfixToRPN!!!! "+nestINFO);

        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        for(String token:infixNotation) {
            System.out.println(nestINFO+" infix token = " + token);
        }

        for (int i=0;i<infixNotation.length;i++) {
            String token = infixNotation[i];

            if ("(".equals(token)) {
                stack.push(token);
                continue;
            }

            if (")".equals(token)) {
                System.out.println(nestINFO+" ++++++++++++++++++++++");
                for(String value:queue) {
                    System.out.println("queue value: "+value);
                }
                System.out.println(nestINFO+" ++++++++++++++++++++++");
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

            System.out.println(nestINFO+" token = " + token);
            if (i+1<infixNotation.length && infixNotation[i+1].equals("(")){
                int functionStartIndex = i;
                i += 2;
                for(;i<infixNotation.length;i++) {
                    String subToken = infixNotation[i];
                    System.out.println(nestINFO+" subToken = " + subToken);

                    if(subToken.equals(",")) {
                        continue;
                    }
                    if(isNumber(subToken) && (i+1<infixNotation.length && infixNotation[i+1].equals(","))) {
                        queue.add(subToken);
                    }  else if(subToken.equals(")")) {
                        break;
                    } else {//if(i+1<infixNotation.length && infixNotation[i+1].equals("("))
//                        String[] function = getFunctionWithArguments(infixNotation, i);
                        i--;
                        String[] function = getValuesUntilBreak(infixNotation, i);
                        i += function.length;
                        List<String> convertedFunction = new ArrayList<>(convertInfixToRPN(function));
                        for(String funToken:convertedFunction) {
                            System.out.println(nestINFO+" funToken = " + funToken);
                            queue.add(funToken);
                        }
                    }
//                    else {
//                        queue.add(subToken);
//                    }
                }
                int argumentsAmount = getAmountOfArguments(infixNotation, functionStartIndex);
                queue.add(token+";"+argumentsAmount);//OK

                continue;
            }

            throw new IllegalArgumentException("Invalid input");
        }
        // at the end, pop all the elements in stack to queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

         System.out.println(nestINFO+" ====================");
         for(String value:queue) {
             System.out.println("queue value: "+value);
         }
         System.out.println(nestINFO+" ====================");
        return queue;
    }

    private static String[] getValuesUntilBreak(String[] infixNotation, int startOfFunction) {
        List<String> function = new ArrayList<>();
        int tempIndex = startOfFunction;
        boolean functionMode = !NumberUtils.isParsable(infixNotation[tempIndex]) && infixNotation[tempIndex+1].equals("(");
        if(functionMode) {
            function.add(infixNotation[startOfFunction]);
        }
        int deepness = 0;
        for(int i=startOfFunction+1;i<infixNotation.length;i++) {
            String token = infixNotation[i];
            if(token.equals("(")) deepness++;
            if(token.equals(")")) deepness--;

            if(functionMode && deepness==0) {
                break;
            }
            if(!functionMode && token.equals(",") || deepness==-1) {
                break;
            }

            function.add(token);
            System.out.println("get values token = " + token);
        }
        return function.toArray(new String[0]);
    }

    //TODO write tests
    private static int getAmountOfArguments(String[] infixNotation, int functionStartIndex) {
        int argumentsStart = functionStartIndex+2;
        int argsAmount = 0;
        int deepness = 0;
        for(int i=argumentsStart;i<infixNotation.length;i++) {
            String token = infixNotation[i];
            System.out.println("amount of args token = " + token);
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
//                argsAmount++;
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
