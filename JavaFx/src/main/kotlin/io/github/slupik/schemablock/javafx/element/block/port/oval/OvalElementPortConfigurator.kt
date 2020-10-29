package io.github.slupik.schemablock.javafx.element.block.port.oval

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
internal object OvalElementPortConfigurator {

    internal fun configureForUp(port: PortInfo): PortInfo {
        port.percentOfHeight = 0.0
        port.percentOfWidth = 0.5
        port.positionName = "top-middle"
        return port
    }

    internal fun configureForRight(port: PortInfo): PortInfo {
        port.percentOfHeight = 0.5
        port.percentOfWidth = 1.0
        port.positionName = "middle-right"
        return port
    }

    internal fun configureForDown(port: PortInfo): PortInfo {
        port.percentOfHeight = 1.0
        port.percentOfWidth = 0.5
        port.positionName = "down-middle"
        return port
    }

    internal fun configureForLeft(port: PortInfo): PortInfo {
        port.percentOfHeight = 0.5
        port.percentOfWidth = 0.0
        port.positionName = "middle-left"
        return port
    }

}