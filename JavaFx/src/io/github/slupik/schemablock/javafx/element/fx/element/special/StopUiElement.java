package io.github.slupik.schemablock.javafx.element.fx.element.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.StopBlock;

/**
 * All rights reserved & copyright ©
 */
public class StopUiElement extends UiSpecialElement {

    @Override
    protected void onPostInit() {
        super.onPostInit();
        setDesc("STOP");
        element = new StopBlock();
    }

    @Override
    public UiElementType getType() {
        return UiElementType.STOP;
    }

    @Override
    protected PortInfo getBasicPortInfo(){
        PortInfo base = new PortInfo();
        base.allowForInput = true;
        base.allowForOutput = false;
        base.parentElementId = getElementId();
        return base;
    }

    @Override
    protected boolean canBeDeleted() {
        return true;
    }
}
