package io.github.slupik.schemablock.javafx.element.fx.element.standard;

import io.github.slupik.schemablock.javafx.element.ElementSizeBinder;
import io.github.slupik.schemablock.javafx.element.fx.element.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class UiStandardElement extends UiElementBase implements ElementSizeBinder.Input {

    @Override
    protected ElementSizeBinder.Input getBinderInput() {
        return this;
    }

    @Override
    protected void onPostInit() {
        super.onPostInit();
        setDesc(getDefaultDesc());
        setElementSize(150, 93);
    }

    protected abstract String getDefaultDesc();

    public void setContent(String content){
        getElement().setContent(content);
    }

    public String getContent(){
        return getElement().getContent();
    }

    @Override
    public void setDesc(String desc) {
        getDescLabel().setText(desc);
    }

    @Override
    protected String getDesc() {
        return getDescLabel().getText();
    }

    private OperationElement getElement(){
        return ((OperationElement) getLogicElement());
    }

    @Override
    public List<PortInfo> getPortsInfo() {
        List<PortInfo> list = new ArrayList<>();

        PortInfo up = getBasicPortInfo();
        up.percentOfHeight = 0;
        up.percentOfWidth = 0.5;
        up.positionName = "top-middle";
        list.add(up);

        PortInfo right = getBasicPortInfo();
        right.percentOfHeight = 0.5;
        right.percentOfWidth = 1;
        right.positionName = "middle-right";
        list.add(right);

        PortInfo down = getBasicPortInfo();
        down.percentOfHeight = 1;
        down.percentOfWidth = 0.5;
        down.positionName = "down-middle";
        list.add(down);

        PortInfo left = getBasicPortInfo();
        left.percentOfHeight = 0.5;
        left.percentOfWidth = 0;
        left.positionName = "middle-left";
        list.add(left);

        return list;
    }

    protected PortInfo getBasicPortInfo(){
        PortInfo base = new PortInfo();
        base.allowForInput = true;
        base.allowForOutput = true;
        base.parentElementId = getElementId();
        return base;
    }

    @Override
    protected boolean canBeDeleted() {
        return true;
    }
}
