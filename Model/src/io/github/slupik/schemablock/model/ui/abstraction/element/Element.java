package io.github.slupik.schemablock.model.ui.abstraction.element;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.controller.ElementCallback;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.newparser.compilator.exception.IndexOutOfBoundsException;
import io.github.slupik.schemablock.newparser.compilator.exception.*;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.ExceptedTypeOfArray;
import io.github.slupik.schemablock.newparser.compilator.implementation.compilator.NameForDeclarationCannotBeFound;
import io.github.slupik.schemablock.newparser.executor.implementation.IllegalOperation;
import io.github.slupik.schemablock.newparser.executor.implementation.UnknownOperation;
import io.github.slupik.schemablock.newparser.function.exception.NoMatchingFunction;
import io.github.slupik.schemablock.newparser.utils.ValueTooBig;
import io.github.slupik.schemablock.both.execution.VariableNotFound;

/**
 * All rights reserved & copyright ©
 */
public interface Element {
    ElementType getType();

    void run() throws UnknownOperation, IncompatibleArrayException, ExceptedArrayButNotReceivedException, ExceptedTypeOfArray, ValueTooBig, IndexOutOfBoundsException, IncompatibleTypeException, IllegalOperation, VariableNotFound, ComExIllegalEscapeChar, NoMatchingFunction, NameForDeclarationCannotBeFound;
    String stringify();
    void load(String data) throws BlockParserException;

    void registerCallback(ElementCallback callback);
    void unregisterCallback(ElementCallback callback);

    String getId();
}
