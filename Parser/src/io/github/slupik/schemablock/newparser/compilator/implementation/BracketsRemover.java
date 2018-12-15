package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.utils.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class BracketsRemover {

    List<Token> getCleared(List<Token> tokenized) {
        List<Token> optimized = getMerged(tokenized);
//        return removeUnnecessaryParentheses(optimized);
        return optimized;
    }

    private List<Token> getMerged(List<Token> tokenized) {
        List<Token> optimized = new ArrayList<>();

        for(int i=0;i<tokenized.size();i++) {
            Token token = tokenized.get(i);

            if(token.getData().equals("[") && (i+1)<tokenized.size()) {
                Token nextToken = tokenized.get(i+1);
                if(nextToken.getData().equals("]")) {
                    optimized.add(mergeTokens(token, nextToken));
                    i++;
                    continue;
                }
            }

            if(token.getData().equals("(") && (i+1)<tokenized.size()) {
                Token nextToken = tokenized.get(i+1);
                if(nextToken.getData().equals(")")) {
                    optimized.add(mergeTokens(token, nextToken));
                    i++;
                    continue;
                }
            }

            optimized.add(token);
        }

        return optimized;
    }

    private List<Token> removeUnnecessaryParentheses(List<Token> optimized) {
        List<Token> changed = new ArrayList<>();

        List<Token> subList = new ArrayList<>();
        Token tempLeftBracket = null;
        int nestLvl = 0;
        for(int i=0;i<optimized.size();i++) {
            Token token = optimized.get(i);

            if(!token.getData().equals("(") && !token.getData().equals(")") && nestLvl>=0 && (i+1)<optimized.size()
                    && optimized.get(i+1).getData().equals("(") && !CodeUtils.isFunctionalSign(token.getData().charAt(0))) {
                nestLvl--;
            }

            if(token.getData().equals("(")) {
                nestLvl++;
                if(nestLvl==1) {
                    tempLeftBracket = token;
                }
            }

            if(nestLvl<1) {
                changed.add(token);
            }
            if(nestLvl>0) {
                if(!(token.getData().equals("(") || token.getData().equals(")")) || nestLvl>1) {
                    subList.add(token);
                }
            }

            if(token.getData().equals(")")) {
                nestLvl--;
                if(nestLvl<1) {
                    List<Token> cleared = removeUnnecessaryParentheses(subList);
                    int operations = countOperations(cleared);
                    if(operations>2) {
                        changed.add(tempLeftBracket);
                    }
                    changed.addAll(cleared);
                    if(operations>2) {
                        changed.add(token);
                    }
                    subList.clear();
                }
            }
        }

        return changed;
    }

    private int countOperations(List<Token> cleared) {
        int nestLvl = 0;
        int arrayNestLvl = 0;
        int operations = 0;

        for(int i=0;i<cleared.size();i++) {
            Token token = cleared.get(i);

            if(token.getData().equals("(")) {
                nestLvl++;
                continue;
            }
            if(token.getData().equals(")")) {
                nestLvl--;
                continue;
            }
            if(token.getData().equals("[")) {
                arrayNestLvl++;
                continue;
            }
            if(token.getData().equals("]")) {
                arrayNestLvl--;
                continue;
            }

            if(nestLvl==0 && arrayNestLvl==0) {
                operations++;
            }
        }
        return operations;
    }

    private Token mergeTokens(Token token, Token nextToken) {
        return new Token(token.getData()+nextToken.getData(), token.getLine(), token.getPos());
    }
}
