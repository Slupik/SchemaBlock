package de.tesis.dynaware.grapheditor.core.connections;

import de.tesis.dynaware.grapheditor.model.GConnectable;
import de.tesis.dynaware.grapheditor.model.GConnector;

import java.util.Collection;

/**
 * All rights reserved & copyright ©
 */
public class ConnectionsValidationUtils {

    public boolean alreadyHaveOutput(GConnectable parent) {
        return parent.getConnectors().stream()
                .map(GConnector::getConnections)
                .flatMap(Collection::stream)
                .anyMatch(connection -> connection.getSource().getParent().equals(parent));
    }

}
