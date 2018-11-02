package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;

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
    public UiElementType getType() {
        return UiElementType.START;
    }
}
