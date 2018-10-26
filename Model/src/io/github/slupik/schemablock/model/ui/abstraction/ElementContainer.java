package io.github.slupik.schemablock.model.ui.abstraction;

import io.github.slupik.schemablock.model.ui.exception.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.ElementInContainerNotFound;
import io.github.slupik.schemablock.model.ui.implementation.StartBlockNotFound;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

/**
 * All rights reserved & copyright ©
 */
public interface ElementContainer {

    void run() throws NotFoundTypeException, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, NextElementNotFound, WrongArgumentException, InvalidArgumentsException, VariableNotFound, StartBlockNotFound;

    void addElement(Element element);
    Element getElement(String id) throws ElementInContainerNotFound;
    void removeElement(String id);
}
