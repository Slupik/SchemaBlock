package io.github.slupik.schemablock.newparser.executor;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public interface Executor {

    void execute(String code) throws AlgorithmException;
    void execute(Queue<ByteCommand> cmds) throws AlgorithmException;

    SimpleValue getResult(String code) throws AlgorithmException;
    SimpleValue getResult(Queue<ByteCommand> cmds) throws AlgorithmException;

}
