package io.github.slupik.schemablock.view.logic.provider

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.core.connections.ConnectionType
import de.tesis.dynaware.grapheditor.core.skins.UiElementType
import de.tesis.dynaware.grapheditor.core.skins.defaults.node.Block
import de.tesis.dynaware.grapheditor.model.GNode
import javax.inject.Inject

/**
 * All rights reserved & copyright ©
 */
class DynawareChainedElementProvider @Inject constructor(
    private val graphEditor: GraphEditor
) : ChainedElementProvider {

    override fun getStartElementId(): TargetElementId? =
        graphEditor.model.nodes.firstOrNull { node ->
            val skin = graphEditor.skinLookup.lookupNode(node)
            skin is Block && skin.type == UiElementType.START
        }?.id

    override fun getNextElement(sourceBlockId: String): TargetElementId? =
        graphEditor.model.connections
            .asSequence()
            .filter { connection ->
                val parent = connection.source.parent
                parent is GNode && parent.id == sourceBlockId
            }
            .map { connection -> connection.target.parent }
            .filterIsInstance<GNode>()
            .map { node -> node.id }
            .firstOrNull()

    override fun getNextElement(sourceBlockId: String, result: Boolean): TargetElementId? =
        graphEditor.model.connections
            .asSequence()
            .filter { connection ->
                val parent = connection.source.parent
                parent is GNode && parent.id == sourceBlockId && (
                        connection.type == ConnectionType.CONDITIONAL_FALSE.name ||
                                connection.type == ConnectionType.CONDITIONAL_TRUE.name)
            }
            .map { connection -> connection.target.parent }
            .filterIsInstance<GNode>()
            .map { node -> node.id }
            .firstOrNull()

}