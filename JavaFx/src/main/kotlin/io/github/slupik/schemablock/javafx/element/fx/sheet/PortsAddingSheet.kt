package io.github.slupik.schemablock.javafx.element.fx.sheet

import io.github.slupik.schemablock.javafx.element.Element
import io.github.slupik.schemablock.javafx.element.block.implementation.DescribedBlockPrototype
import io.github.slupik.schemablock.javafx.element.block.port.PortInfoProvider
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo
import io.github.slupik.schemablock.javafx.element.fx.port.connection.establishment.old.PortConnectorOnSheet
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import io.github.slupik.schemablock.javafx.element.fx.port.group.PortGroupImpl
import javafx.scene.layout.Pane

/**
 * All rights reserved & copyright ©
 */
class PortsAddingSheet constructor(
        private val container: Pane,
        wrapee: Sheet
): SheetWrapper(wrapee), Sheet {
        //TODO repair
//    private val pconnector: ConnectionEstablisher = IOAwareConnectionEstablisher(container, PortsConnectionsStorage())

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
//            group.addPort(port)
        }
    }

    private fun spawnPort(element: DescribedBlockPrototype, info: PortInfo): Port {
//        val port = PortElement(element, establishment, info)
//        port.setRelativePos(info.percentOfWidth, info.percentOfHeight)
//        establishment.addPort(port)

        val copy = RoundedPort(
                element
        )
//        pconnector.addPort(
//                copy,
//                PortAccessibilityConfiguration.TWO_WAY
//        )
        copy.setRelativePos(info.percentOfWidth, info.percentOfHeight)
        container.children.add(copy.graphic)
        return copy
    }

}