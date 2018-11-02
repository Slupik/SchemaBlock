package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;

/**
 * All rights reserved & copyright ©
 */
public class StopElement extends UiSpecialElement {

    @Override
    protected void onPostInit() {
        super.onPostInit();
        setDesc("STOP");
    }

    @Override
    public UiElementType getType() {
        return UiElementType.STOP;
    }
}
