package io.github.slupik.schemablock.javafx.element.block.utils.port.parallelogram

import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.block.utils.port.parallelogram.ParallelogramElementPortConfigurator as Configurator

/**
 * All rights reserved & copyright ©
 */
internal object IOBlockPortsFactory {

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