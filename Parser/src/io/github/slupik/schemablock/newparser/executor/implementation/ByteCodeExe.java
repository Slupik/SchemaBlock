package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.*;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.element.*;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
class ByteCodeExe {

    static void execute(Queue<ByteCommand> cmds, Memory memory, Register register) throws IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ValueTooBig {

        //TODO remove
        for(ByteCommand cmd:cmds) {
            System.out.println("cmd.toString() = " + cmd.toString());
        }

        for(ByteCommand cmd:cmds) {
            switch (cmd.getCommandType()) {
                case HEAP_VAR: {
                    ByteCommandHeapVariable bc = ((ByteCommandHeapVariable) cmd);
                    Memoryable var = memory.get(bc.getName());
                    register.add(var);
                    break;
                }
                case HEAP_VALUE: {
                    ByteCommandHeapValue bc = ((ByteCommandHeapValue) cmd);
                    Value value = new ValueImpl(bc.getValueType(), bc.getRawValue());
                    register.add(value);
                    break;
                }
                case DECLARE_VAR: {
                    ByteCommandDeclareVar bc = ((ByteCommandDeclareVar) cmd);
                    Variable var = new VariableImpl(bc.getType(), bc.getDimensionsCount(), bc.getName());
                    memory.register(var);
                    break;
                }
                case OPERATION: {
                    ByteCommandOperation bc = ((ByteCommandOperation) cmd);

                    if(bc.getSymbol().equals("=")) {
                        Value val = pollValue(register);
                        Variable var = (Variable) register.pop();

                        var.setValue(val);
                        break;
                    }

                    int argsCount = CodeUtils.getArgsCount(bc.getSymbol());
                    Value[] args = new Value[argsCount];
                    for(int i=0;i<argsCount;i++) {
                        Value val = pollValue(register);
                        args[i] = val;
                    }

                    switch (bc.getSymbol()) {
                        //TODO add all operations
                        case "+": {
                            register.add(MathOperationExecutor.add(args[0], args[1]));
                            break;
                        }
                        case "-": {
                            register.add(MathOperationExecutor.subtract(args[0], args[1]));
                            break;
                        }
                        case "*": {
                            register.add(MathOperationExecutor.multiply(args[0], args[1]));
                            break;
                        }
                        case "/": {
                            register.add(MathOperationExecutor.divide(args[0], args[1], true));
                            break;
                        }
                        case "\\": {
                            register.add(MathOperationExecutor.divide(args[0], args[1], false));
                            break;
                        }
                        case "%": {
                            register.add(MathOperationExecutor.modulo(args[0], args[1]));
                            break;
                        }

                        case "<<": {
                            register.add(BitwiseOperationExecutor.leftShift(args[0], args[1]));
                            break;
                        }
                        case ">>": {
                            register.add(BitwiseOperationExecutor.rightShift(args[0], args[1]));
                            break;
                        }
                        case "&": {
                            register.add(BitwiseOperationExecutor.and(args[0], args[1]));
                            break;
                        }
                        case "^": {
                            register.add(BitwiseOperationExecutor.xor(args[0], args[1]));
                            break;
                        }
                        case "|": {
                            register.add(BitwiseOperationExecutor.or(args[0], args[1]));
                            break;
                        }
                    }

                    break;
                }
                case HEAP_VIRTUAL_ARRAY: {
                    //TODO implement
                }
                case EXECUTE: {
                    System.out.println("EXECUTE");
                    ByteCommandExecute bc = ((ByteCommandExecute) cmd);
                    if(bc.getName().equals("sqrt")) {
                        Value value = pollValue(register);
                        Value result = new ValueImpl(ValueType.DOUBLE, Math.sqrt(((Integer) value.getValue())));
                        register.add(result);
                    }
                    break;
                }
                case CLEAR_EXEC_HEAP: {
                    register.clear();
                    break;
                }
            }
        }
    }

    private static Value pollValue(Register register) {
        Memoryable memoried = register.pop();

        Value value = null;
        if(memoried instanceof Variable) {
            value = ((Variable) memoried).getValue();
        } else if(memoried instanceof Value) {
            value = ((Value) memoried);
        }
        return value;
    }
}
