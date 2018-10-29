package io.github.slupik.schemablock.javafx.element;

import io.github.slupik.schemablock.model.ui.abstraction.element.OperationElement;

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
}
