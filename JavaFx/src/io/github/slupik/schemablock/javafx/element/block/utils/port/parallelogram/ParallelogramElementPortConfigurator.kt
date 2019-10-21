package io.github.slupik.schemablock.javafx.element.block.utils.port.parallelogram

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
internal object ParallelogramElementPortConfigurator {

    internal fun configureForUp(port: PortInfo): PortInfo {
        port.percentOfHeight = 0.0
        port.percentOfWidth = 0.5
        port.positionName = "top-middle"
        return port
    }

    internal fun configureForRight(port: PortInfo): PortInfo {
        port.percentOfHeight = 0.5
        port.percentOfWidth = 0.9
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
        port.percentOfWidth = 0.1
        port.positionName = "middle-left"
        return port
    }

}