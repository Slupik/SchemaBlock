package io.github.slupik.schemablock.newparser.executor.implementation;

import io.github.slupik.schemablock.InternalException;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.error.UnknownError;
import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.*;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.SimpleValueExpected;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.UnknownMemoryElement;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.UnknownOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.exception.VariableIsNotArray;
import io.github.slupik.schemablock.newparser.function.Function;
import io.github.slupik.schemablock.newparser.function.FunctionContainer;
import io.github.slupik.schemablock.newparser.function.FunctionExecutor;
import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction;
import io.github.slupik.schemablock.newparser.memory.Memory;
import io.github.slupik.schemablock.newparser.memory.Register;
import io.github.slupik.schemablock.newparser.memory.element.*;
import io.github.slupik.schemablock.newparser.utils.CodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * All rights reserved & copyright ©
 */
class ByteCodeExe {

    static void execute(Queue<ByteCommand> cmds, Memory memory, Register register, FunctionContainer functionContainer, FunctionExecutor executor) throws AlgorithmException {
        for (ByteCommand cmd : cmds) {
            try {
                execute(cmd, memory, register, functionContainer, executor);
            } catch (Throwable throwable) {
                if (throwable instanceof AlgorithmException) {
                    throw ((AlgorithmException) throwable);
                } else {
                    throwable.printStackTrace();
                    throw new UnknownError();
                }
            }
        }
    }

