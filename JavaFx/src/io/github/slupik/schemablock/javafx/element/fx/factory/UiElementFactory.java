package io.github.slupik.schemablock.javafx.element.fx.factory;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.element.special.StartUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.special.StopUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.ConditionUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.IOUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.OperatingUiElement;

/**
 * All rights reserved & copyright ©
 */
public class UiElementFactory {

    private UiElementFactory(){}

    public static UiElementBase createByType(UiElementType type) {
        UiElementBase element = new ConditionUiElement();
        switch (type) {
            case CALCULATION:
                element = new OperatingUiElement();
                break;
            case IF:
                element = new ConditionUiElement();
                break;
            case START:
                element = new StartUiElement();
                break;
            case STOP:
                element = new StopUiElement();
                break;
            case IO:
                element = new IOUiElement();
                break;
        }
        element.setElementSize(50,31);
        return element;
    }
}
