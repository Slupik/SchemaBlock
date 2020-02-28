package io.github.slupik.schemablock.javafx.element.fx.port.spawner.old;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;

/**
 * All rights reserved & copyright ©
 */
public class ElementIsNotVisible extends Throwable {
    public ElementIsNotVisible(String id, ElementType type) {
        super("Logic element with id: "+id+" and with type: "+type+ " should be instance of one of the visible objects.");
    }
}
