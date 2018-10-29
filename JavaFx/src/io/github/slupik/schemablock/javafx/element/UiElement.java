package io.github.slupik.schemablock.javafx.element;

import io.github.slupik.schemablock.model.ui.abstraction.container.ElementContainer;
import io.github.slupik.schemablock.model.ui.abstraction.element.Element;

/**
 * All rights reserved & copyright ©
 */
public interface UiElement {
    void setElementSize(double width, double height);
    void setElementWidth(double width);
    void setElementHeight(double height);

    void setDesc(String desc);

    void setLogicElement(Element element);
    Element getLogicElement();

    String getElementId();

    String stringify();
    void restore(String data, ElementContainer container);
}
