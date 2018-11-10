package io.github.slupik.schemablock.javafx.element.fx.port.spawner;

import io.github.slupik.schemablock.javafx.element.fx.UiElementBase;
import io.github.slupik.schemablock.javafx.element.fx.port.connector.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PortSpawnerOnSheet implements PortSpawner {

    private final PortConnector connector;

    public PortSpawnerOnSheet(PortConnector connector){
        this.connector = connector;
    }

    @Override
    public void spawnForElement(UiElementBase element) {
        List<PortInfo> infoList = element.getPortsInfo();
        for(PortInfo info:infoList) {
            spawnPort(element, info);
        }
    }

    private void spawnPort(UiElementBase element, PortInfo info) {
        PortElement port = new PortElement(element, connector, info.allowForInput, info.allowForOutput);
        port.setRelativePos(info.percentOfWidth, info.percentOfHeight);
        connector.addPort(port);
    }

    @Override
    public void restorePorts() {
        //TODO implement
    }
}
