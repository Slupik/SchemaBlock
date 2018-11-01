package io.github.slupik.schemablock.javafx.view;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.special.StartElement;
import io.github.slupik.schemablock.javafx.element.fx.special.StopElement;
import io.github.slupik.schemablock.javafx.element.fx.standard.ConditionBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.IOBlock;
import io.github.slupik.schemablock.javafx.element.fx.standard.OperatingBlock;

/**
 * All rights reserved & copyright ©
 */
public class UiElementFactory {

    private UiElementFactory(){}

    public static UiElementBase createByType(UiElementType type) {
        UiElementBase element = new ConditionBlock();
        switch (type) {
            case CALCULATION:
                element = new OperatingBlock();
                break;
            case IF:
                element = new ConditionBlock();
                break;
            case START:
                element = new StartElement();
                break;
            case STOP:
                element = new StopElement();
                break;
            case IO:
                element = new IOBlock();
                break;
        }
        element.setElementSize(50,31);
        return element;
    }
}
