package io.github.slupik.schemablock.javafx.element.block.port.oval

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.block.port.oval.OvalElementPortConfigurator as Configurator

/**
 * All rights reserved & copyright ©
 */
internal object StartBlockPortsFactory {

    internal fun getList(elementId: String): List<PortInfo> =
            arrayListOf(
                    Configurator.configureForUp(getBase(elementId)),
                    Configurator.configureForRight(getBase(elementId)),
                    Configurator.configureForDown(getBase(elementId)),
                    Configurator.configureForLeft(getBase(elementId))
            )

    private fun getBase(elementId: String): PortInfo {
        val base = PortInfo()
        base.allowForInput = false
        base.allowForOutput = true
        base.parentElementId = elementId
        return base
    }

}