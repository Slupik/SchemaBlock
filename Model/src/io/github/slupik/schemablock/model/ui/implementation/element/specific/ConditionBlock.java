package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.ConditionalElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.StandardElementBase;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
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
public class ConditionBlock extends StandardElementBase implements ConditionalElement {

    private String elementOnFalse = "";
    private String elementOnTrue = "";

    @Override
    public ElementType getType() {
        return ElementType.CONDITION;
    }

    @Override
    public void run() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, NextElementNotFound, VariableNotFound, WrongArgumentException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        onStart();
        if(((Boolean) runAndGetResult())) {
            tryRun(elementOnTrue);
        } else {
            tryRun(elementOnFalse);
        }
        onStop();
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[2];
        if(elementOnFalse!=null && elementOnFalse.length()>0) {
            pojo.nextBlocks[0] = elementOnFalse+";"+"false";
        }
        if(elementOnTrue!=null && elementOnTrue.length()>0) {
            pojo.nextBlocks[1] = elementOnTrue+";"+"true";
        }
        return pojo;
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        super.load(pojo);
        for(String element:pojo.nextBlocks) {
            restoreElement(element);
        }
    }

    private void restoreElement(String element) throws BlockParserException {
        String id = element.split(";")[0];
        String type = element.split(";")[1];

        if(type.equalsIgnoreCase("false")) {
            setOnFalse(id);
        } else {
            setOnTrue(id);
        }
    }

    @Override
    public void setOnFalse(String elementId) {
        elementOnFalse = elementId;
    }

    @Override
    public void setOnTrue(String elementId) {
        elementOnTrue = elementId;
    }

    @Override
    public String getOnFalse() {
        return elementOnFalse;
    }

    @Override
    public String getOnTrue() {
        return elementOnTrue;
    }

    @Override
    public void removeOnFalse(String elementId) {
        if(elementOnFalse.equals(elementId)) {
            elementOnFalse = "";
        }
    }

    @Override
    public void removeOnTrue(String elementId) {
        if(elementOnTrue.equals(elementId)) {
            elementOnTrue = "";
        }
    }
}
