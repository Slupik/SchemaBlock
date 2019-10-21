package io.github.slupik.schemablock.javafx.element.block.standard;

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class ElementPortProvider {

    private final String elementId;

    ElementPortProvider(String elementId) {
        this.elementId = elementId;
    }

    public List<PortInfo> getPorts() {
        List<PortInfo> list = new ArrayList<>();
        list.add(getUpPort());
        list.add(getRightPort());
        list.add(getDownPort());
        list.add(getLeftPort());
        return list;
    }

    private PortInfo getUpPort() {
        PortInfo up = getBasicPort();
        up.percentOfHeight = 0;
        up.percentOfWidth = 0.5;
        up.positionName = "top-middle";
        return up;
    }

    private PortInfo getRightPort() {
        PortInfo right = getBasicPort();
        right.percentOfHeight = 0.5;
        right.percentOfWidth = 1;
        right.positionName = "middle-right";
        return right;
    }

    private PortInfo getDownPort() {
        PortInfo down = getBasicPort();
        down.percentOfHeight = 1;
        down.percentOfWidth = 0.5;
        down.positionName = "down-middle";
        return down;
    }

    private PortInfo getLeftPort() {
        PortInfo left = getBasicPort();
        left.percentOfHeight = 0.5;
        left.percentOfWidth = 0;
        left.positionName = "middle-left";
        return left;
    }

    private PortInfo getBasicPort(){
        PortInfo base = new PortInfo();
        base.allowForInput = true;
        base.allowForOutput = true;
        base.parentElementId = elementId;
        return base;
    }

}
