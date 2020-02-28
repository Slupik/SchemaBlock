package io.github.slupik.schemablock.javafx.element.fx.port.spawner.old;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype;
import io.github.slupik.schemablock.javafx.element.block.port.PortInfoProvider;
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.old.PortConnector;
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortGroup;
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortGroupImpl;
import io.github.slupik.schemablock.javafx.element.fx.sheet.old.ElementNotFound;
import io.github.slupik.schemablock.javafx.element.fx.sheet.old.VisibleUIContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights reserved & copyright ©
 */
public class PortSpawnerOnSheet implements PortSpawner {

    private final VisibleUIContainer container;
    private final PortConnector connector;

    public PortSpawnerOnSheet(VisibleUIContainer container, PortConnector connector){
        this.container = container;
        this.connector = connector;
    }

    @Override
    public void spawnForElement(DescribedBlockPrototype element) {
        List<PortInfo> infoList =
                PortInfoProvider.INSTANCE.getPortsFor$JavaFx(element.getType(), element.getElementId());
        PortGroup group = new PortGroupImpl();
        for(PortInfo info:infoList) {
            PortElement port = spawnPort(element, info);
            group.addPort(port);
        }
    }

    private PortElement spawnPort(DescribedBlockPrototype element, PortInfo info) {
        PortElement port = new PortElement(element, connector, info);
        port.setRelativePos(info.percentOfWidth, info.percentOfHeight);
        connector.addPort(port);
        return port;
    }

    @Override
    public void restorePorts(String portsArray) {
        JsonParser parser = new JsonParser();
        JsonArray ports = parser.parse(portsArray).getAsJsonArray();
        HashMap<String, List<PortInfo>> groupedPorts = getGroupedPorts(ports);

        for (Map.Entry<String, List<PortInfo>> entry : groupedPorts.entrySet()) {
            String elementId = entry.getKey();
            List<PortInfo> portsToSpawn = entry.getValue();

            PortGroup group = new PortGroupImpl();
            for(PortInfo info:portsToSpawn) {
                try {
                    PortElement port = spawnPort(container.get(elementId), info);
                    group.addPort(port);
                } catch (ElementNotFound e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private HashMap<String, List<PortInfo>> getGroupedPorts(JsonArray ports) {
        HashMap<String, List<PortInfo>> result = new HashMap<>();

        for(JsonElement element:ports) {
            PortInfo portInfo = new Gson().fromJson(element, PortInfo.class);
            if(!result.containsKey(portInfo.parentElementId)) {
                result.put(portInfo.parentElementId, new ArrayList<>());
            }
            result.get(portInfo.parentElementId).add(portInfo);
        }
        return result;
    }
}
