package io.github.slupik.schemablock.parser.code;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class BlockTokenizer {

    public static List<String> tokenize(String codeBlock) {
        List<String> lines = new ArrayList<>();

        boolean quotationMode = false;
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<codeBlock.length();i++) {
            char c = codeBlock.charAt(i);

            if(c=='\\') {
                if(codeBlock.length()>i+1) {
                    char nc = codeBlock.charAt(i+1);
                    if(nc=='"') {
                        i++;
                        buffer.append('"');
                        quotationMode = !quotationMode;
                    } else {
                        buffer.append(c);
                    }
                } else {
                    buffer.append(c);
                }
            } else if (c==';' && !quotationMode){
                lines.add(buffer.toString());
                buffer = new StringBuilder();
            } else {
                buffer.append(c);
            }
        }
        if(buffer.length()>0) {
            lines.add(buffer.toString());
        }
        return lines;
    }
}
