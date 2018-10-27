package io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class ProgramPrintln extends MathPattern {

    //TODO make this non static
    private static IOproxy io = new DefaultIO();

    public ProgramPrintln() {
        super("println");
    }

    public static void setIo(IOproxy io) {
        ProgramPrintln.io = io;
    }

    @Override
    public Object getResult(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            String value = args[0].getValue();
            if(value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length()-1);
            }
            io.print(value+'\n');
            return null;
        } else {
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public boolean isValidArgs(Value... args) {
        return args.length==1;
    }

    @Override
    public int maxArgs() {
        return 1;
    }
}
