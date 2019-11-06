package io.github.slupik.schemablock.javafx.element.fx.sheet.old;

import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class VisibleUIContainerImpl implements VisibleUIContainer {

    private final List<DescribedBlockPrototype> list = new ArrayList<>();

    @Override
    public DescribedBlockPrototype get(String id) throws ElementNotFound {
        for(DescribedBlockPrototype element:list) {
            if(element.getElementId().equals(id)) {
                return element;
            }
        }
        throw new ElementNotFound(id);
    }

    @Override
    public void add(DescribedBlockPrototype element) {
        list.add(element);
    }

    @Override
    public void remove(String id) {
        List<DescribedBlockPrototype> toDelete = new ArrayList<>();
        for(DescribedBlockPrototype element:list) {
            if(element.getElementId().equals(id)) {
                toDelete.add(element);
            }
        }
        list.removeAll(toDelete);
    }
}
