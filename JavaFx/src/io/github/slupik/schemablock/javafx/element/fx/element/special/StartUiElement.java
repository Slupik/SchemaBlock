package io.github.slupik.schemablock.javafx.element.fx.element.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import io.github.slupik.schemablock.model.ui.implementation.element.specific.StartBlock;

/**
 * All rights reserved & copyright ©
 */
public class StartUiElement extends UiSpecialElement {

    @Override
    protected void onPostInit() {
        super.onPostInit();
        setDesc("START");
        element = new StartBlock();
    }

    @Override
    public UiElementType getType() {
        return UiElementType.START;
    }

    @Override
    protected PortInfo getBasicPortInfo(){
        PortInfo base = new PortInfo();
        base.allowForInput = false;
        base.allowForOutput = true;
        base.parentElementId = getElementId();
        return base;
    }

    @Override
    protected boolean canBeDeleted() {
        return false;
    }
}
