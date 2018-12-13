package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class DefaultCompilator implements Compilator {

    @Override
    public Queue<ByteCommand> getCompiled(String code) throws ComExIllegalEscapeChar {
        Queue<ByteCommand> commands = new LinkedList<>();

        int line = 1;
        int linePos = 1;
        StringBuilder word = new StringBuilder();
        boolean textMode = false;
        boolean commentMode = false;
        boolean pernamentCommentMode = false;
        List<String> parts = new ArrayList<>();
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

            //Comments
            if(commentMode || pernamentCommentMode) {
                if(!textMode && token == '*' && code.charAt(i+1)=='/') {
                    pernamentCommentMode = false;
                    i++;
                }
                continue;
            }

            //Text
            if(textMode) {
                if(token=='\"') {
                    parts.add(word.toString());
                    word = new StringBuilder();
                    textMode = false;
                    continue;
                }
                if(token=='\\') {
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
                            throw new ComExIllegalEscapeChar(line, linePos, token, nextToken);
                        }
                    }
                } else {
                    word.append(token);
                }
                continue;
            }
            if(token=='\"'){
                textMode = true;
                continue;
            }

            //Comments
            if(token == '/' && code.charAt(i+1)=='/') {
                commentMode = true;
                continue;
            }
            if(token == '/' && code.charAt(i+1)=='*') {
                pernamentCommentMode = true;
                continue;
            }


        }

        for(String text:parts) {
            System.out.println("text = " + text);
        }

        return null;
    }
}
