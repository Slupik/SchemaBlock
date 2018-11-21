package io.github.slupik.schemablock.model.ui.abstraction.element;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface IOElement extends StandardElement {
    void setNextElement(String elementId);
    String getNextElement();
    void removeNextElement(String elementId);

    //TODO cleanup this mess
    void setContent(List<Data> content);
    List<Data> getContentAsList();

    interface Data {
        boolean isInput();
        String getValue();
    }
}
