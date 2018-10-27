package io.github.slupik.schemablock.model.ui.implementation.container;

import io.github.slupik.schemablock.model.ui.abstraction.controller.ElementCallback;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
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
public class DefaultElementContainer implements ElementContainer, ElementCallback {

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
            element.registerCallback(this);
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
                element.unregisterCallback(this);
            }
        }
        for(Element element:toDelete) {
            elements.remove(element);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStop(Object result) {

    }

    @Override
    public void onTryRun(String elementId) throws NotFoundTypeException, IncompatibleTypeException, UnsupportedValueException, VariableIsAlreadyDefinedException, NextElementNotFound, WrongArgumentException, InvalidArgumentsException, VariableNotFound {
        try {
            getElement(elementId).run();
        } catch (ElementInContainerNotFound elementInContainerNotFound) {
            throw new NextElementNotFound();
        }
    }
}
