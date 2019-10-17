package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.Compilator;
import io.github.slupik.schemablock.newparser.executor.Executor;
import io.github.slupik.schemablock.newparser.function.DefaultFunctionContainer;
import io.github.slupik.schemablock.newparser.function.DefaultFunctionExecutor;
import io.github.slupik.schemablock.newparser.function.FunctionContainer;
import io.github.slupik.schemablock.newparser.function.FunctionExecutor;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.element.ArrayCell;
import io.github.slupik.schemablock.newparser.memory.element.Memoryable;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.memory.element.Variable;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public class ExecutorImpl implements Executor {

    private static final FunctionContainer FUNCTIONS_CONTAINER = new DefaultFunctionContainer();
    private static final FunctionExecutor FUNCTION_EXECUTOR = new DefaultFunctionExecutor();

    private final Compilator compilator;
    private final Memory memory;
    private final Register register;

    public ExecutorImpl(Compilator compilator, Memory memory, Register register) {
        this.compilator = compilator;
        this.memory = memory;
        this.register = register;
    }

    @Override
    public void execute(String code) throws AlgorithmException {
        Queue<ByteCommand> cmds = compilator.getCompiled(code);
        execute(cmds);
    }

    @Override
    public void execute(Queue<ByteCommand> cmds) throws AlgorithmException {
        ByteCodeExe.execute(cmds, memory, register, FUNCTIONS_CONTAINER, FUNCTION_EXECUTOR);
    }

    @Override
    public SimpleValue getResult(String code) throws AlgorithmException {
        Queue<ByteCommand> cmds = compilator.getCompiled(code, true);
        return getResult(cmds);
    }

    @Override
    public SimpleValue getResult(Queue<ByteCommand> cmds) throws AlgorithmException {
        ByteCodeExe.execute(cmds, memory, register, FUNCTIONS_CONTAINER, FUNCTION_EXECUTOR);
        Memoryable memoryable = register.pop();
        if(memoryable instanceof SimpleValue) {
            return ((SimpleValue) memoryable);
        } else if(memoryable instanceof ArrayCell) {
            return ((SimpleValue) ((ArrayCell) memoryable).getValue());
        } else {
            return ((SimpleValue) ((Variable) memoryable).getContent());
        }
    }

}
