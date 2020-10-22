/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core.connections;

import de.tesis.dynaware.grapheditor.GConnectorValidator;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.model.GConnectable;
import de.tesis.dynaware.grapheditor.model.GConnector;

import java.util.Collection;

/**
 * Default validation rules that determine which connectors can be connected to each other.
 */
public class DefaultConnectorValidator implements GConnectorValidator {

    @Override
    public boolean prevalidate(final GConnector source, final GConnector target) {

        if (source == null || target == null) {
            return false;
        } else return !source.equals(target);
    }

    @Override
    public boolean validate(final GConnector source, final GConnector target) {

        if (source.getType() == null || target.getType() == null) {
            return false;
        } else if (alreadyHaveOutput(source.getParent())) {
            return false;
        } else if (source.getParent().equals(target.getParent())) {
            return false;
        }

        if (DefaultConnectorTypes.isBoth(source.getType()) || DefaultConnectorTypes.isOutput(source.getType())) {
            return DefaultConnectorTypes.isBoth(target.getType()) || DefaultConnectorTypes.isInput(target.getType());
        }
        return false;
    }

    private boolean alreadyHaveOutput(GConnectable parent) {
        return parent.getConnectors().stream()
                .map(GConnector::getConnections)
                .flatMap(Collection::stream)
                .anyMatch(connection -> connection.getSource().getParent().equals(parent));
    }

    @Override
    public String createConnectionType(final GConnector source, final GConnector target) {
        return null;
    }

    @Override
    public String createJointType(final GConnector source, final GConnector target) {
        return null;
    }
}
