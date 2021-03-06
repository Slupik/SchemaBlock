package io.github.slupik.schemablock.javafx.element.block.port.rectangular

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.block.port.rectangular.RectangularElementPortConfigurator as Configurator

/**
 * All rights reserved & copyright ©
 */
internal object RectangularBlockPortsFactory {

    internal fun getList(elementId: String): List<PortInfo> =
            arrayListOf(
                    Configurator.configureForUp(getBase(elementId)),
                    Configurator.configureForRight(getBase(elementId)),
                    Configurator.configureForDown(getBase(elementId)),
                    Configurator.configureForLeft(getBase(elementId))
            )

    private fun getBase(elementId: String): PortInfo {
        val base = PortInfo()
        base.allowForInput = true
        base.allowForOutput = true
        base.parentElementId = elementId
        return base
    }

}