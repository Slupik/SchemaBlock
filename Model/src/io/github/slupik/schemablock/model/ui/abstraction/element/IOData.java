package io.github.slupik.schemablock.model.ui.abstraction.element;

/**
 * All rights reserved & copyright ©
 */
public class IOData implements IOElement.Data {

    private final boolean isInput;
    private final String value;

    public IOData(boolean isInput, String value) {
        this.isInput = isInput;
        this.value = value;
    }

    @Override
    public boolean isInput() {
        return isInput;
    }

    @Override
    public String getValue() {
        return value;
    }
}
