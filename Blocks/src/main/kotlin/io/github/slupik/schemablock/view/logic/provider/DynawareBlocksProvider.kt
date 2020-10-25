package io.github.slupik.schemablock.view.logic.provider

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DynawareBlocksProvider @Inject constructor(
    private val graphEditor: GraphEditor
) : BlocksProvider {

    override val blocks: List<Block>
        get() = graphEditor.model.nodes
            .map { node -> graphEditor.skinLookup.lookupNode(node) }
            .filterIsInstance<Block>()
            .toList()

    override fun getBlock(elementId: String): Block? {
        return graphEditor.model.nodes
            .filter { node -> node.id == elementId }
            .map { node -> graphEditor.skinLookup.lookupNode(node) }
            .filterIsInstance<Block>()
            .firstOrNull()
    }
}