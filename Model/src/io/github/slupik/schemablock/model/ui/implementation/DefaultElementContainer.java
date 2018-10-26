package io.github.slupik.schemablock.model.ui.implementation;

import io.github.slupik.schemablock.model.ui.abstraction.Element;
import io.github.slupik.schemablock.model.ui.abstraction.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.exception.NextElementNotFound;
import io.github.slupik.schemablock.parser.code.CodeParser;
import io.github.slupik.schemablock.parser.code.IncompatibleTypeException;
import io.github.slupik.schemablock.parser.code.VariableNotFound;
import io.github.slupik.schemablock.parser.code.WrongArgumentException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.InvalidArgumentsException;
import io.github.slupik.schemablock.parser.math.rpn.pattern.UnsupportedValueException;
import io.github.slupik.schemablock.parser.math.rpn.variable.VariableIsAlreadyDefinedException;
import io.github.slupik.schemablock.parser.math.rpn.variable.value.NotFoundTypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class DefaultElementContainer implements ElementContainer {

    private final List<Element> elements = new ArrayList<>();
    private Element start;

    @Override
    public void run() throws NotFoundTypeException, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, NextElementNotFound, WrongArgumentException, InvalidArgumentsException, VariableNotFound, StartBlockNotFound {

        CodeParser.clearHeap();

        if(start!=null) {
            start.run();
        } else {
            throw new StartBlockNotFound();
        }
    }

    @Override
    public void addElement(Element element) {
        if(element!=null) {
            if(element.getType() == ElementType.START) {
                if(start!=null) {
                    removeElement(element.getId());
                }
                start = element;
            }
            elements.add(element);
        }
    }

    @Override
    public Element getElement(String id) throws ElementInContainerNotFound {
        for(Element element:elements) {
            if(element!=null && element.getId().equalsIgnoreCase(id)) {
                return element;
            }
        }
        throw new ElementInContainerNotFound("Wrong id: "+id);
    }

    @Override
    public void removeElement(String id) {
        List<Element> toDelete = new ArrayList<>();
        for(Element element:elements) {
            if(element!=null && element.getId().equalsIgnoreCase(id)) {
                if(element.getType()==ElementType.START) {
                    start = null;
                }
                toDelete.add(element);
            }
        }
        for(Element element:toDelete) {
            elements.remove(element);
        }
    }
}
