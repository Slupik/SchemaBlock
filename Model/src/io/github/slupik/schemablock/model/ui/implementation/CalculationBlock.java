package io.github.slupik.schemablock.model.ui.implementation;

import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.OperationElement;
import io.github.slupik.schemablock.model.ui.exception.NextElementNotFound;
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
public class CalculationBlock extends StandardElementBase implements OperationElement {

    private Element nextElement;

    @Override
    public void setNextElement(Element element) {
        nextElement = element;
    }

    @Override
    public ElementType getType() {
        return ElementType.CALCULATION;
    }

    @Override
    public void run() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, NextElementNotFound, VariableNotFound, WrongArgumentException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        justRunCode();
        if(nextElement!=null) {
            nextElement.run();
        } else {
            throw new NextElementNotFound();
        }
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[1];
        if(nextElement!=null) {
            pojo.nextBlocks[0] = nextElement.stringify();
        }
        return pojo;
    }
}
