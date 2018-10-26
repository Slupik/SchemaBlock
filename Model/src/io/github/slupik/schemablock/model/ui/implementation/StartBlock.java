package io.github.slupik.schemablock.model.ui.implementation;

import com.google.gson.Gson;
import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.StartElement;
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
public class StartBlock extends ElementBase implements StartElement {

    private Element nextElement;

    @Override
    public ElementType getType() {
        return ElementType.START;
    }

    @Override
    public void setNextElement(Element element) {
        nextElement = element;
    }

    @Override
    public void run() throws NextElementNotFound, NotFoundTypeException, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, VariableNotFound, WrongArgumentException, InvalidArgumentsException {
        if(nextElement!=null) {
            nextElement.run();
        } else {
            throw new NextElementNotFound();
        }
    }

    @Override
    public String stringify() {
        ElementPOJO pojo = new ElementPOJO();
        pojo.elementType = getType();
        return new Gson().toJson(pojo);
    }

    @Override
    public void load(String data) {
//        ElementPOJO pojo = new Gson().fromJson(data, ElementPOJO.class);
    }
}
