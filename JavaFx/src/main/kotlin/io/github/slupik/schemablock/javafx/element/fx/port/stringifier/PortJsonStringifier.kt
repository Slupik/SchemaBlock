package io.github.slupik.schemablock.javafx.element.fx.port.stringifier

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortAccessibility
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PortJsonStringifier @Inject constructor(
    private val jsonConverter: Gson
): PortStringifier {

    override fun stringify(port: Port, accessibility: PortAccessibility): String =
        jsonConverter.toJson(
            PortSpecification(
                elementId = port.elementId,
                ownerId = port.owner.elementId,
                accessibility = accessibility,
                relativeX = port.getRelativeX(),
                relativeY = port.getRelativeY()
            )
        )

}