package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype
import io.github.slupik.schemablock.javafx.element.block.port.PortInfoProvider
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.old.PortConnectorOnSheet
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortGroupImpl
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javafx.scene.layout.Pane
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PortsAddingSheet @Inject constructor(
    private val container: Pane,
    private val holder: PortsHolder,
    wrapee: Sheet
) : SheetWrapper(wrapee), Sheet {
    //TODO repair
//    private val pconnector: ConnectionEstablisher = IOAwareConnectionEstablisher(container, PortsConnectionsStorage())

    private val connector: PortConnectorOnSheet = PortConnectorOnSheet(container)

    override fun addElement(element: Element) {
        super.addElement(element)
        if (element is DescribedBlockPrototype) {
            spawnPorts(element)
        }
    }

    private fun spawnPorts(element: DescribedBlockPrototype) {
        val infoList = PortInfoProvider.getPortsFor(element.type, element.elementId)
        val group = PortGroupImpl()
        for (info in infoList) {
            val port = spawnPort(element, info)
//            group.addPort(port)
        }
    }

    private fun spawnPort(element: DescribedBlockPrototype, info: PortInfo): Port {
        val port = RoundedPort(element)
        val accessibility = getAccessibility(element.type)

        holder.addPort(
            port,
            accessibility
        )
        port.setRelativePos(info.percentOfWidth, info.percentOfHeight)
        container.children.add(port.graphic)
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