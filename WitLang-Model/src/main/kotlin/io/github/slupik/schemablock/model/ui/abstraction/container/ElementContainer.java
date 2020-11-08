package io.github.slupik.schemablock.model.ui.abstraction.container;

import io.github.slupik.schemablock.execution.ExecutionFlowController;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;
import io.github.slupik.schemablock.model.ui.implementation.container.ExecutionCallback;
import io.github.slupik.schemablock.model.ui.implementation.container.NextElementNotFound;

/**
 * All rights reserved & copyright ©
 */
public interface ElementContainer {

    void run(ExecutionCallback callback);

    void setExecutionFlowController(ExecutionFlowController controller);

    void addElement(Element element);

    Element getElement(String id) throws NextElementNotFound;

    void removeElement(String id);

    void deleteAll();

    String stringify();

    void restore(String data);
}
