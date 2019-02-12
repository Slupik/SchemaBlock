package io.github.slupik.schemablock.newparser.compilator.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import org.junit.jupiter.api.Test;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
class DefaultCompilatorTest {

    @Test
    void getCompiled() throws ComExIllegalEscapeChar {
        //FIXME
//        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("\"test a\\\"b\\tc\"");//"test a\"b\tc"
        Queue<ByteCommand> queue = new DefaultCompilator().getCompiled("double name = 3;");//"test a\"b\tc"
        for(ByteCommand bc:queue) {
            System.out.println("bc.getCommandType().toString() = " + bc.toString());
        }
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test//\"\"awdwa\"");
//        System.out.println("=======");
//        new DefaultCompilator().getCompiled("\"test\"/*\"awdwa\"*/");
    }
}