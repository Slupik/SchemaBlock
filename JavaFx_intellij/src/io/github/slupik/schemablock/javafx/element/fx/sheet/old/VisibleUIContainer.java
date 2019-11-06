package io.github.slupik.schemablock.javafx.element.fx.sheet.old;

import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;

/**
 * All rights reserved & copyright ©
 */
public interface VisibleUIContainer {
    DescribedBlockPrototype get(String id) throws ElementNotFound;
    void add(DescribedBlockPrototype element);
    void remove(String id);
}
