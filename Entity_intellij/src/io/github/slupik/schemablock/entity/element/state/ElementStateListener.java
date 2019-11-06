package io.github.slupik.schemablock.entity.element.state;

/**
 * All rights reserved & copyright ©
 */
@FunctionalInterface
public interface ElementStateListener {

    void onChange(ElementState newState);

}
