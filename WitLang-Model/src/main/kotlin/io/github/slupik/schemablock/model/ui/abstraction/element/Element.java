package io.github.slupik.schemablock.model.ui.abstraction.element;

import io.github.slupik.schemablock.model.ui.abstraction.ElementType;
import io.github.slupik.schemablock.model.ui.abstraction.controller.ElementCallback;
import io.github.slupik.schemablock.model.ui.error.AlgorithmException;
import io.github.slupik.schemablock.model.ui.parser.BlockParserException;

/**
 * All rights reserved & copyright ©
 */
public interface Element {
    ElementType getType();

    void run() throws AlgorithmException;
    String stringify();
    void load(String data) throws BlockParserException;

    void registerCallback(ElementCallback callback);
    void unregisterCallback(ElementCallback callback);

    String getId();

    void setState(ElementState state);

}
