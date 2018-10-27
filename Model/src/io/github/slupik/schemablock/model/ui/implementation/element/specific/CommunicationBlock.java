package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
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
public class CommunicationBlock extends StandardElementBase implements OperationElement {

    private String nextElement;

    @Override
    public void setNextElement(String elementId) {
        nextElement = elementId;
    }

    @Override
    public ElementType getType() {
        return ElementType.CALCULATION;
    }

    @Override
    public void run() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, NextElementNotFound, VariableNotFound, WrongArgumentException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        onStart();
        justRunCode();
        tryRun(nextElement);
        onStop();
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[1];
        if(nextElement!=null) {
            pojo.nextBlocks[0] = nextElement;
        }
        return pojo;
    }
}
