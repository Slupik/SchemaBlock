package io.github.slupik.schemablock.javafx.element.fx.port.connection.storage

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.ChainedElementProvider
import io.github.slupik.schemablock.javafx.element.fx.port.element.Port
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All rights reserved & copyright ©
 */

typealias TargetElementId = String

@Singleton
class PortsConnectionsStorage @Inject constructor(
    private val blocksHolder: BlocksHolder,
    private val portsHolder: PortsHolder
) : PortsConnectionsModifier, ChainedElementProvider, PortsConnectionProvider {

    override val connections: MutableMap<ConnectionStorageKey, TargetPort> = mutableMapOf()

    override fun getStartElementId(): TargetElementId? =
        blocksHolder.blocks.firstOrNull {
            it.type == UiElementType.START
        }?.elementId

    override fun getNextElement(sourceBlockId: String): TargetElementId? =
        getConnectionsForBlock(sourceBlockId)
            ?.filter { connection ->
                connection.key is StandardConnectionKey
            }
            ?.values
            ?.firstOrNull()
            ?.owner
            ?.elementId

    override fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId? =
        getConnectionsForBlock(sourceBlockId)
            ?.filter { connection ->
                when(val settings = connection.key) {
                    is ConditionalConnectionKey -> settings.value == result
                    else -> false
                }
            }
            ?.values
            ?.firstOrNull()
            ?.owner
            ?.elementId

    private fun getConnectionsForBlock(sourceBlockId: String): Map<ConnectionStorageKey, TargetPort>? {
        val block = blocksHolder.getBlock(sourceBlockId)
        return if(block != null) {
            val ports = portsHolder.ports.keys.filter { port ->
                port.owner.elementId == block.elementId
            }

            connections.filter { connection ->
                ports.any {
                    it.elementId == connection.key.sourcePortId
                }
            }
        } else {
            null
        }
    }

    override fun add(key: ConnectionStorageKey, target: Port) {
        connections[key] = target
    }

    override fun remove(key: ConnectionStorageKey) {
        connections.remove(key)
    }

}