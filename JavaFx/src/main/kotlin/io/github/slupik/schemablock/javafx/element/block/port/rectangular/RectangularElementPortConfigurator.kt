package io.github.slupik.schemablock.javafx.element.block.port.rectangular

import io.github.slupik.schemablock.javafx.element.block.port.oval.OvalElementPortConfigurator
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
internal object RectangularElementPortConfigurator {

    internal fun configureForUp(port: PortInfo): PortInfo =
            OvalElementPortConfigurator.configureForUp(port)

    internal fun configureForRight(port: PortInfo): PortInfo  =
            OvalElementPortConfigurator.configureForRight(port)

    internal fun configureForDown(port: PortInfo): PortInfo  =
            OvalElementPortConfigurator.configureForDown(port)

    internal fun configureForLeft(port: PortInfo): PortInfo  =
            OvalElementPortConfigurator.configureForLeft(port)

}