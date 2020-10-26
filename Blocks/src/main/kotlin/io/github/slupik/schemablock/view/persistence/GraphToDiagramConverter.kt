package io.github.slupik.schemablock.view.persistence

import de.tesis.dynaware.grapheditor.GraphEditor
import de.tesis.dynaware.grapheditor.core.DefaultGraphEditor
import de.tesis.dynaware.grapheditor.model.GraphFactory
import io.github.slupik.schemablock.view.entity.AdditionalBlockModel
import io.github.slupik.schemablock.view.entity.DefaultDiagram
import io.github.slupik.schemablock.view.entity.Diagram
import io.github.slupik.schemablock.view.persistence.graph.GraphLoader
import org.eclipse.emf.common.util.URI
import javax.inject.Inject


/**
 * All rights reserved & copyright ©
 */
class GraphToDiagramConverter @Inject constructor(
    private val graphLoader: GraphLoader,
    private val saver: DiagramSaver
) {

    fun convert(uri: URI) {
        val editor = createNewGraphEditor()
        graphLoader.loadModel(editor, uri)
        val diagram: Diagram = DefaultDiagram(editor)
        diagram.additionalModel = AdditionalBlockModel(mutableMapOf(), mutableMapOf())
        println(saver.stringify(diagram))
    }

    private fun createNewGraphEditor(): GraphEditor {
        val model = GraphFactory.eINSTANCE.createGModel();
        val graphEditor: GraphEditor = DefaultGraphEditor()
        graphEditor.model = model
        return graphEditor
    }

}