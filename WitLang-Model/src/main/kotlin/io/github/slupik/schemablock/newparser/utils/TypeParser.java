package io.github.slupik.schemablock.newparser.utils;


import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommandOperation;
import io.github.slupik.schemablock.newparser.compilator.implementation.Token;
import io.github.slupik.schemablock.newparser.memory.element.ValueType;

import static io.github.slupik.schemablock.newparser.memory.element.ValueType.*;

/**
 * All rights reserved & copyright ©
 */
public class TypeParser {

    public static ValueType getType(Token token) throws ValueTooBig {
        return getType(token.getData(), token.getLine(), token.getPos());
    }

    public static ValueType getType(String data, ByteCommandOperation commandOperation) throws ValueTooBig {
        return getType(data, commandOperation.getLine(), commandOperation.getPosition());
    }

    public static ValueType getType(String data, int line, int position) throws ValueTooBig {
        if (TextUtils.isNumber(data)) {
            if (CodeUtils.isLetterForNumber(data.charAt(data.length() - 1))) {
                char lastLetter = data.charAt(data.length() - 1);
                switch (lastLetter) {
                    case 's':
                        return SHORT;
                    case 'i':
                        return INTEGER;
                    case 'l':
                        return LONG;
                    case 'f':
                        return FLOAT;
                    case 'd':
                        return DOUBLE;
                }
            } else {
                try {
                    Integer.parseInt(data);
                    return INTEGER;
                } catch (Exception ignore) {
                }
                try {
                    Long.parseLong(data);
                    return LONG;
                } catch (Exception ignore) {
                }
                try {
                    double parsed = Double.parseDouble(data);
                    if (Double.toString(parsed).equals(data)) {
                        return DOUBLE;
                    } else {
                        throw new ValueTooBig(data, line, position);
                    }
                } catch (NumberFormatException ignore) {
                }
            }
        } else if (data.startsWith("\"") && data.endsWith("\"")) {
            return STRING;
        } else if (data.equals("true") || data.equals("false")) {
            return BOOLEAN;
        }
        return UNKNOWN;
    }
}
