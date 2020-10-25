package de.tesis.dynaware.grapheditor.core.skins;

/**
 * All rights reserved & copyright ©
 */
public enum UiElementType {
    START("start"),
    OPERATIONS("operations"),
    CONDITION("condition"),
    IO("io"),
    STOP("stop");

    public final String code;

    UiElementType(String code) {
        this.code = code;
    }
}
