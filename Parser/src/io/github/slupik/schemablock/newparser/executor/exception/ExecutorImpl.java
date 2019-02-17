package io.github.slupik.schemablock.newparser.executor.exception;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class ExecutorImpl implements Executor {

    private final Compilator compilator;
    private final Memory memory;
    private final Register register;

    public ExecutorImpl(Compilator compilator, Memory memory, Register register) {
        this.compilator = compilator;
        this.memory = memory;
        this.register = register;
    }

    @Override
    public void execute(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, IncompatibleArrayException, IncompatibleTypeException {
        Queue<ByteCommand> cmds = compilator.getCompiled(code);
        execute(cmds);
    }

    @Override
    public void execute(Queue<ByteCommand> cmds) throws IncompatibleArrayException, IncompatibleTypeException {
        ByteCodeExe.execute(cmds, memory, register);
    }

    @Override
    public Object getResult(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar {
        Queue<ByteCommand> cmds = compilator.getCompiled(code);
        return getResult(cmds);
    }

    @Override
    public Object getResult(Queue<ByteCommand> cmds) {
        //TODO implement
        return null;
    }
}
