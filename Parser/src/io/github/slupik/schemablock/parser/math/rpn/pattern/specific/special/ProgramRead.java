package io.github.slupik.schemablock.parser.math.rpn.pattern.specific.special;

import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.MathPattern;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.Value;

/**
 * All rights reserved & copyright ©
 */
public class ProgramRead extends MathPattern {

    //TODO make this non static
    private static IOproxy io = new DefaultIO();

    public ProgramRead() {
        super("read");
    }

    public static void setIo(IOproxy io) {
        ProgramRead.io = io;
    }

    @Override
    public Object getResult(Value... args) throws InvalidArgumentsException, UnsupportedValueException {
        if(isValidArgs(args)) {
            String type = args[0].getValue();
            String data = io.readLine();

            switch (type) {
                case "SHORT": {
                    return Short.valueOf(data);
                }
                case "INTEGER": {
                    return Integer.valueOf(data);
                }
                case "LONG": {
                    return Long.valueOf(data);
                }
                case "FLOAT": {
                    return Float.valueOf(data);
                }
                case "DOUBLE": {
                    return Double.valueOf(data);
                }
                case "STRING": {
                    return "\""+data+"\"";
                }
            }
            throw new UnsupportedValueException();
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
