package de.tesis.dynaware.grapheditor.core.connections;

import de.tesis.dynaware.grapheditor.GNodeSkin;
import de.tesis.dynaware.grapheditor.SkinLookup;
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.ConditionalBlock;
import de.tesis.dynaware.grapheditor.model.GConnectable;
import de.tesis.dynaware.grapheditor.model.GConnection;
import de.tesis.dynaware.grapheditor.model.GConnector;
import de.tesis.dynaware.grapheditor.model.GNode;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * All rights reserved & copyright ©
 */
public class ConnectionsValidationUtils {

    private static final int MAX_COUNT_OF_CONNECTION_FOR_CONDITIONAL_BLOCK = 2;

    private final SkinLookup skinLookup;

    public ConnectionsValidationUtils(SkinLookup skinLookup) {
        this.skinLookup = skinLookup;
    }

    public boolean alreadyHasMaxOutputs(GConnectable parent) {
        Stream<GConnection> connectionsStream = parent.getConnectors().stream()
                .map(GConnector::getConnections)
                .flatMap(Collection::stream);
        if (parent instanceof GNode) {
            GNode node = (GNode) parent;
            GNodeSkin skin = skinLookup.lookupNode(node);
            if (skin instanceof ConditionalBlock) {
                return connectionsStream
                        .filter(connection -> connection.getSource().getParent().equals(parent))
                        .count() == MAX_COUNT_OF_CONNECTION_FOR_CONDITIONAL_BLOCK;
            }
        }
        return connectionsStream.anyMatch(connection -> connection.getSource().getParent().equals(parent));
    }

}
