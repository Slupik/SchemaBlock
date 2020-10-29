/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor.core.connections;

import de.tesis.dynaware.grapheditor.GConnectorValidator;
import de.tesis.dynaware.grapheditor.GNodeSkin;
import de.tesis.dynaware.grapheditor.SkinLookup;
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.ConditionalBlock;
import de.tesis.dynaware.grapheditor.core.skins.defaults.utils.DefaultConnectorTypes;
import de.tesis.dynaware.grapheditor.model.GConnectable;
import de.tesis.dynaware.grapheditor.model.GConnector;
import de.tesis.dynaware.grapheditor.model.GNode;

import java.util.Collection;

/**
 * Default validation rules that determine which connectors can be connected to each other.
 */
public class DefaultConnectorValidator implements GConnectorValidator {

    private final ConnectionsValidationUtils utils;
    private final SkinLookup skinLookup;

    public DefaultConnectorValidator(SkinLookup skinLookup) {
        this.skinLookup = skinLookup;
        utils = new ConnectionsValidationUtils(skinLookup);
    }

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
        } else if (utils.alreadyHasMaxOutputs(source.getParent())) {
            return false;
        } else if (source.getParent().equals(target.getParent())) {
            return false;
        }

        if (DefaultConnectorTypes.isBoth(source.getType()) || DefaultConnectorTypes.isOutput(source.getType())) {
            return DefaultConnectorTypes.isBoth(target.getType()) || DefaultConnectorTypes.isInput(target.getType());
        }
        return false;
    }

    @Override
    public String createConnectionType(final GConnector source, final GConnector target) {
        if (source.getParent() instanceof GNode) {
            GNode node = (GNode) source.getParent();
            GNodeSkin skin = skinLookup.lookupNode(node);
            if (skin instanceof ConditionalBlock) {
                if (hasTrueConnection(source.getParent())) {
                    return ConnectionType.CONDITIONAL_FALSE.name();
                } else {
                    return ConnectionType.CONDITIONAL_TRUE.name();
                }
            }
        }
        return ConnectionType.STANDARD.name();
    }

    private boolean hasTrueConnection(GConnectable parent) {
        return parent.getConnectors().stream()
                .map(GConnector::getConnections)
                .flatMap(Collection::stream)
                .anyMatch(connection -> ConnectionType.CONDITIONAL_TRUE.name().equals(connection.getType()));
    }

    @Override
    public String createJointType(final GConnector source, final GConnector target) {
        return null;
    }
}
