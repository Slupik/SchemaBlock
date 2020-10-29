package de.tesis.dynaware.grapheditor.core.skins.defaults.connector;

/**
 * All rights reserved & copyright ©
 */
public enum ConnectorType {
    INPUT("input"),
    OUTPUT("output"),
    BOTH("both");

    private final String code;

    ConnectorType(String code) {
        this.code = code;
    }

    public static ConnectorType getType(String value) {
        if (value.contains(INPUT.code)) {
            return INPUT;
        }
        if (value.contains(OUTPUT.code)) {
            return OUTPUT;
        }
        if (value.contains(BOTH.code)) {
            return BOTH;
        }
        return null;
    }

}
