package io.github.slupik.schemablock.model.ui.implementation;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.ConditionalElement;
import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.exception.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;
import io.github.slupik.schemablock.model.ui.parser.ElementPOJO;
import io.github.slupik.schemablock.model.ui.parser.ElementParser;
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

    private Element elementOnFalse;
    private Element elementOnTrue;

    @Override
    public ElementType getType() {
        return ElementType.START;
    }

    @Override
    public void run() throws InvalidArgumentsException, NotFoundTypeException, UnsupportedValueException, NextElementNotFound, VariableNotFound, WrongArgumentException, VariableIsAlreadyDefinedException, IncompatibleTypeException {
        if(((Boolean) runAndResult())) {
            if(elementOnTrue!=null) {
                elementOnTrue.run();
            } else if(elementOnFalse!=null) {
                elementOnFalse.run();
            } else {
                throw new NextElementNotFound();
            }
        }
    }

    @Override
    protected ElementPOJO getPOJO() {
        ElementPOJO pojo = getPreCreatedPOJO();
        pojo.nextBlocks = new String[4];
        pojo.nextBlocks[0] = "false";
        if(elementOnFalse!=null) {
            pojo.nextBlocks[1] = elementOnFalse.stringify();
        }
        pojo.nextBlocks[2] = "true";
        if(elementOnTrue!=null) {
            pojo.nextBlocks[3] = elementOnTrue.stringify();
        }
        return pojo;
    }

    @Override
    public void load(String data) throws BlockParserException {
        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);

        if(pojo.nextBlocks.length>0) {
            restoreElement(pojo.nextBlocks[0], pojo.nextBlocks[1]);

            if (pojo.nextBlocks.length > 3) {
                restoreElement(pojo.nextBlocks[2], pojo.nextBlocks[3]);
            }
        }
    }

    private void restoreElement(String trueOrFalse, String block) throws BlockParserException {
        if(block!=null && block.length()>2) {
            ElementPOJO pojoElement = new Gson().fromJson(block, ElementPOJO.class);
            Element element = ElementParser.parse(pojoElement);
            if(trueOrFalse.equalsIgnoreCase("true")) {
                setOnTrue(element);
            } else if(trueOrFalse.equalsIgnoreCase("false")) {
                setOnFalse(element);
            }
        }
    }

    @Override
    public void setOnFalse(Element element) {
        elementOnFalse = element;
    }

    @Override
    public void setOnTrue(Element element) {
        elementOnTrue = element;
    }
}
