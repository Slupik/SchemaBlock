package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.compilator.exception.MissingSemicolon;
import io.github.slupik.schemablock.newparser.utils.CodeOperations;
import io.github.slupik.schemablock.newparser.utils.TextUtils;

import java.util.*;

/**
 * All rights reserved & copyright ©
 */
class ConvertInfixToRPN {
    private static final Map<String, Integer> OPERATION = new CodeOperations();

    static List<Token> convertInfixToRPN(List<Token> infixNotation) throws MissingSemicolon {
        return convertInfixToRPN(infixNotation, 0);
    }

    private static List<Token> convertInfixToRPN(List<Token> infixNotation, int globalNestLvl) throws MissingSemicolon {

        List<Token> rpn = new LinkedList<>();
        Stack<Token> operatorsStack = new Stack<>();

        //It should be true at the beginning to allow to parse raw data ex. "-3"
        boolean lastWasOperation = true;
        //To be sure that only one assignment operation is made
        int assingementCount = 0;

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
                            arrayBuffer.addAll(convertInfixToRPN(buffer));
                            arrayBuffer.add(temp);
                            break;
                        }
                    }

                    if(nestLvl==0) {
                        if(",".equals(temp.getData())) {
                            argsCount++;
                            arrayBuffer.addAll(convertInfixToRPN(buffer));
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
                    assingementCount++;
                    if(assingementCount>1) {
                        throw new MissingSemicolon(token.getLine(), token.getPos());
                    }
                }
                if(lastWasOperation && ("-".equals(token.getData()) || "+".equals(token.getData()))) {
                    //For example: (-9), (+-+-+-9), -9, -+-+-+9
                    operatorsStack.push(new Token("*", token.getLine(), token.getPos()));
                    if("-".equals(token.getData())) {
                        rpn.add(new Token("-1", token.getLine(), token.getPos()));
                    } else {
                        rpn.add(new Token("1", token.getLine(), token.getPos()));
                    }
                } else {
                    while (!operatorsStack.empty() && OPERATION.get(token.getData()) <= OPERATION.get(operatorsStack.peek().getData())) {
                        rpn.add(operatorsStack.pop());
                    }
                    operatorsStack.push(token);
                }
                lastWasOperation = true;
                continue;
            }

            //Number
            if (TextUtils.isNumber(token.getData())) {
                rpn.add(token);
                lastWasOperation = false;
                continue;
            }

            //If it's a function
            if(i+1<infixNotation.size() && "(".equals(infixNotation.get(i+1).getData())) {
                lastWasOperation = false;

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

                continue;
            }

            lastWasOperation = false;
            rpn.add(token);
        }

        // at the end, pop all the elements in operatorsStack to rpn
        while (!operatorsStack.isEmpty()) {
            rpn.add(operatorsStack.pop());
        }

        return rpn;
    }

    private static List<Token> getArgumentsAsRPN(List<Token> raw) throws MissingSemicolon {
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
