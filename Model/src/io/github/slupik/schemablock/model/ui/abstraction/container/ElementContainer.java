package io.github.slupik.schemablock.model.ui.abstraction.container;

import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;
import io.github.slupik.schemablock.parser.execution.ExecutionFlowController;

/**
 * All rights reserved & copyright ©
 */
public interface ElementContainer {

    void run();
    void setExecutionFlowController(ExecutionFlowController controller);

    void addElement(Element element);
    Element getElement(String id) throws NextElementNotFound;
    void removeElement(String id);

    void deleteAll();

    String stringify();
    void restore(String data);
}
