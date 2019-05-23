package io.github.slupik.schemablock.model.ui.abstraction.element;

import io.github.slupik.schemablock.model.ui.implementation.element.specific.IOCommunicable;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface IOElement extends StandardElement {
    void setNextElement(String elementId);
    String getNextElement();
    void removeNextElement(String elementId);

    void setCommunicator(IOCommunicable communicator);
    IOCommunicable getCommunicator();

    //TODO cleanup this mess
    void setContent(List<Data> content);
    List<Data> getContentAsList();

    interface Data {
        boolean isInput();
        String getValue();
    }
}
