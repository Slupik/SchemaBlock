package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.exception.ExceptedArrayButNotReceivedException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
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
    public void execute(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, UnknownOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
        Queue<ByteCommand> cmds = compilator.getCompiled(code);
        execute(cmds);
    }

    @Override
    public void execute(Queue<ByteCommand> cmds) throws IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ValueTooBig, UnknownOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
        ByteCodeExe.execute(cmds, memory, register);
    }

    @Override
    public SimpleValue getResult(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, UnknownOperation, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
        Queue<ByteCommand> cmds = compilator.getCompiled(code, true);
        return getResult(cmds);
    }

    @Override
    public SimpleValue getResult(Queue<ByteCommand> cmds) throws ValueTooBig, IncompatibleArrayException, UnknownOperation, IllegalOperation, IncompatibleTypeException, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException {
        ByteCodeExe.execute(cmds, memory, register);
        return ((SimpleValue) register.pop());
    }
}
