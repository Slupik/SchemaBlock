package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class DefaultCompilator implements Compilator {

    @Override
    public Queue<ByteCommand> getCompiled(String code) {
        Queue<ByteCommand> commands = new LinkedList<>();

        StringBuilder word = new StringBuilder();
        boolean textMode = false;
        boolean commentMode = false;
        for(int i=0;i<code.length();i++) {
            char token = code.charAt(i);

            if(textMode) {
                if(token=='\"') {

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
                    }
                }

            }
        }

        return null;
    }
}
