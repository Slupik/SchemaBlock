package io.github.slupik.schemablock.javafx.element.fx.port.connection.stringifier

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ConditionalPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.PortConnectionConfiguration
import io.github.slupik.schemablock.javafx.element.fx.port.connection.StandardPortsConnection
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConditionalConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.ConnectionStorageKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.StandardConnectionKey
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.TargetPort
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PortConnectionJsonStringifier @Inject constructor(
    private val jsonConverter: Gson
) : PortConnectionStringifier {

    override fun stringify(connection: PortConnectionConfiguration): String =
        jsonConverter.toJson(
            when (connection) {
                is StandardPortsConnection -> {
                    StandardConnectionSpecification(
                        sourcePortId = connection.source.elementId,
                        targetPortId = connection.target.elementId
                    )
                }
                is ConditionalPortsConnection -> {
                    ConditionalConnectionSpecification(
                        sourcePortId = connection.source.elementId,
                        targetPortId = connection.target.elementId,
                        condition = connection.value
                    )
                }
            }
        )

    override fun stringify(key: ConnectionStorageKey, target: TargetPort): String =
        jsonConverter.toJson(
            when (key) {
                is StandardConnectionKey -> {
                    StandardConnectionSpecification(
                        sourcePortId = key.sourcePortId,
                        targetPortId = target.elementId
                    )
                }
                is ConditionalConnectionKey -> {
                    ConditionalConnectionSpecification(
                        sourcePortId = key.sourcePortId,
                        targetPortId = target.elementId,
                        condition = key.value
                    )
                }
            }
        )

}