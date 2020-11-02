package io.github.slupik.schemablock.model.ui.abstraction.element;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface IOElement extends StandardElement {
    String getNextElement();

    void setNextElement(String elementId);

    void removeNextElement(String elementId);

    IOCommunicable getCommunicator();

    void setCommunicator(IOCommunicable communicator);

    //TODO cleanup this mess
    void setContent(List<Data> content);

    List<Data> getContentAsList();

    interface Data {
        boolean isInput();

        String getValue();
    }
}
