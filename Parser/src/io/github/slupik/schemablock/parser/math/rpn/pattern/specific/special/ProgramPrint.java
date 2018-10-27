package io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class ProgramPrint extends MathPattern {

    //TODO make this non static
    private static IOproxy io = new DefaultIO();

    public ProgramPrint() {
        super("print");
    }

    public static void setIo(IOproxy io) {
        ProgramPrint.io = io;
    }

    @Override
    public Object getResult(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            String value = args[0].getValue();
            io.print(value);
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
