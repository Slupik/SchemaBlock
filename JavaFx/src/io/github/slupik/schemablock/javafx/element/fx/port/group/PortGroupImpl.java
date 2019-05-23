package io.github.slupik.schemablock.javafx.element.fx.port.group;

import io.github.slupik.schemablock.javafx.element.fx.port.PortElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PortGroupImpl implements PortGroup {

    private final List<PortElement> ports = new ArrayList<>();

    @Override
    public void addPort(PortElement... ports) {
        this.ports.addAll(Arrays.asList(ports));
        for(PortElement port:ports) {
            port.addListener(new PortListener() {
                @Override
                public void onSetNextElement(String elementId, String portId) {
                    onConnectionEstablish(port);
                }

                @Override
                public void onSetNextElement(String nextId, boolean isForTrue) {
                    onConnectionEstablish(port, isForTrue);
                }
            });
        }
    }

    @Override
    public void onConnectionEstablish(PortElement activePort) {
        for(PortElement port:ports) {
            if(!port.getPortId().equals(activePort.getPortId())) {
                port.deleteActiveArrow();
            }
        }
    }

    @Override
    public void onConnectionEstablish(PortElement activePort, boolean isForTrue) {
        for(PortElement port:ports) {
            if(!port.getPortId().equals(activePort.getPortId()) && port.isPortForTrue()==isForTrue) {
                port.deleteActiveArrow();
            }
        }
    }
}
