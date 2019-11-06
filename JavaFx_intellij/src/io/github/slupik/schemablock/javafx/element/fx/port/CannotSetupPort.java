package io.github.slupik.schemablock.javafx.element.fx.port;

/**
 * All rights reserved & copyright ©
 */
public class CannotSetupPort extends Exception {
    public CannotSetupPort(String causedBy) {
        super(causedBy);
    }

    public CannotSetupPort() {
        super();
    }
}
