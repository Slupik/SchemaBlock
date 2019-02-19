package io.github.slupik.schemablock.newparser.executor;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.exception.ExceptedArrayButNotReceivedException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.UnknownOperation;
import io.github.slupik.schemablock.newparser.memory.element.SimpleValue;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public interface Executor {

    void execute(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, UnknownOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException;
    void execute(Queue<ByteCommand> cmds) throws IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ValueTooBig, UnknownOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException;

    SimpleValue getResult(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, UnknownOperation, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException;
    SimpleValue getResult(Queue<ByteCommand> cmds) throws ValueTooBig, IncompatibleArrayException, UnknownOperation, IllegalOperation, IncompatibleTypeException, ExceptedArrayButNotReceivedException, IndexOutOfBoundsException;
}
