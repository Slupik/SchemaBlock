package io.github.slupik.schemablock.newparser.executor;

import io.github.slupik.schemablock.newparser.bytecode.bytecommand.abstraction.ByteCommand;
import io.github.slupik.schemablock.newparser.compilator.exception.ComExIllegalEscapeChar;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleArrayException;
import io.github.slupik.schemablock.newparser.compilator.exception.IncompatibleTypeException;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;

import java.util.Queue;

/**
 * All rights reserved & copyright ©
 */
public interface Executor {

    void execute(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar, IncompatibleArrayException, IncompatibleTypeException, IllegalOperation;
    void execute(Queue<ByteCommand> cmds) throws IncompatibleArrayException, IncompatibleTypeException, IllegalOperation;

    Object getResult(String code) throws ValueTooBig, NameForDeclarationCannotBeFound, ExceptedTypeOfArray, ComExIllegalEscapeChar;
    Object getResult(Queue<ByteCommand> cmds);
}
