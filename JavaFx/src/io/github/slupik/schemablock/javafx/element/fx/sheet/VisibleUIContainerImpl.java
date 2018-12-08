package io.github.slupik.schemablock.javafx.element.fx.sheet;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class VisibleUIContainerImpl implements VisibleUIContainer {

    private final List<UiElementBase> list = new ArrayList<>();

    @Override
    public UiElementBase get(String id) throws ElementNotFound {
        for(UiElementBase element:list) {
            if(element.getElementId().equals(id)) {
                return element;
            }
        }
        throw new ElementNotFound(id);
    }

    @Override
    public void add(UiElementBase element) {
        list.add(element);
    }

    @Override
    public void remove(String id) {
        List<UiElementBase> toDelete = new ArrayList<>();
        for(UiElementBase element:list) {
            if(element.getElementId().equals(id)) {
                toDelete.add(element);
            }
        }
        list.removeAll(toDelete);
    }
}
