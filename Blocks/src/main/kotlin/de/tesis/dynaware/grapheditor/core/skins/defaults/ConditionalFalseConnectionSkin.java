/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core.skins.defaults;

import de.tesis.dynaware.grapheditor.core.skins.defaults.connection.SimpleConnectionSkin;
import de.tesis.dynaware.grapheditor.model.GConnection;

/**
 * The default connection skin.
 *
 * <p>
 * Extension of {@link SimpleConnectionSkin} that provides a mechanism for creating and removing joints.
 * </p>
 */
public class ConditionalFalseConnectionSkin extends ConditionalConnectionSkin {

    private static final String STYLE_CLASS = "conditional-connection-false";

    /**
     * Creates a new default connection skin instance.
     *
     * @param connection the {@link GConnection} the skin is being created for
     */
    public ConditionalFalseConnectionSkin(GConnection connection) {
        super(connection);
    }

    @Override
    protected String getStyleClass() {
        return STYLE_CLASS;
    }
}
