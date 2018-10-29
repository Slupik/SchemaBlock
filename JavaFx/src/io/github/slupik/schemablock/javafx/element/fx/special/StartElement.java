package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.UiSpecialElement;

/**
 * All rights reserved & copyright ©
 */
public class StartElement extends UiSpecialElement {

    @Override
    protected void onPostInit() {
        super.onPostInit();
        setDesc("START");
    }

    @Override
    protected UiElementType getType() {
        return UiElementType.START;
    }
}
