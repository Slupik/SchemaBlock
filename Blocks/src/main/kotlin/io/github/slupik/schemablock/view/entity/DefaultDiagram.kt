package io.github.slupik.schemablock.view.entity

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.*
import de.tesis.dynaware.grapheditor.model.GNode
import io.github.slupik.schemablock.view.dialog.data.CodeAndDescription
import io.github.slupik.schemablock.view.dialog.data.DescriptionAndIO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DefaultDiagram @Inject constructor(
    override val graph: GraphEditor
) : Diagram {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(DefaultDiagram::class.java.name)
    }

    override var additionalModel: AdditionalBlockModel
        get() = createAdditionalModel()
        set(value) {
            load(value)
        }

    private fun createAdditionalModel() = AdditionalBlockModel(
        getCodes(),
        getIoOperations()
    )

    private fun getCodes(): MutableMap<BlockId, CodeAndDescription> {
        val map = mutableMapOf<BlockId, CodeAndDescription>()
        graph.model.nodes.stream()
            .forEach {
                val element = graph.skinLookup.lookupNode(it)
                if (element is LabeledNodeSkin) {
                    if (element is ConditionalBlock) {
                        map[it.id] = CodeAndDescription(element.description, element.code)
                    } else if (element is OperationsBlock) {
                        map[it.id] = CodeAndDescription(element.description, element.code)
                    }
                }
            }
        return map;
    }

    private fun getIoOperations(): MutableMap<BlockId, DescriptionAndIO> {
        val map = mutableMapOf<BlockId, DescriptionAndIO>()
        graph.model.nodes.stream()
            .forEach {
                val element = graph.skinLookup.lookupNode(it)
                if (element is IoBlock) {
                    map[it.id] = DescriptionAndIO(element.description, element.operations)
                }
            }
        return map;
    }

    private fun load(additionalModel: AdditionalBlockModel) {
        additionalModel.codingBlocks.entries.forEach {
            val node = getNodeForId(it.key)
            val element = graph.skinLookup.lookupNode(node)
            if (element is Block) {
                when (element) {
                    is ConditionalBlock -> {
                        element.code = it.value.code
                    }
                    is OperationsBlock -> {
                        element.code = it.value.code
                    }
                    else -> {
                        LOGGER.error("Block with id ${it.key} not found.")
                    }
                }
                element.description = it.value.description
            }
        }
        additionalModel.ioBlocks.entries.forEach {
            val node = getNodeForId(it.key)
            if (node is Block) {
                if (node is IoBlock) {
                    node.operations = it.value.operations
                } else {
                    LOGGER.error("Block with id ${it.key} not found.")
                }
                node.description = it.value.description
            }
        }
    }

    private fun getNodeForId(key: BlockId): GNode? =
        graph.model.nodes.firstOrNull {
            it.id == key
        }
}