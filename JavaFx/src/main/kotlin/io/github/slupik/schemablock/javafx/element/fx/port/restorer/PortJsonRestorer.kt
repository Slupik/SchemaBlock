package io.github.slupik.schemablock.javafx.element.fx.port.restorer

import com.google.gson.Gson
import io.github.slupik.schemablock.javafx.element.fx.element.holder.BlocksHolder
import io.github.slupik.schemablock.javafx.element.fx.port.element.RoundedPort
import io.github.slupik.schemablock.javafx.element.fx.port.stringifier.PortSpecification
import io.github.slupik.schemablock.javafx.element.fx.schema.Schema
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class PortJsonRestorer @Inject constructor(
    private val jsonConverter: Gson,
    private val blocksHolder: BlocksHolder
) : PortRestorer {

    override fun restore(schema: Schema, portsToRestore: List<String>) {
        portsToRestore.map {json ->
            jsonConverter.fromJson(json, PortSpecification::class.java)
        }.forEach { specification ->
            val port = createPort(specification)
            port?.let {
                schema.addPort(it, specification.accessibility)
            }
        }
    }

    private fun createPort(specification: PortSpecification): RoundedPort? {
        val owner = getOwner(specification.ownerId)
        return owner?.let {
            RoundedPort(
                owner = owner,
                elementId = specification.elementId
            ).apply {
                setRelativePos(
                    specification.relativeX,
                    specification.relativeY
                )
            }
        }
    }

    private fun getOwner(ownerId: String) =
        blocksHolder.blocks.firstOrNull {
            it.elementId == ownerId
        }

}