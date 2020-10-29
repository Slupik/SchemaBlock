package io.github.slupik.schemablock.javafx.element.fx.port.connection

import io.github.slupik.schemablock.javafx.element.UiElementType
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.connection.storage.*
import io.github.slupik.schemablock.javafx.element.fx.port.holder.PortsHolder
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class ChainedSheetElementProvider @Inject constructor(
    private val connectionsHolder: PortConnectionsHolder,
    private val blocksHolder: BlocksHolder,
    private val portsHolder: PortsHolder
) : ChainedElementProvider {

    override fun getStartElementId(): TargetElementId? {
        println("getStartElementId ${blocksHolder.blocks.size}")
        blocksHolder.blocks.forEach {
            println("block $it")
        }
        return blocksHolder.blocks.firstOrNull {
            println(it)
            it.type == UiElementType.START
        }?.elementId
    }

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

            connectionsHolder.connections.filter { connection ->
                ports.any {
                    it.elementId == connection.key.sourcePortId
                }
            }
        } else {
            null
        }
    }

}