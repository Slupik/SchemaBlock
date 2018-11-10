package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;

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

    @Override
    protected PortInfo getBasicPortInfo(){
        PortInfo base = new PortInfo();
        base.allowForInput = true;
        base.allowForOutput = false;
        base.parentElementId = getElementId();
        return base;
    }
}
