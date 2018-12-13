package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class Tokenizer {

    private final String code;
    private List<Token> tokens;
    private int line;
    private int linePos;

    Tokenizer(String code) {
        this.code = code;
    }

    List<Token> getTokenized() throws ComExIllegalEscapeChar {
        tokens = new ArrayList<>();
        line = 1;
        linePos = 1;
        StringBuilder word = new StringBuilder();

        boolean textMode = false;
        boolean commentMode = false;
        boolean permanentCommentMode = false;
        for(int i=0;i<code.length();i++) {
            char token = code.charAt(i);

            //Pointer (cursor)
            if(token=='\n') {
                line++;
                linePos = 0;
                commentMode = false;
            } else {
                linePos++;
            }

            if(!textMode && TextUtils.isWhitespace(token)) {
                addNewToken(word);
                continue;
            }

            //Comments
            if((commentMode || permanentCommentMode) && (i+1)<code.length()) {
                if(!textMode && token == '*' && code.charAt(i+1)=='/') {
                    permanentCommentMode = false;
                    i++;
                }
                continue;
            }

            //Text
            if(textMode) {
                if(token=='\"') {
                    addNewToken(word);
                    textMode = false;
                    continue;
                }
                if(token=='\\' && (i+1)<code.length()) {
                    char nextToken = code.charAt(++i);
                    switch (nextToken) {
                        case 't': {
                            word.append('\t');
                            break;
                        }
                        case 'b': {
                            word.append('\b');
                            break;
                        }
                        case 'n': {
                            word.append('\n');
                            break;
                        }
                        case 'r': {
                            word.append('\r');
                            break;
                        }
                        case 'f': {
                            word.append('\f');
                            break;
                        }
                        case '\'': {
                            word.append('\'');
                            break;
                        }
                        case '\"': {
                            word.append('\"');
                            break;
                        }
                        case '\\': {
                            word.append('\\');
                            break;
                        }
                        default: {
                            throw new ComExIllegalEscapeChar(
                                    line,
                                    linePos,
                                    token,
                                    nextToken
                            );
                        }
                    }
                } else {
                    word.append(token);
                }
                continue;
            }
            if(token=='\"'){
                addNewToken(word);
                textMode = true;
                continue;
            }

            //Comments
            if(token == '/' && (i+1)<code.length() && code.charAt(i+1)=='/') {
                addNewToken(word);
                commentMode = true;
                continue;
            }
            if(token == '/' && (i+1)<code.length() && code.charAt(i+1)=='*') {
                addNewToken(word);
                permanentCommentMode = true;
                continue;
            }

            if(CodeUtils.isFunctionalSignn(token)) {
                addNewToken(word);//Flush last word
                word.append(token);
                addNewToken(word);
                continue;
            }

            word.append(token);
        }
        return tokens;
    }

    private void addNewToken(StringBuilder builder) {
        if(builder.length()>0) {
            tokens.add(new Token(builder.toString(), line, linePos));
            builder.delete(0, builder.length());
        }
    }
}
