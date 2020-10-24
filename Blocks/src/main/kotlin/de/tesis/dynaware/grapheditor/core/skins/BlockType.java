package de.tesis.dynaware.grapheditor.core.skins;

/**
 * All rights reserved & copyright ©
 */
public enum BlockType {
    START("start"),
    OPERATIONS("operations"),
    CONDITION("condition"),
    IO("io"),
    STOP("stop");

    public final String code;

    BlockType(String code) {
        this.code = code;
    }
}