    private static void execute(ByteCommand cmd,
                                Memory memory,
                                Register register,
                                FunctionContainer functionContainer,
                                FunctionExecutor executor) throws AlgorithmException, InternalException {
        switch (cmd.getCommandType()) {
            case HEAP_VAR: {
                ByteCommandHeapVariable bc = ((ByteCommandHeapVariable) cmd);
                Variable var = memory.get(bc.getName());

                if (bc.getIndexes() > 0) {
                    Value value = var.getContent();
                    if (value.isArray()) {
                        Array array = ((Array) value);

                        Value[] args = getValues(register, bc.getIndexes());
                        int[] indexes = new int[args.length];
                        if (args.length > 0) {
                            for (int i = 0; i < args.length; i++) {
                                Value arg = args[i];
                                if (arg instanceof SimpleValue) {
                                    indexes[args.length - 1 - i] = ((SimpleValue) arg).getCastedValue();
                                } else {
                                    throw new SimpleValueExpected(bc.getLine(), bc.getPosition());
                                }
                            }
                        }

                        register.add(array.getCell(indexes));
                    } else {
                        throw new VariableIsNotArray(var.getName(), bc.getLine(), bc.getPosition());
                    }
                } else {
                    register.add(var);
                }
                break;
            }
            case HEAP_VALUE: {
                ByteCommandHeapValue bc = ((ByteCommandHeapValue) cmd);
                SimpleValue value = new SimpleValueImpl(bc.getValueType(), bc.getRawValue());
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

                if (bc.getSymbol().equals("=")) {
                    Value val = pollValue(register);
                    Memoryable container = register.pop();
                    if (container instanceof Variable) {
                        ((Variable) container).setContent(val);
                    } else if (container instanceof ArrayCell) {
                        ((ArrayCell) container).setValue(val);
                    }

                    break;
                }

                int argsCount = CodeUtils.getArgsCount(bc.getSymbol());
                SimpleValue[] args = new SimpleValue[argsCount];
                for (int i = 0; i < argsCount; i++) {
                    Value val = pollValue(register);
                    if (val.isArray()) {
                        throw new SimpleValueExpected(bc.getLine(), bc.getPosition());
                    } else {
                        args[argsCount - 1 - i] = ((SimpleValue) val);
                    }
                }

                switch (bc.getSymbol()) {
                    case "+": {
                        register.add(MathOperationExecutor.add(args[0], args[1], bc));
                        break;
                    }
                    case "-": {
                        register.add(MathOperationExecutor.subtract(args[0], args[1], bc));
                        break;
                    }
                    case "*": {
                        register.add(MathOperationExecutor.multiply(args[0], args[1], bc));
                        break;
                    }
                    case "/": {
                        register.add(MathOperationExecutor.divide(args[0], args[1], true, bc));
                        break;
                    }
                    case "\\": {
                        register.add(MathOperationExecutor.divide(args[0], args[1], false, bc));
                        break;
                    }
                    case "%": {
                        register.add(MathOperationExecutor.modulo(args[0], args[1], bc));
                        break;
                    }

                    case "<<": {
                        register.add(BitwiseOperationExecutor.leftShift(args[0], args[1], bc));
                        break;
                    }
                    case ">>": {
                        register.add(BitwiseOperationExecutor.rightShift(args[0], args[1], bc));
                        break;
                    }
                    case "&": {
                        register.add(BitwiseOperationExecutor.and(args[0], args[1], bc));
                        break;
                    }
                    case "^": {
                        register.add(BitwiseOperationExecutor.xor(args[0], args[1], bc));
                        break;
                    }
                    case "|": {
                        register.add(BitwiseOperationExecutor.or(args[0], args[1], bc));
                        break;
                    }
                    case "~": {
                        register.add(BitwiseOperationExecutor.not(args[0], bc));
                        break;
                    }

                    case "&&": {
                        register.add(LogicOperationExecutor.and(args[0], args[1], bc));
                        break;
                    }
                    case "||": {
                        register.add(LogicOperationExecutor.or(args[0], args[1], bc));
                        break;
                    }
                    case "!": {
                        register.add(LogicOperationExecutor.not(args[0], bc));
                        break;
                    }

                    case "<=": {
                        register.add(ComparisonOperationExecutor.smallerOrEqual(args[0], args[1], bc));
                        break;
                    }
                    case ">=": {
                        register.add(ComparisonOperationExecutor.greaterOrEqual(args[0], args[1], bc));
                        break;
                    }
                    case "<": {
                        register.add(ComparisonOperationExecutor.smaller(args[0], args[1], bc));
                        break;
                    }
                    case ">": {
                        register.add(ComparisonOperationExecutor.greater(args[0], args[1], bc));
                        break;
                    }
                    case "==": {
                        register.add(ComparisonOperationExecutor.equal(args[0], args[1], bc));
                        break;
                    }
                    case "!=": {
                        register.add(ComparisonOperationExecutor.notEqual(args[0], args[1], bc));
                        break;
                    }

                    default: {
                        throw new UnknownOperation(bc.getSymbol(), bc.getLine(), bc.getPosition());
                    }
                }
                break;
            }
            case HEAP_VIRTUAL_ARRAY: {
                ByteCommandHeapVirArr bc = ((ByteCommandHeapVirArr) cmd);
                Value[] values = getValues(register, bc.getElementsCount());

                if (bc.isEmpty()) {
                    int[] indexes = new int[values.length];
                    for (int i = 0; i < values.length; i++) {
                        Value value = values[i];
                        if (value instanceof SimpleValue) {
                            indexes[values.length - i - 1] = ((SimpleValue) value).getCastedValue();
                        } else {
                            throw new SimpleValueExpected(bc.getLine(), bc.getPosition());
                        }
                    }

                    List<Array> arrays = new ArrayList<>();
                    int dimensions = bc.getElementsCount();
                    for (int index : indexes) {
                        if (arrays.isEmpty()) {
                            Array array = new ArrayImpl(bc.getType(), dimensions, index);
                            register.add(array);
                            arrays.add(array);
                        } else {
                            List<Array> toFill = arrays;
                            arrays = new ArrayList<>();
                            for (Array elementToFill : toFill) {
                                for (ArrayCell cell : elementToFill.getCells()) {
                                    Array array = new ArrayImpl(bc.getType(), dimensions, index);
                                    cell.setValue(array);
                                    arrays.add(array);
                                }
                            }
                        }
                        dimensions--;
                    }
                } else {
                    int dimensions = 1;
                    if (values[0].isArray()) {
                        dimensions += ((Array) values[0]).getDimensionsCount();
                    }
                    Array array = new ArrayImpl(bc.getType(), dimensions, bc.getElementsCount());
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] instanceof Array) {
                            array.setValue(new int[]{i}, (Array) values[values.length - 1 - i]);
                        } else if (values[i] instanceof SimpleValue) {
                            array.setValue(new int[]{i}, (SimpleValue) values[values.length - 1 - i]);
                        } else {
                            throw new UnknownMemoryElement();
                        }
                    }
                    register.add(array);
                }
                break;
            }
            case EXECUTE: {
                ByteCommandExecute bc = ((ByteCommandExecute) cmd);

                List<Value> args = new ArrayList<>();
                for (int i = 0; i < bc.getArgsCount(); i++) {
                    args.add(pollValue(register));
                }

                List<Function> matchingFunctions = functionContainer.getMatchingFunctions(bc.getName());
                Value result = executor.execute(matchingFunctions, args, bc);
                if (result == null) {
                    throw new NoMatchingFunction(
                            bc.getName(),
                            args
                                    .stream()
                                    .map(Memoryable::getType)
                                    .collect(Collectors.toList()),
                            bc.getPosition(),
                            bc.getLine()
                    );
                }

                if (result.getType() != ValueType.VOID) {
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

    private static Value[] getValues(Register register, int amount) throws InternalException {
        Value[] args = new Value[amount];
        for (int i = 0; i < amount; i++) {
            Value val = pollValue(register);
            args[amount - 1 - i] = val;
        }
        return args;
    }

    private static Value pollValue(Register register) throws InternalException {
        Memoryable memoried = register.pop();

        Value value = null;
        if (memoried instanceof Variable) {
            value = ((Variable) memoried).getContent();
        } else if (memoried instanceof ArrayCell) {
            value = ((ArrayCell) memoried).getValue();
        } else if (memoried instanceof SimpleValue) {
            value = ((SimpleValue) memoried);
        } else if (memoried instanceof Array) {
            value = ((Array) memoried);
        } else {
            throw new UnknownMemoryElement();
        }
        return value;
    }
}
