package io.github.slupik.schemablock.javafx.element.block.utils.port

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.block.utils.port.oval.StartBlockPortsFactory
import io.github.slupik.schemablock.javafx.element.block.utils.port.oval.StopBlockPortsFactory
import io.github.slupik.schemablock.javafx.element.block.utils.port.parallelogram.IOBlockPortsFactory
import io.github.slupik.schemablock.javafx.element.block.utils.port.rectangular.RectangularBlockPortsFactory
import io.github.slupik.schemablock.javafx.element.fx.port.PortInfo

/**
 * All rights reserved & copyright ©
 */
internal object PortInfoProvider {

    internal fun getPortsFor(elementType: UiElementType, elementId: String): List<PortInfo> =
        when (elementType) {
            UiElementType.START ->
                StartBlockPortsFactory.getList(elementId)
            UiElementType.STOP ->
                StopBlockPortsFactory.getList(elementId)
            UiElementType.IO ->
                IOBlockPortsFactory.getList(elementId)
            UiElementType.CALCULATION ->
                RectangularBlockPortsFactory.getList(elementId)
            UiElementType.IF ->
                RectangularBlockPortsFactory.getList(elementId)
        }

}