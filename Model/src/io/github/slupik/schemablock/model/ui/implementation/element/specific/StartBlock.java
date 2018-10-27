package io.github.slupik.schemablock.model.ui.implementation.element.specific;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.element.StartElement;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.model.ui.implementation.element.ElementBase;
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
public class StartBlock extends ElementBase implements StartElement {

    private String nextElement;

    @Override
    public ElementType getType() {
        return ElementType.START;
    }

    @Override
    public void setNextElement(String elementId) {
        nextElement = elementId;
    }

    @Override
    public void run() throws NextElementNotFound, NotFoundTypeException, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, InvalidArgumentsException {
        onStart();
        tryRun(nextElement);
        onStop();
    }

    @Override
    public String stringify() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        pojo.nextBlocks = new String[1];
        pojo.nextBlocks[0] = nextElement;
        return new Gson().toJson(pojo);
    }

    @Override
    protected void load(ElementPOJO pojo) throws BlockParserException {
        nextElement = pojo.nextBlocks[0];
    }
}
