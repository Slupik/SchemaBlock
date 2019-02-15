package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.utils.TextUtils;

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

    static List<Token> convertInfixToRPN(List<Token> infixNotation) {
        return convertInfixToRPN(infixNotation, 0);
    }

    static List<Token> convertInfixToRPN(List<Token> infixNotation, int globalNestLvl) {

        List<Token> rpn = new LinkedList<>();
        Stack<Token> operatorsStack = new Stack<>();

        for (int i=0;i<infixNotation.size();i++) {
            Token token = infixNotation.get(i);
            if ("(".equals(token.getData())) {
                operatorsStack.push(token);
                continue;
            }

            if (")".equals(token.getData())) {
                while (!"(".equals(operatorsStack.peek().getData())) {
                    rpn.add(operatorsStack.pop());
                }
                operatorsStack.pop();
                continue;
            }

            // {v1, v2, v3}
            if("{".equals(token.getData())) {
                List<Token> arrayBuffer = new ArrayList<>();
                arrayBuffer.add(token);
                i+=1;

                int nestLvl = 0;
                int argsCount = 0;
                List<Token> buffer = new ArrayList<>();
                for(;i<infixNotation.size() && nestLvl>=0;i++) {
                    Token temp = infixNotation.get(i);

                    //Manipulate of nest level
                    if("{".equals(temp.getData())) {
                        nestLvl++;
                    }
                    if("}".equals(temp.getData())) {
                        nestLvl--;
                        if(nestLvl<0) {
                            if(buffer.size()>0) {
                                argsCount++;
                            }
                            arrayBuffer.addAll(getArgumentsAsRPN(buffer));
                            arrayBuffer.add(temp);
                            break;
                        }
                    }

                    if(nestLvl==0) {
                        if(",".equals(temp.getData())) {
                            argsCount++;
                            arrayBuffer.addAll(getArgumentsAsRPN(buffer));
                            arrayBuffer.add(temp);
                            buffer.clear();
                            continue;
                        }
                    }

                    buffer.add(temp);
                }

                Token size = new Token(String.valueOf(argsCount), token.getLine(), token.getPos());
                rpn.add(size);
                rpn.addAll(arrayBuffer);
                continue;
            }

            // an operator
            if (OPERATION.containsKey(token.getData())) {
                if("=".equals(token.getData())) {
                    rpn.add(new SpecialToken(token));
                }
                while (!operatorsStack.empty() && OPERATION.get(token.getData()) <= OPERATION.get(operatorsStack.peek().getData())) {
                    rpn.add(operatorsStack.pop());
                }
                operatorsStack.push(token);
                continue;
            }

            if (TextUtils.isNumber(token.getData())) {
                rpn.add(token);
                continue;
            }

            //If it's a function
            if(i+1<infixNotation.size() && "(".equals(infixNotation.get(i+1).getData())) {
                i+=2;
                int nestLvl = 0;
                int argsCount = 0;
                boolean existsAnyArguments = false;

                List<List<Token>> args = new ArrayList<>();
                List<Token> buffer = new ArrayList<>();
                for(;i<infixNotation.size() && nestLvl>=0;i++) {
                    Token temp = infixNotation.get(i);

                    //Manipulate of nest level
                    if("(".equals(temp.getData())) {
                        nestLvl++;
                    }
                    if(")".equals(temp.getData())) {
                        nestLvl--;
                        if(nestLvl<0) {
                            break;
                        }
                    }

                    //How many arguments?
                    if(",".equals(temp.getData()) && nestLvl==0) {
                        args.add(buffer);
                        buffer = new ArrayList<>();
                        argsCount++;
                    }

                    if(!existsAnyArguments && !("(".equals(temp.getData()) || ")".equals(temp.getData()))) {
                        existsAnyArguments = true;
                    }

                    buffer.add(temp);
                }
                args.add(buffer);
                for(int j=args.size()-1;j>=0;j--) {
                    rpn.addAll(getArgumentsAsRPN(args.get(j)));
                }

                if(existsAnyArguments) argsCount++;
                token.setFunctionArguments(argsCount);
                Token argumentsCount = new Token(Integer.toString(argsCount), token.getLine(), token.getPos());
                rpn.add(argumentsCount);

                rpn.add(token);
                continue;
            }

            //If it's brackets of array
            if("[".equals(token.getData())) {
                i++;
                int nestLvl = 0;
                List<Token> buffer = new ArrayList<>();
                for(;i<infixNotation.size() && nestLvl>=0;i++) {
                    Token temp = infixNotation.get(i);

                    //Manipulate of nest level
                    if("[".equals(temp.getData())) {
                        nestLvl++;
                    }
                    if("]".equals(temp.getData())) {
                        nestLvl--;
                        if(nestLvl<0) {
                            break;
                        }
                    }

                    buffer.add(temp);
                }
                rpn.add(new SpecialToken("["+globalNestLvl, token.getLine(), token.getPos()));
                rpn.addAll(convertInfixToRPN(buffer, globalNestLvl+1));
                rpn.add(new SpecialToken(globalNestLvl+"]", infixNotation.get(i).getLine(), infixNotation.get(i).getPos()));

                //TODO is this necessary?
//                rpn.add(new Token("["+globalNestLvl+"]", token.getLine(), token.getPos()));
                continue;
            }

            rpn.add(token);
        }

        // at the end, pop all the elements in operatorsStack to rpn
        while (!operatorsStack.isEmpty()) {
            rpn.add(operatorsStack.pop());
        }

        return rpn;
    }

    private static List<Token> getArgumentsAsRPN(List<Token> raw) {
        List<Token> rpn = new ArrayList<>();

        int nestLvl = 0;
        List<Token> buffer = new ArrayList<>();
        for (Token token : raw) {
            //Manipulate of nest level
            if ("(".equals(token.getData())) {
                nestLvl++;
            }
            if (")".equals(token.getData())) {
                nestLvl--;
                if (nestLvl < 0) {
                    break;
                }
            }

            //How many arguments?
            if (",".equals(token.getData()) && nestLvl == 0) {
                rpn.addAll(convertInfixToRPN(buffer));
                continue;
            }

            buffer.add(token);
        }
        rpn.addAll(convertInfixToRPN(buffer));

        return rpn;
    }
}
