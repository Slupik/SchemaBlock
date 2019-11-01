package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype
import io.github.slupik.schemablock.javafx.element.block.port.PortInfoProvider
import io.github.slupik.schemablock.javafx.element.fx.port.PortElement
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.fx.port.connector.old.PortConnectorOnSheet
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortGroupImpl
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
class PortsAddingSheet constructor(
        container: Pane,
        wrapee: Sheet
): SheetWrapper(wrapee), Sheet {

    private val connector: PortConnectorOnSheet = PortConnectorOnSheet(container)

    override fun addElement(element: Element) {
        super.addElement(element)
        if(element is DescribedBlockPrototype) {
            spawnPorts(element)
        }
    }

    private fun spawnPorts(element: DescribedBlockPrototype) {
        val infoList = PortInfoProvider.getPortsFor(element.type, element.elementId)
        val group = PortGroupImpl()
        for (info in infoList) {
            val port = spawnPort(element, info)
            group.addPort(port)
        }
    }

    private fun spawnPort(element: DescribedBlockPrototype, info: PortInfo): PortElement {
        val port = PortElement(element, connector, info)
        port.setRelativePos(info.percentOfWidth, info.percentOfHeight)
        connector.addPort(port)
        return port
    }

}