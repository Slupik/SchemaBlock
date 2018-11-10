package io.github.slupik.schemablock.javafx.element.fx.special;

import io.github.slupik.schemablock.javafx.element.UiElementType;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;

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

    @Override
    protected PortInfo getBasicPortInfo(){
        PortInfo base = new PortInfo();
        base.allowForInput = false;
        base.allowForOutput = true;
        base.parentElementId = getElementId();
        return base;
    }
}
