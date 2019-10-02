package io.github.slupik.schemablock.javafx.element.fx.factory;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.element.special.StartUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.special.StopUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.ConditionUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.IOUiElement;
import io.github.slupik.schemablock.javafx.element.fx.element.standard.OperatingUiElement;
import io.github.slupik.schemablock.model.ui.newparser.HeapController;
import io.github.slupik.schemablock.newparser.executor.Executor;

/**
 * All rights reserved & copyright ©
 */
public class UiElementFactory {

    private UiElementFactory(){}

    public static UiElementBase createByType(UiElementType type, Executor executor, HeapController heap) {
        System.out.println("createByType executor = " + executor);
        UiElementBase element = new ConditionUiElement(executor, heap);
        switch (type) {
            case CALCULATION:
                element = new OperatingUiElement(executor, heap);
                break;
            case IF:
                element = new ConditionUiElement(executor, heap);
                break;
            case START:
                element = new StartUiElement();
                break;
            case STOP:
                element = new StopUiElement();
                break;
            case IO:
                element = new IOUiElement(executor, heap);
                break;
        }
        element.setElementSize(50,31);
        return element;
    }
}
