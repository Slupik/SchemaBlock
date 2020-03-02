package io.github.slupik.schemablock.javafx.element.fx.port.spawner

import io.github.slupik.schemablock.javafx.dagger.LogicalSheet
import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.Block
import io.github.slupik.schemablock.javafx.element.block.port.PortInfoProvider
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import io.github.slupik.schemablock.javafx.element.fx.sheet.Sheet
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class RoundedPortSpawner @Inject constructor(
    @LogicalSheet private val sheet: Sheet,
    private val holder: PortsHolder
) : PortSpawner {

    override fun spawnFor(block: Block) {
        val infoList = PortInfoProvider.getPortsFor(block.type, block.elementId)
        for (info in infoList) {
            spawnPort(block, info)
        }
    }

    private fun spawnPort(block: Block, info: PortInfo): Port {
        val port = RoundedPort(block)
        val accessibility = getAccessibility(block.type)

        holder.addPort(
            port,
            accessibility
        )
        port.setRelativePos(info.percentOfWidth, info.percentOfHeight)
        return port
    }

    private fun getAccessibility(type: UiElementType): PortAccessibility =
        when (type) {
            UiElementType.START -> PortAccessibility.ONLY_SOURCE
            UiElementType.STOP -> PortAccessibility.ONLY_TARGET
            UiElementType.IF -> PortAccessibility.CONDITIONAL_INPUT
            UiElementType.CALCULATION, UiElementType.IO -> PortAccessibility.TWO_WAY
        }

}