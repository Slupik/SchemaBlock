package io.github.slupik.schemablock.newparser.compilator.implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class BracketsRemover {

    List<Token> getCleared(List<Token> tokenized) {
        return getMerged(tokenized);
    }

    private List<Token> getMerged(List<Token> tokenized) {
        List<Token> optimized = new ArrayList<>();

        for (int i = 0; i < tokenized.size(); i++) {
            Token token = tokenized.get(i);

            if (token.getData().equals("[") && (i + 1) < tokenized.size()) {
                Token nextToken = tokenized.get(i + 1);
                if (nextToken.getData().equals("]")) {
                    optimized.add(mergeTokens(token, nextToken));
                    i++;
                    continue;
                }
            }

            if (token.getData().equals("(") && (i + 1) < tokenized.size()) {
                Token nextToken = tokenized.get(i + 1);
                if (nextToken.getData().equals(")")) {
                    optimized.add(mergeTokens(token, nextToken));
                    i++;
                    continue;
                }
            }

            optimized.add(token);
        }

        return optimized;
    }

    private Token mergeTokens(Token token, Token nextToken) {
        return new Token(token.getData() + nextToken.getData(), token.getLine(), token.getPos());
    }
}
